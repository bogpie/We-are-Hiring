import java.util.ArrayList;
import java.util.Date;

public class Information {
    private final String familyName;
    private final String givenName;
    private final String email;
    private final Date dateOfBirth;
    private final String gender;
    private final ArrayList<String> languages;
    private final ArrayList<String> languagesLevel;

    public Information(String familyName, String givenName, String email, Date dateOfBirth, String gender, ArrayList<String> languages, ArrayList<String> languagesLevel)
    {
        this.familyName = familyName;
        this.givenName = givenName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.languages = languages;
        this.languagesLevel = languagesLevel;
    }

    public String getFamilyName() {
        return familyName;
    }
    public String getGivenName() {
        return givenName;
    }

    public String getEmail() {
        return email;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public ArrayList<String> getLanguages()
    {
        return languages;
    }

    public ArrayList<String> getLanguagesLevel()
    {
        return languagesLevel;
    }
}
