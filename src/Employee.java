import java.util.ArrayList;
import java.util.TreeSet;

public class Employee extends Consumer
{
    private final String companyName;
    private final Double salary;

    protected Employee(Resume resume, ArrayList<Consumer> network, String companyName, Double salary)
    {
        super(resume, network);
        this.companyName = companyName;
        this.salary = salary;
    }

    public String getCompanyName()
    {
        return companyName;
    }

    public Double getSalary()
    {
        return salary;
    }

    @Override
    public String toString()
    {
        return "Employee{" +
                "resume=" + resume +
                ", network=" + network +
                ", companyName='" + companyName + '\'' +
                ", salary=" + salary +
                '}';
    }
}
