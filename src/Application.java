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
        this.companies = new ArrayList<>();
        this.users = new ArrayList<>();
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

    public ArrayList<Job> getJobs(List<String> companies)
    {
        ArrayList<Job> jobs = new ArrayList<>();
        for (Company company : this.companies)
        {
            jobs.addAll(company.getJobs());
        }
        return jobs;
    }

    public ArrayList<Company> getCompanies()
    {
        return companies;
    }

    public Company getCompany(String name)
    {
        for (Company company : this.companies)
        {
            if (company.getCompanyName().equals(name))
            {
                return company;
            }
        }
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
