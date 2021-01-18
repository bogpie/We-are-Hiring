import java.util.ArrayList;

public class Management extends Department
{
    public Management(ArrayList<Employee> employees, ArrayList<Job> jobs)
    {
        super(employees, jobs);
    }

    @Override
    public double getTotalSalaryBudget()
    {
        double result = 0;
        for (Employee employee : getEmployees())
        {
            result += employee.getSalary() * (1 - 0.16);
        }
        return result;
    }
}
