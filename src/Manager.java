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
        requests = new TreeSet<>();
    }

    public void process(Job job)
    {
        Application application = Application.getInstance();
        Company desiredCompany = application.getCompany(job.getCompany());

        for (Request<Job, Consumer> request : requests)
        {
            User user = (User) request.getValue1();

            if (user.getGraduationYear() == null)
            {
                int breakpoint = 0;
            }

            if (job.getNoPositions() == 0) break;
            if (!request.getKey().getName().equals(job.getName())) continue;
            if (!job.meetsRequirement(user)) continue;

            ArrayList<User> users = application.getUsers();
            ArrayList<Company> companies = application.getCompanies();

            /// previously unemployed
            users.remove(user);

            Employee employee = user.convert();
            employee.setCompanyName(getCompanyName());

            /// previously employed to another company
            boolean isEmployed = false;
            for (Company company : companies)
            {
                if (company.contains(employee))
                {
                    isEmployed = true;
                    break;
                }
            }
            if (isEmployed) continue;

            for (Department department : desiredCompany.getDepartments())
            {
                if (department.getJobs().contains(job))
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
        return "Manager: " + super.toString();

    }

    public void add(Request<Job, Consumer> request)
    {
        requests.add(request);
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
