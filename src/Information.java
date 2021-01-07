import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class Information {
    private String familyName;
    private String givenName;
    private String email;
    private Date birthDate;
    private String sex;
    private ArrayList<Map<String, String>> languages;

    public ArrayList<Map<String, String>> getLanguages() {
        return languages;
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

    public Date getBirthDate() {
        return birthDate;
    }

    public String getSex() {
        return sex;
    }
}
