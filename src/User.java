import java.util.*;

public class User extends Consumer
{
    private ArrayList<String> companyNames;

    protected User(Resume resume, ArrayList<Consumer> network, TreeSet<Education> educationSet, TreeSet<Experience> experienceSet)
    {
        super(resume, network, educationSet, experienceSet);
    }

    public Employee convert()
    {
        return new Employee(getResume(),getNetwork(),getEducationSet(),getExperienceSet(),"",0.0);
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
