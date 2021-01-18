import Exceptions.InvalidDatesException;
import Exceptions.ResumeIncompleteException;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

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

    public void parseJson(File input, ArrayList<Employee> employees, ArrayList<Recruiter> recruiters, ArrayList<User> users, ArrayList<Manager> managers)
            throws FileNotFoundException, InvalidDatesException, ResumeIncompleteException, NullPointerException
    {
        JsonElement jsonElement = JsonParser.parseReader(new FileReader(input));
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        ParsedInfo parsedInfo;
        ArrayList<Consumer> network;

        for (JsonElement element : jsonObject.get("employees").getAsJsonArray())
        {
            parsedInfo = DeserializeHelpers.deserializeConsumer(element.getAsJsonObject());
            String companyName = parsedInfo.getResume().getExperienceSet().first().getCompany();
            network = new ArrayList<>();
            employees.add(new Employee(parsedInfo.getResume(), network, companyName, parsedInfo.getSalary()));
        }

        for (JsonElement element : jsonObject.get("recruiters").getAsJsonArray())
        {
            parsedInfo = DeserializeHelpers.deserializeConsumer(element.getAsJsonObject());
            String companyName = parsedInfo.getResume().getExperienceSet().first().getCompany();
            network = new ArrayList<>();
            recruiters.add(new Recruiter(parsedInfo.getResume(), network, companyName, parsedInfo.getSalary()));
        }

        for (JsonElement element : jsonObject.get("managers").getAsJsonArray())
        {
            parsedInfo = DeserializeHelpers.deserializeConsumer(element.getAsJsonObject());
            String companyName = parsedInfo.getResume().getExperienceSet().first().getCompany();
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
}
