import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Information
{
    private String familyName;

    private String givenName;
    private String email;
    private String phone;
    private LocalDate dateOfBirth;
    private String genre;
    private ArrayList<String> languages;
    private ArrayList<String> languagesLevel;

    public Information(String familyName, String givenName, String email, String phone, LocalDate dateOfBirth, String genre, ArrayList<String> languages, ArrayList<String> languagesLevel)
    {
        this.phone = phone;
        this.familyName = familyName;
        this.givenName = givenName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.genre = genre;
        this.languages = languages;
        this.languagesLevel = languagesLevel;
    }

    public String getFamilyName()
    {
        return familyName;
    }

    public String getGivenName()
    {
        return givenName;
    }

    public String getEmail()
    {
        return email;
    }

    public LocalDate getDateOfBirth()
    {
        return dateOfBirth;
    }

    public String getGenre()
    {
        return genre;
    }

    public ArrayList<String> getLanguages()
    {
        return languages;
    }

    public ArrayList<String> getLanguagesLevel()
    {
        return languagesLevel;
    }

    public void setFamilyName(String familyName)
    {
        this.familyName = familyName;
    }

    public void setGivenName(String givenName)
    {
        this.givenName = givenName;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public void setDateOfBirth(LocalDate dateOfBirth)
    {
        this.dateOfBirth = dateOfBirth;
    }

    public void setGenre(String genre)
    {
        this.genre = genre;
    }

    public void setLanguages(ArrayList<String> languages)
    {
        this.languages = languages;
    }

    public void setLanguagesLevel(ArrayList<String> languagesLevel)
    {
        this.languagesLevel = languagesLevel;
    }
}

