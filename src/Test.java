import Exceptions.InvalidDatesException;
import com.google.gson.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Test
{

    public static void main(String[] args)
    {
        String pathName = System.getProperty("user.dir") + "/arhiva/consumers.json";
        System.out.println("path name: " + pathName);
        File input = new File(pathName);
        try
        {
            JsonElement jsonElement = JsonParser.parseReader(new FileReader(input));
            JsonObject jsonObject = jsonElement.getAsJsonObject();

            JsonArray employeesArray = jsonObject.get("employees").getAsJsonArray();
            JsonArray recruitersArray = jsonObject.get("recruiters").getAsJsonArray();
            JsonArray usersArray = jsonObject.get("users").getAsJsonArray();
            JsonArray managersArray = jsonObject.get("managers").getAsJsonArray();

            ArrayList<Employee> employees = new ArrayList<>();
            ArrayList<Recruiter> recruiters = new ArrayList<>();
            ArrayList<User> users = new ArrayList<>();
            ArrayList<Manager> managers = new ArrayList<>();

            for (JsonElement employeeElement : employeesArray)
            {
                JsonObject employeeObject = employeeElement.getAsJsonObject();
                Information information = DeserializeHelpers.deserializeInformation(employeeObject);
                TreeSet<Education> educationSet = DeserializeHelpers.deserializeEducationSet(employeeObject);
                TreeSet<Experience> experienceSet = DeserializeHelpers.deserializeExperienceSet(employeeObject);
                double salary = employeeObject.get("salary").getAsDouble();

                employees.add(new Employee(new Consumer.Resume(information), null, educationSet, experienceSet, experienceSet.first().getCompany(), salary));
            }
            
            for (JsonElement managerElement : managersArray)
            {
                JsonObject managerObject = managerElement.getAsJsonObject();
                Information information = DeserializeHelpers.deserializeInformation(managerObject);
                TreeSet<Education> educationSet = DeserializeHelpers.deserializeEducationSet(managerObject);
                TreeSet<Experience> experienceSet = DeserializeHelpers.deserializeExperienceSet(managerObject);
                double salary = managerObject.get("salary").getAsDouble();

                managers.add(new Manager(new Consumer.Resume(information), null, educationSet, experienceSet, experienceSet.first().getCompany(), salary));
            }
            
            for (JsonElement recruiterElement : recruitersArray)
            {
                JsonObject recruiterObject = recruiterElement.getAsJsonObject();
                Information information = DeserializeHelpers.deserializeInformation(recruiterObject);
                TreeSet<Education> educationSet = DeserializeHelpers.deserializeEducationSet(recruiterObject);
                TreeSet<Experience> experienceSet = DeserializeHelpers.deserializeExperienceSet(recruiterObject);
                double salary = recruiterObject.get("salary").getAsDouble();

                recruiters.add(new Recruiter(new Consumer.Resume(information), null, educationSet, experienceSet, experienceSet.first().getCompany(), salary));
            }
            
            for (JsonElement userElement : usersArray)
            {
                JsonObject userObject = userElement.getAsJsonObject();
                Information information = DeserializeHelpers.deserializeInformation(userObject);
                TreeSet<Education> educationSet = DeserializeHelpers.deserializeEducationSet(userObject);
                TreeSet<Experience> experienceSet = DeserializeHelpers.deserializeExperienceSet(userObject);

                JsonArray interestedArray = userObject.getAsJsonArray("interested_companies");
                ArrayList<String> interestedCompanies = new ArrayList<>();
                for (JsonElement interestedElement : interestedArray)
                {
                    interestedCompanies.add(interestedElement.getAsString());
                }

                users.add(new User(new Consumer.Resume(information),null,educationSet,experienceSet,interestedCompanies));
            }


            System.out.println(employees);

        } catch (FileNotFoundException | InvalidDatesException e)
        {
            e.printStackTrace();
        }


    }


}
