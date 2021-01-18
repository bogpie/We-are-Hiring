import Exceptions.InvalidDatesException;
import Exceptions.ResumeIncompleteException;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ParsedInfo
{
    private Consumer.Resume resume;
    private double salary;
    private ArrayList<String> interestedCompanies;

    public ParsedInfo(Consumer.Resume resume, double salary, ArrayList<String> interestedCompanies)
    {
        this.resume = resume;
        this.salary = salary;
        this.interestedCompanies = interestedCompanies;
    }

    public ParsedInfo()
    {

    }

    public Consumer.Resume getResume()
    {
        return resume;
    }

    public void setResume(Consumer.Resume resume)
    {
        this.resume = resume;
    }

    public double getSalary()
    {
        return salary;
    }

    public void setSalary(double salary)
    {
        this.salary = salary;
    }

    public ArrayList<String> getInterestedCompanies()
    {
        return interestedCompanies;
    }

    public void setInterestedCompanies(ArrayList<String> interestedCompanies)
    {
        this.interestedCompanies = interestedCompanies;
    }

    public void parseConsumers(File input, ArrayList<Employee> employees, ArrayList<Recruiter> recruiters, ArrayList<User> users, ArrayList<Manager> managers)
            throws FileNotFoundException, InvalidDatesException, ResumeIncompleteException, NullPointerException
    {
        JsonElement jsonElement = JsonParser.parseReader(new FileReader(input));
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        ParsedInfo parsedInfo;
        ArrayList<Consumer> network;

        for (JsonElement element : jsonObject.get("employees").getAsJsonArray())
        {
            parsedInfo = DeserializeHelpers.deserializeConsumer(element.getAsJsonObject());
            String companyName = parsedInfo.getResume().getCurrentCompany();
            network = new ArrayList<>();
            employees.add(new Employee(parsedInfo.getResume(), network, companyName, parsedInfo.getSalary()));
        }

        for (JsonElement element : jsonObject.get("recruiters").getAsJsonArray())
        {
            parsedInfo = DeserializeHelpers.deserializeConsumer(element.getAsJsonObject());
            String companyName = parsedInfo.getResume().getCurrentCompany();
            network = new ArrayList<>();
            recruiters.add(new Recruiter(parsedInfo.getResume(), network, companyName, parsedInfo.getSalary()));
        }

        for (JsonElement element : jsonObject.get("managers").getAsJsonArray())
        {
            parsedInfo = DeserializeHelpers.deserializeConsumer(element.getAsJsonObject());
            String companyName = parsedInfo.getResume().getCurrentCompany();
            network = new ArrayList<>();
            managers.add(new Manager(parsedInfo.getResume(), network, companyName, parsedInfo.getSalary()));
        }

        for (JsonElement element : jsonObject.get("users").getAsJsonArray())
        {
            parsedInfo = DeserializeHelpers.deserializeConsumer(element.getAsJsonObject());
            network = new ArrayList<>();
            users.add(new User(parsedInfo.getResume(), network, parsedInfo.getInterestedCompanies()));
        }
    }

    public void parseCompanies(File input) throws FileNotFoundException
    {
        JsonElement jsonElement = JsonParser.parseReader(new FileReader(input));
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonArray companiesArray = jsonObject.get("companies").getAsJsonArray();

        for (JsonElement element : companiesArray)
        {
            String companyName = element.getAsJsonObject().get("name").getAsString();

            Manager manager = null;
            String managerName = element.getAsJsonObject().get("manager").getAsString();
            for (Manager iterated : Application.getInstance().getManagers())
            {
                if (iterated.getName().equals(managerName))
                {
                    manager = iterated;
                    break;
                }
            }

            Company company = new Company(companyName, manager);
            Application.getInstance().add(company);

            Department IT = DepartmentFactory.factory("IT");
            Department management = DepartmentFactory.factory("Management");
            Department marketing = DepartmentFactory.factory("Marketing");
            Department finance = DepartmentFactory.factory("Finance");

            for (Department department : Arrays.asList(IT, management, marketing, finance))
            {
                company.add(department);
            }

            JsonArray employeesArray = element.getAsJsonObject().get("employees").getAsJsonArray();
            for (JsonElement employeeElement : employeesArray)
            {
                JsonObject object = employeeElement.getAsJsonObject();
                String name = object.get("name").getAsString();
                String code = object.get("code").getAsString();
                String departmentName = object.get("department").getAsString();


                if (object.get("department").getAsString().equals("Recruiter(IT)"))
                {
                    ///todo add as employee
                    for (Recruiter iterated : Application.getInstance().getRecruiters())
                    {
                        if (iterated.getName().equals(name))
                        {
                            iterated.setCode(code);
                            Application.getInstance().getCodeToConsumer().put(code, iterated);
                            break;
                        }
                    }

                }
                else
                {
                    Employee employee = null;
                    for (Employee iterated : Application.getInstance().getEmployees())
                    {
                        if (iterated.getName().equals(name))
                        {
                            employee = iterated;
                            break;
                        }
                    }
                    employee.setCode(code);
                    Application.getInstance().getCodeToConsumer().put(code, employee);
                    switch (departmentName)
                    {
                        case "IT":
                            IT.add(employee);
                        case "management":
                            management.add(employee);
                        case "marketing":
                            marketing.add(employee);
                        case "finance":
                            finance.add(employee);
                    }
                }
            }
        }

    }

    public void parseUsersCodes(File input) throws FileNotFoundException
    {
        JsonElement jsonElement = JsonParser.parseReader(new FileReader(input));
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonArray usersArray = jsonObject.get("users").getAsJsonArray();
        for (JsonElement element : usersArray)
        {
            String name = element.getAsJsonObject().get("name").getAsString();
            String code = element.getAsJsonObject().get("code").getAsString();
            for (User user : Application.getInstance().getUsers())
            {
                if (user.getName().equals(name))
                {
                    user.setCode(code);
                    Application.getInstance().getCodeToConsumer().put(code, user);
                }

            }
        }
    }

    public void parseGraphEdges(File input) throws FileNotFoundException
    {
        Scanner scanner = new Scanner(input);
        while (scanner.hasNextLine())
        {
            String line = scanner.nextLine();


            StringTokenizer stringTokenizer = new StringTokenizer(line, " ");

            String code1 = stringTokenizer.nextToken();
            String code2 = stringTokenizer.nextToken();

            Consumer consumer1 = Application.getInstance().getCodeToConsumer().get(code1);
            Consumer consumer2 = Application.getInstance().getCodeToConsumer().get(code2);

            consumer1.getNetwork().add(consumer2);
            consumer2.getNetwork().add(consumer1);
        }

    }
}
