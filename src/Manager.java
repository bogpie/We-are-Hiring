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

    protected Manager(Resume resume, ArrayList<Consumer> network, TreeSet<Education> educationSet, TreeSet<Experience> experienceSet, String companyName, Double salary)
    {
        super(resume, network, educationSet, experienceSet, companyName, salary);
    }

    public void process(Job job)
    {
        for (Request<Job, Consumer> request : requests)
        {
            if (job.getNoPositions() == 0) break;
            if (!request.getKey().getJobName().equals(job.getJobName())) continue;

            Application application = Application.getInstance();
            ArrayList<User> users = application.getUsers();

            User user = (User) request.getValue1();

            // previously unemployed
            if (users.contains(user))
            {
                users.remove(user);
                application.setUsers(users);
            }

            // previously employed to another company
            for (Company company : application.getCompanies())
            {
                /// ???
                break;
            }

            Employee employee = new Employee();
            employee = user.convert();

            // adaugat in departamentul specific jobului
            job.setNoPositions(job.getNoPositions() - 1);
        }

    }

}
