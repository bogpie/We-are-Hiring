import java.util.*;

public class User extends Consumer implements Observer
{
    private final ArrayList<String> interestedCompanies;
    private final ArrayList<Notification> notifications;

    protected User(Resume resume, ArrayList<String> interestedCompanies)
    {
        super(resume);
        notifications = new ArrayList<>();
        this.interestedCompanies = interestedCompanies;
    }

    public Employee convert()
    {
        Employee employee = new Employee(getResume(), getNetwork(), "", 0.0);
        employee.setCode(getCode());
        return employee;
    }

    @Override
    public void update(Notification notification)
    {
        notifications.add(notification);
        System.out.println("Notification for " + getName() + ": " + notification.getTitle() + " - " +
                notification.getContent());
    }

    @Override
    public String toString()
    {
        return getName() + ", user";

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

}
