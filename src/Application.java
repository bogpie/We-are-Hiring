import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;


public class Application
{
    private final ArrayList<Company> companies;
    private ArrayList<User> users;
    private static Application instance = null;

    private DefaultFrame defaultFrame;

    private ArrayList<Employee> employees;

    private ArrayList<Recruiter> recruiters;
    private ArrayList<Manager> managers;
    private final HashMap<String,Consumer> codeToConsumer;

    private Application()
    {
        companies = new ArrayList<>();
        users = new ArrayList<>();
        codeToConsumer = new HashMap<>();
    }

    public void add(Company company)
    {
        companies.add(company);
    }

    public void add(User user)
    {
        users.add(user);
    }

    public boolean remove(Company company)
    {
        boolean contains = companies.contains(company);
        if (!contains)
        {
            companies.remove(company);
        }
        return contains;
    }

    public boolean remove(User user)
    {
        boolean contains = users.contains(user);
        if (!contains)
        {
            users.remove(user);
        }
        return contains;
    }

    public static Application getInstance()
    {
        if (instance == null)
        {
            instance = new Application();
        }
        return instance;
    }

    public ArrayList<Company> getCompanies()
    {
        return companies;
    }

    public Company getCompany(String name)
    {
        for (Company company : getCompanies())
        {
            if (company.getCompanyName().equals(name))
            {
                return company;
            }
        }
        return null;
    }

    public void switchPage(JPanel panel,String title)
    {
        getDefaultFrame().dispose();
        setDefaultFrame(new DefaultFrame(title));
        getDefaultFrame().add(panel);
        getDefaultFrame().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getDefaultFrame().show();
        getDefaultFrame().pack();
    }

    public ArrayList<User> getUsers()
    {
        return users;
    }

    public void setUsers(ArrayList<User> users)
    {
        this.users = users;
    }

    public ArrayList<Employee> getEmployees()
    {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees)
    {
        this.employees = employees;
    }

    public ArrayList<Recruiter> getRecruiters()
    {
        return recruiters;
    }

    public void setRecruiters(ArrayList<Recruiter> recruiters)
    {
        this.recruiters = recruiters;
    }

    public ArrayList<Manager> getManagers()
    {
        return managers;
    }

    public void setManagers(ArrayList<Manager> managers)
    {
        this.managers = managers;
    }

    public HashMap<String, Consumer> getCodeToConsumer()
    {
        return codeToConsumer;
    }

    public DefaultFrame getDefaultFrame()
    {
        return defaultFrame;
    }

    public void setDefaultFrame(DefaultFrame defaultFrame)
    {
        this.defaultFrame = defaultFrame;
    }
}
