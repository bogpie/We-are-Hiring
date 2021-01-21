import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReturnPanel extends JPanel implements ActionListener
{
    private JButton button;
    public ReturnPanel()
    {
        button = new JButton("Return to main page");
        button.addActionListener(this);
        add(button);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() instanceof JButton)
        {
            Application application = Application.getInstance();
            application.getDefaultFrame().dispose();
            application.setDefaultFrame(new DefaultFrame("Main page"));
            application.getDefaultFrame().add(new MainPage());
            application.getDefaultFrame().show();
            application.getDefaultFrame().pack();
        }
    }
}
