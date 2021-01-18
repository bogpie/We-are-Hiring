import java.nio.channels.OverlappingFileLockException;
import java.util.ArrayList;
import java.util.TreeSet;

/*
class ComparatorByScore implements Comparator<Request<Job,Consumer>>
{
    @Override
    public int compare(Request<Job, Consumer> o1, Request<Job, Consumer> o2)
    {
        return o2.getScore().compareTo(o1.getScore());
    }
}*/

public class Manager extends Employee
{
    private TreeSet<Request<Job, Consumer>> requests;

    protected Manager(Resume resume, ArrayList<Consumer> network, String companyName, Double salary)
    {
        super(resume, network, companyName, salary);
    }

    public void process(Job job)
    {
        Application application = Application.getInstance();
        Company desiredCompany = application.getCompany(getCompanyName());

        for (Request<Job, Consumer> request : requests)
        {
            User user = (User) request.getValue1();

            if (job.getNoPositions() == 0) break;
            if (!request.getKey().getJobName().equals(job.getJobName())) continue;
            if(!job.meetsRequirement(user)) continue;

            ArrayList<User> users = application.getUsers();
            ArrayList<Company> companies = application.getCompanies();

            /// previously unemployed
            if (users.contains(user))
            {
                users.remove(user);
                application.setUsers(users);
            }

            /// previously employed to another company
            Employee employee = user.convert();
            for (Company company : companies)
            {
                if(company.contains(employee))
                {
                    company.remove(employee);
                    break;
                }
            }

            for(Department department : desiredCompany.getDepartments())
            {
                if(department.getJobs().contains(job))
                {
                    department.add(employee);
                    break;
                }
            }

            job.setNoPositions(job.getNoPositions() - 1);
        }
    }

    @Override
    public String toString()
    {
        return "Manager{" +
                "resume=" + resume +
                ", network=" + network +
                ", requests=" + requests +
                '}';
    }

    public void setRequests(TreeSet<Request<Job, Consumer>> requests)
    {
        this.requests = requests;
    }

    public TreeSet<Request<Job, Consumer>> getRequests()
    {
        return requests;
    }
}
