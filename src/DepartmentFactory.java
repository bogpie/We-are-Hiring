import java.util.ArrayList;

public class DepartmentFactory
{
    public DepartmentFactory()
    {
    }

    public static Department factory(String type)
    {
        return switch (type)
                {
                    case "IT" -> new IT();
                    case "Management" -> new Management();
                    case "Marketing" -> new Marketing();
                    case "Finance" -> new Finance();
                    default -> null;
                };
    }
}
