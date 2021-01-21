import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;

public class ManagerFrame extends DefaultFrame
{
    private JTree tree;

    public ManagerFrame(Manager manager) throws HeadlessException
    {
        super(manager.getName());

        DefaultMutableTreeNode managerNode = new DefaultMutableTreeNode(manager.getName() + "'s requests");

        for (Request<Job, Consumer> request : manager.getRequests())
        {
            DefaultMutableTreeNode requestNode = new DefaultMutableTreeNode("Request for " + request.getValue1().getName()
                    + " , from " + request.getValue1().getName()
                    + ", evaluated by " + request.getValue2().getName());

            managerNode.add(requestNode);
        }

        tree = new JTree(managerNode);

        add(tree);
        show();
        pack();
    }

}
