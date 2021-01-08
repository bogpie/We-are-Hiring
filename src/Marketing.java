public class Marketing extends Department
{
    @Override
    public double getTotalSalaryBudget()
    {
        double result = 0;
        for (Employee employee : getEmployees())
        {
            double salary = employee.getSalary();

            if (salary < 3000)
            {
                result += salary;
            }
            else if (salary < 5000)
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
