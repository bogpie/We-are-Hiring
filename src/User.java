import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeSet;

public class User extends Consumer
{
    private ArrayList<String> companyNames;

    protected User(Resume resume, ArrayList<User> network, TreeSet<Education> educationSet, TreeSet<Experience> experienceSet)
    {
        super(resume, network, educationSet, experienceSet);
    }

    public Employee convert()
    {
        return null;
    }

    public Double getTotalScore()
    {
        int totalExperience = getTotalExperience();
        Double meanGPA = meanGPA();
        return totalExperience * 1.5 + meanGPA;
    }

    public ArrayList<String> getCompanyNames()
    {
        return companyNames;
    }
}
