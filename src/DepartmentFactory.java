import java.util.ArrayList;

public class DepartmentFactory
{
    public DepartmentFactory()
    {
    }

    public static Department factory(String type, String name, ArrayList<Employee> employees, ArrayList<Job> jobs)
    {
        return switch (type)
                {
                    case "IT" -> new IT(employees, jobs);
                    case "Management" -> new Management(employees, jobs);
                    case "Marketing" -> new Marketing(employees, jobs);
                    case "Finance" -> new Finance(employees, jobs);
                    default -> null;
                };
    }
}
