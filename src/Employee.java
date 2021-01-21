import java.util.ArrayList;

public class Employee extends Consumer
{
    private String companyName;

    private Double salary;


    public Employee(Resume resume, String companyName, Double salary)
    {
        super(resume);
        this.companyName = companyName;
        this.salary = salary;
    }

    public Employee(Resume resume, ArrayList<Consumer> network, String companyName, Double salary)
    {
        this(resume, companyName, salary);
        this.setNetwork(network);
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
    public boolean equals(Object other)
    {
        if(getClass() != other.getClass()) return false;
        return (((Employee) other).getName().equals(this.getName()));
    }

    @Override
    public String toString()
    {
        return getName() + " " + "@" + companyName;

    }

    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }
}
