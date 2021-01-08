import java.util.ArrayList;

public class Company
{
    private final String companyName;
    private final Manager manager;
    private final ArrayList<Department> departments;
    private final ArrayList<Recruiter> recruiters;

    public Company(String companyName, Manager manager, ArrayList<Department> departments, ArrayList<Recruiter> recruiters)
    {
        this.companyName = companyName;
        this.manager = manager;
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
        for (Department iterated : departments)
        {
            if (iterated == department)
            {
                departments.remove(department);
                department.add(employee);
                departments.add(department);
                return;
            }
        }
    }

    public void remove(Employee employee)
    {
        for (Department iterated : departments)
        {
            if (iterated.getEmployees().contains(employee))
            {
                departments.remove(iterated);
                iterated.remove(employee);
                departments.add(iterated);
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
        ArrayList <Employee> employees = new ArrayList<>();
        ArrayList <Job> jobs = new ArrayList<>();
        for(Department department : departments)
        {
            if(department.equals(source))
            {
                departments.remove(source);
                employees.addAll(source.getEmployees());
                jobs.addAll(source.getJobs());
            }
        }

        for(Department department : departments)
        {
            if(department.equals(destination))
            {
                departments.remove(destination);
                employees.addAll(destination.getEmployees());
                jobs.addAll(destination.getJobs());
                destination.setEmployees(employees);
                destination.setJobs(jobs);
                departments.add(destination);
            }
        }
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

        for (Department department:departments)
        {
            if(department.equals(newDepartment))
            {
                departments.remove(department);
                department.add(employee);
                departments.add(department);
            }
        }
    }

    public boolean contains(Department department)
    {
        return departments.contains(department);
    }

    public boolean contains(Employee employee)
    {
        for (Department department : departments)
        {
            if (department.getEmployees().contains(employee))
            {
                return true;
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
        // ????
        return null;
    }

    public ArrayList<Job> getJobs()
    {
        ArrayList<Job> jobs = new ArrayList<>();
        for(Department department : departments)
        {
            jobs.addAll(department.getJobs());
        }
        return jobs;
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
}
