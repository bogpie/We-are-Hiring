import Exceptions.InvalidDatesException;
import Exceptions.ResumeIncompleteException;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class Test
{
    public Consumer.Resume resume;

    public static void main(String[] args)
    {
        String pathName = System.getProperty("user.dir") + "/arhiva/consumers.json";
        File input = new File(pathName);
        ParsedInfo parsedInfo = new ParsedInfo();
        try
        {
            ArrayList<Employee> employees = new ArrayList<>();
            ArrayList<Recruiter> recruiters = new ArrayList<>();
            ArrayList<User> users = new ArrayList<>();
            ArrayList<Manager> managers = new ArrayList<>();

            parsedInfo.parseJson(input, employees, recruiters, users, managers);

            System.out.println(employees + "\n");
            System.out.println(recruiters + "\n");
            System.out.println(users + "\n");
            System.out.println(managers + "\n");


        } catch (FileNotFoundException | InvalidDatesException | ResumeIncompleteException e)
        {
            e.printStackTrace();
        }


    }


}
