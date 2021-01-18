import java.util.ArrayList;
import java.util.TreeSet;

public class Employee extends Consumer
{
    private String companyName;
    private Double salary;

    protected Employee(Resume resume, ArrayList<Consumer> network, String companyName, Double salary)
    {
        super(resume, network);
        this.companyName = companyName;
        this.salary = salary;
    }

    public Employee()
    {
        super();
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
        return "Employee: " + super.toString();

        /*
        return "Employee{" +
                "resume=" + getResume() +
                ", companyName='" + getCompanyName() + '\'' +
                ", salary=" + getSalary() +
                '}';
        */
    }
}
