import java.util.ArrayList;

public class Finance extends Department
{
    public Finance(ArrayList<Employee> employees, ArrayList<Job> jobs)
    {
        super(employees, jobs);
    }

    @Override
    public double getTotalSalaryBudget()
    {
        double result = 0;
        for (Employee employee : getEmployees())
        {
            int totalExperience = employee.getTotalExperience();
            double salary = employee.getSalary();

            if (totalExperience  < 1)
            {
                result += salary * (1 - 0.1);
            }
            else
            {
                result += salary * (1 - 0.16);
            }

        }
        return result;
    }
}
