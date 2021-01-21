import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class AppFrame extends DefaultFrame
{
    private JTree tree;

    public AppFrame(String title)
    {
        super(title);

        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
        tree = new JTree(root);
        DefaultMutableTreeNode companiesNode = new DefaultMutableTreeNode("Companies");
        DefaultMutableTreeNode usersNode = new DefaultMutableTreeNode("Users");
        root.add(companiesNode);
        root.add(usersNode);

        ArrayList<Company> companies = Application.getInstance().getCompanies();
        for (Company company : companies)
        {
            DefaultMutableTreeNode companyNode = new DefaultMutableTreeNode(company.getCompanyName());
            companiesNode.add(companyNode);
            for (Department department : company.getDepartments())
            {
                String classType = department.getClass().toString();
                String departmentName = classType.split(" ")[1];
                DefaultMutableTreeNode departmentNode = new DefaultMutableTreeNode(departmentName);
                companyNode.add(departmentNode);

                DefaultMutableTreeNode employeesNode = new DefaultMutableTreeNode("Employees");
                departmentNode.add(employeesNode);
                for (Employee employee : department.getEmployees())
                {
                    DefaultMutableTreeNode employeeNode = new DefaultMutableTreeNode(employee.getName());
                    employeesNode.add(employeeNode);
                }

                DefaultMutableTreeNode jobsNode = new DefaultMutableTreeNode("Jobs");
                departmentNode.add(jobsNode);
                for (Job job : department.getJobs())
                {
                    DefaultMutableTreeNode jobNode = new DefaultMutableTreeNode(job.getName());
                    // + "- is open: " + job.getOpen());
                    jobsNode.add(jobNode);
                }

                DefaultMutableTreeNode budgetNode = new DefaultMutableTreeNode("Salary Budget: " + department.getTotalSalaryBudget());
                departmentNode.add(budgetNode);
            }

        }

        ArrayList<User> users = Application.getInstance().getUsers();
        for (User user:users)
        {
            DefaultMutableTreeNode userNode = new DefaultMutableTreeNode(user.getName());
            usersNode.add(userNode);

        }
        add(tree);
        show();
        pack();
    }
}
