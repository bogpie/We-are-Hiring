import java.util.ArrayList;

public class IT extends Department
{
    public IT(ArrayList<Employee> employees, ArrayList<Job> jobs)
    {
        super(employees, jobs);
    }

    @Override
    public double getTotalSalaryBudget()
    {
        double result = 0;
        for(Employee employee : getEmployees())
        {
            result += employee.getSalary();
        }
        return result;
    }
}
