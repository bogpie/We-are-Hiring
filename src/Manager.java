import java.util.ArrayList;
import java.util.TreeSet;


public class Manager extends Employee
{
    private final TreeSet<Request<Job, Consumer>> requests;

    protected Manager(Resume resume, ArrayList<Consumer> network, String companyName, Double salary)
    {
        super(resume, network, companyName, salary);
        requests = new TreeSet<>();
    }

    public void process(Job job)
    {
        Application application = Application.getInstance();
        Company desiredCompany = application.getCompany(job.getCompanyName());

        for (Request<Job, Consumer> request : requests)
        {
            User user = (User) request.getValue1();

            if (!request.getKey().getName().equals(job.getName())) continue;
            if (job.getNoPositions() == 0)
            {
                user.update(new Notification("Rejected from " + job.toString(),
                        "Reason: no more positions left"));
                continue;
            }
            if (!job.meetsRequirement(user))
            {
                user.update(new Notification("Rejected from " + job.toString(),
                        "Reason: didn't meet requirements"));
            }

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
                    user.update(new Notification("Accepted! ", job.toString()));

                    for (Company company : application.getCompanies())
                        company.removeObserver(user);

                    break;
                }
            }

            job.setNoPositions(job.getNoPositions() - 1);
        }

        requests.clear();
    }

    @Override
    public String toString()
    {
        return super.toString() + ", manager";

    }

    public void add(Request<Job, Consumer> request)
    {
        requests.add(request);
    }

    public void forceEmploy(Job job, User user)
    {
        Application application = Application.getInstance();

        /// previously unemployed
        Application.getInstance().getUsers().remove(user);

        Employee employee = user.convert();
        employee.setCompanyName(getCompanyName());

        Company desiredCompany = application.getCompany(job.getCompanyName());

        for (Department department : desiredCompany.getDepartments())
        {
            if (department.getJobs().contains(job))
            {
                department.add(user.convert());
                user.update(new Notification("Accepted! ", job.toString()));
                break;
            }
        }

        for (Company company : application.getCompanies())
            company.removeObserver(user);

        job.setNoPositions(job.getNoPositions() - 1);
    }

    public TreeSet<Request<Job, Consumer>> getRequests()
    {
        return requests;
    }
}
