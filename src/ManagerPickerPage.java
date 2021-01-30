import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ManagerPickerPage extends DefaultPanel implements ActionListener
{
    public ManagerPickerPage()
    {
        super();

        JLabel label = new JLabel("Choose a manager:");

        JPanel grid = new JPanel(new GridLayout(3, 1));
        add(grid);
        grid.add(label);

        JPanel buttonsPanel = new JPanel();

        ArrayList<Manager> managers = Application.getInstance().getManagers();
        buttonsPanel.setLayout(new GridLayout(managers.size() / 3 + 1, 3));
        for (Manager manager : managers)
        {
            JButton button = new JButton(manager.getName());
            button.addActionListener(this);
            buttonsPanel.add(button);
        }

        grid.add(buttonsPanel);

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() instanceof JButton)
        {
            JButton button = ((JButton) e.getSource());
            String name = button.getText();

            Manager manager = null;
            for (Manager iterated : Application.getInstance().getManagers())
            {
                if (iterated.getName().equals(name))
                {
                    manager = iterated;
                }
            }
            if (manager == null) return;

            Application.getInstance().switchPage(new ManagerPage(manager),manager.toString());


        }
    }
}
