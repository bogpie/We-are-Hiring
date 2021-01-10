import Exceptions.InvalidDatesException;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class DeserializeHelpers
{
    private static LocalDate convertDate(JsonElement dateElement)
    {
        if (dateElement.isJsonNull())
        {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy");
        return LocalDate.parse(dateElement.getAsString(), formatter);
    }

    static Information deserializeInformation(JsonObject consumerObject)
    {
        String genre = consumerObject.get("genre").getAsString();
        String email = consumerObject.get("email").getAsString();
        String phone = consumerObject.get("phone").getAsString();

        String name = consumerObject.get("name").getAsString();
        StringTokenizer stringTokenizer = new StringTokenizer(name, " ");
        String givenName = stringTokenizer.nextToken();
        String familyName = stringTokenizer.nextToken();

        LocalDate dateOfBirth = convertDate(consumerObject.get("date_of_birth"));

        JsonArray languagesArray = consumerObject.getAsJsonArray("languages");
        ArrayList<String> languages = new ArrayList<>();
        for (JsonElement languageElement : languagesArray)
        {
            languages.add(languageElement.getAsString());
        }

        JsonArray languagesLevelArray = consumerObject.getAsJsonArray("languages_level");
        ArrayList<String> languagesLevel = new ArrayList<>();
        for (JsonElement languageElement : languagesLevelArray)
        {
            languagesLevel.add(languageElement.getAsString());
        }

        return new Information(familyName, givenName, email, phone, dateOfBirth, genre, languages, languagesLevel);
    }

    static TreeSet<Experience> deserializeExperienceSet(JsonObject consumerObject) throws InvalidDatesException
    {
        TreeSet<Experience> experienceSet = new TreeSet<>();
        JsonArray experienceArray = consumerObject.get("experience").getAsJsonArray();
        for (JsonElement experienceElement : experienceArray)
        {
            JsonObject experienceObject = experienceElement.getAsJsonObject();
            String company = experienceObject.get("company").getAsString();
            String position = experienceObject.get("position").getAsString();

            String department;
            if (position.equals("Recruiter")) department = "IT";
            else if (!experienceObject.has("department")) department = null;
            else department = experienceObject.get("department").getAsString();

            LocalDate startDate = convertDate(experienceObject.get("start_date"));
            LocalDate endDate = convertDate(experienceObject.get("end_date"));
            Experience experience = new Experience(startDate, endDate, position, company, department);
            experienceSet.add(experience);
        }
        return experienceSet;
    }


    static TreeSet<Education> deserializeEducationSet(JsonObject consumerObject) throws InvalidDatesException
    {
        TreeSet<Education> educationSet = new TreeSet<>();
        JsonArray educationArray = consumerObject.get("education").getAsJsonArray();
        for (JsonElement educationElement : educationArray)
        {
            JsonObject educationObject = educationElement.getAsJsonObject();
            String level = educationObject.get("level").getAsString();
            String name = educationObject.get("name").getAsString();
            Double grade = educationObject.get("grade").getAsDouble();
            LocalDate startDate = convertDate(educationObject.get("start_date"));
            LocalDate endDate = convertDate(educationObject.get("end_date"));

            educationSet.add(new Education(startDate, endDate, name, level, grade));
        }
        return educationSet;
    }

}
