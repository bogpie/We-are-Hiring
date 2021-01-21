import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DefaultPanel extends JPanel
{
    public DefaultPanel()
    {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(10, 10, 10, 10));
        add(new ReturnPanel(),BorderLayout.PAGE_END);
    }
}
