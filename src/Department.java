import java.util.ArrayList;

public abstract class Department
{
    private ArrayList<Employee> employees;
    private ArrayList<Job> jobs;

    public Department()
    {
        employees = new ArrayList<>();
        jobs = new ArrayList<>();
    }

    public Department(ArrayList<Employee> employees, ArrayList<Job> jobs)
    {
        this.employees = employees;
        this.jobs = jobs;
    }

    public void add(Employee employee)
    {
        employees.add(employee);
    }

    public void remove(Employee employee)
    {
        employees.remove(employee);
    }

    public void add(Job job)
    {
        jobs.add(job);
    }

    public abstract double getTotalSalaryBudget();

    public ArrayList<Job> getJobs()
    {
        return jobs;
    }

    public ArrayList<Employee> getEmployees()
    {
        return employees;
    }


    public void setEmployees(ArrayList<Employee> employees)
    {
        this.employees = employees;
    }

    public void setJobs(ArrayList<Job> jobs)
    {
        this.jobs = jobs;
    }
}
