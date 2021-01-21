import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPage extends JPanel implements ActionListener
{

    public MainPage() throws HeadlessException
    {
        super();
        setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel label = new JLabel("Choose a page:");
        add(label);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 3));

        JButton admin = new JButton("Admin");
        buttonsPanel.add(admin);

        JButton manager = new JButton("Manager");
        buttonsPanel.add(manager);

        JButton profile = new JButton("Profile");
        buttonsPanel.add(profile);

        admin.addActionListener(this);
        manager.addActionListener(this);
        profile.addActionListener(this);

        setLayout(new GridLayout(3, 1));
        add(buttonsPanel);


    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() instanceof JButton)
        {
            JButton button = ((JButton) e.getSource());
            Application application = Application.getInstance();
            switch (button.getText())
            {
                case "Admin" -> {
                    AdminPage adminPage = new AdminPage();
                    application.switchPage(adminPage, "Admin");
                }
                case "Manager" -> {
                    ManagerPickerPage managerPickerPage = new ManagerPickerPage();
                    application.switchPage(managerPickerPage, "Pick a manager");
                }
                case "Profile" -> {
                    ProfilePage profilePage = new ProfilePage();
                    application.switchPage(profilePage, "Profile");
                }
            }
        }
    }
}
