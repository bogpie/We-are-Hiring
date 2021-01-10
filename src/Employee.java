import java.util.ArrayList;
import java.util.TreeSet;

public class Employee extends Consumer
{
    private String companyName;
    private Double salary;

    protected Employee(Resume resume, ArrayList<Consumer> network, TreeSet<Education> educationSet, TreeSet<Experience> experienceSet, String companyName, Double salary)
    {
        super(resume, network, educationSet, experienceSet);
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

}
