import java.util.ArrayList;
import java.util.List;

//singleton

public class Application
{
    private ArrayList<Company> companies;

    private ArrayList<User> users;

    private static Application instance = null;

    private Application(ArrayList<Company> companies, ArrayList<User> users)
    {
        this.companies = companies;
        this.users = users;
    }

    private Application()
    {
        this.companies = null;
        this.users = null;
    }

    public void add(Company company)
    {
        return;
    }

    public void add(User user)
    {
        return;
    }

    public boolean remove(Company company)
    {
        return false;
    }

    public boolean remove(User user)
    {
        return false;
    }

    public static Application getInstance()
    {
        if(instance == null)
        {
            instance = new Application();
        }
        return instance;
    }

    public ArrayList<Job> getJobs(List<String> companies)
    {
        return null;
    }

    public ArrayList<Company> getCompanies()
    {
        return companies;
    }

    public Company getCompany(String name)
    {
        return null;
    }

    public ArrayList<User> getUsers()
    {
        return users;
    }

    public void setUsers(ArrayList<User> users)
    {
        this.users = users;
    }

}
