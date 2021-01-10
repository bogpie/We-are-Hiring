import java.util.*;

public class User extends Consumer
{
    private ArrayList<String> interestedCompanies;


    protected User(Resume resume, ArrayList<Consumer> network, TreeSet<Education> educationSet, TreeSet<Experience> experienceSet,ArrayList<String> interestedCompanies)
    {
        super(resume, network, educationSet, experienceSet);
        this.interestedCompanies = interestedCompanies;
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

    public ArrayList<String> getInterestedCompanies()
    {
        return interestedCompanies;
    }

    public void setInterestedCompanies(ArrayList<String> interestedCompanies)
    {
        this.interestedCompanies = interestedCompanies;
    }
}
