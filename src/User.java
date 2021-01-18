import javax.management.Notification;
import java.util.*;

public class User extends Consumer implements Observer
{
    private ArrayList<String> interestedCompanies;

    protected User(Resume resume, ArrayList<Consumer> network, ArrayList<String> interestedCompanies)
    {
        super(resume, network);
        this.interestedCompanies = interestedCompanies;
    }

    public Employee convert()
    {
        return new Employee(getResume(), getNetwork(), "", 0.0);
    }

    @Override
    public void update(Notification notification)
    {
        System.out.println("Notification for" + getName() + ": " + notification.getMessage());
    }

    @Override
    public String toString()
    {
        return "User: " + super.toString();

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
