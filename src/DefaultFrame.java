import javax.swing.*;
import java.awt.*;

public class DefaultFrame extends JFrame
{
    public DefaultFrame(String title) throws HeadlessException
    {
        super(title);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(700, 700));
        setLayout(new GridLayout());

    }
}
