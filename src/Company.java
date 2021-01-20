import javax.management.Notification;
import java.util.ArrayList;

public class Company implements Subject
{
    private final String companyName;
    private final Manager manager;
    private ArrayList<Department> departments;
    private ArrayList<Recruiter> recruiters;
    private ArrayList<User> applicants;

    public Company(String companyName, Manager manager)
    {
        this.companyName = companyName;
        this.manager = manager;
        applicants = new ArrayList<>();
        departments = new ArrayList<>();
        recruiters = new ArrayList<>();
    }

    public Company(String companyName, Manager manager, ArrayList<Department> departments, ArrayList<Recruiter> recruiters)
    {
        this(companyName, manager);
        this.departments = departments;
        this.recruiters = recruiters;
    }

    public void add(Department department)
    {
        departments.add(department);
    }

    public void add(Recruiter recruiter)
    {
        recruiters.add(recruiter);

    }

    public void add(Employee employee, Department department)
    {
        department.add(employee);

        /*

        for (Department iterated : departments)
        {
            if (iterated == department)
            {
                //departments.remove(department);
                department.add(employee);
                //departments.add(department);
                return;
            }
        }*/
    }

    public void remove(Employee employee)
    {
        for (Department iterated : departments)
        {
            if (iterated.getEmployees().contains(employee))
            {
                //departments.remove(iterated);
                iterated.remove(employee);
                //departments.add(iterated);
            }
        }
    }

    public void remove(Department department)
    {
        departments.remove(department);
    }

    public void remove(Recruiter recruiter)
    {
        recruiters.remove(recruiter);
    }

    public void move(Department source, Department destination)
    {

        ArrayList<Employee> employees = new ArrayList<>(source.getEmployees());
        employees.addAll(destination.getEmployees());
        ArrayList<Job> jobs = new ArrayList<>(destination.getJobs());
        destination.setEmployees(employees);
        destination.setJobs(jobs);
    }

    public void move(Employee employee, Department newDepartment)
    {
        for (Department department : departments)
        {
            if (department.getEmployees().contains(employee))
            {
                department.remove(employee);
            }
        }
        newDepartment.add(employee);

    }

    public boolean contains(Department department)
    {
        return departments.contains(department);
    }

    public boolean contains(Employee employee)
    {
        for (Department department : departments)
        {
            if (department.getEmployees().contains(employee)) return true;
            for (Employee iterated : department.getEmployees())
            {
                if (iterated.equals(employee))
                {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean contains(Recruiter recruiter)
    {
        return recruiters.contains(recruiter);
    }

    public Recruiter getRecruiter(User user)
    {
        Recruiter evaluator = null;
        int maxDegree = -1;
        Double maxRating = -1.0;

        for (Recruiter recruiter : recruiters)
        {
            int degree = user.getDegreeInFriendship(recruiter);
            if (degree > maxDegree)
            {
                evaluator = recruiter;
                maxDegree = degree;
                maxRating = recruiter.getRating();
            }
            else if (degree == maxDegree)
            {
                if (recruiter.getRating() > maxRating)
                {
                    evaluator = recruiter;
                    maxRating = recruiter.getRating();
                }
            }
        }
        return evaluator;
    }

    public ArrayList<Job> getJobs()
    {
        ArrayList<Job> jobs = new ArrayList<>();
        for (Department department : departments)
        {
            jobs.addAll(department.getJobs());
        }
        return jobs;
    }

    @Override
    public void addObserver(User user)
    {
        applicants.add(user);
    }

    @Override
    public void removeObserver(User user)
    {
        applicants.remove(user);
    }

    @Override
    public void notifyAllObservers()
    {
        Notification notification = new Notification("Type", this, 1, "Message");
        for (User user : applicants)
        {
            user.update(notification);
        }
    }


    @Override
    public String toString()
    {
        return "Company{" +
                "companyName='" + companyName + '\'' +
                ", manager=" + manager +
                "}\n";
    }

    public String getCompanyName()
    {
        return companyName;
    }

    public Manager getManager()
    {
        return manager;
    }

    public ArrayList<Department> getDepartments()
    {
        return departments;
    }

    public ArrayList<Recruiter> getRecruiters()
    {
        return recruiters;
    }


    public ArrayList<User> getApplicants()
    {
        return applicants;
    }

    public void setApplicants(ArrayList<User> applicants)
    {
        this.applicants = applicants;
    }
}
