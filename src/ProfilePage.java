import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TreeSet;

public class ProfilePage extends DefaultPanel implements ActionListener, ListSelectionListener
{
    private JList<Consumer> list;
    private DefaultListModel<Consumer> model;
    private JTextField searchField;
    private int selectedIndex;
    private User selectedUser;
    private JTree tree;

    public ProfilePage()
    {
        super();
        list = new JList<>();
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JPanel searchBar = new JPanel(new BorderLayout());
        add(searchBar, BorderLayout.PAGE_START);

        searchField = new JTextField(15);
        JButton searchButton = new JButton("SEARCH");
        searchButton.addActionListener(this);
        searchBar.add(searchField);
        searchBar.add(searchButton, BorderLayout.EAST);

        model = new DefaultListModel<>();
        list = new JList<>(model);
        list.addListSelectionListener(this);

        tree = new JTree(new DefaultMutableTreeNode("Details for selected: "));

        JPanel grid = new JPanel(new GridLayout(1, 2));
        grid.add(list);
        grid.add(tree);
        add(grid);

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() instanceof JButton)
        {
            JButton button = ((JButton) e.getSource());
            if (button.getText().equals("SEARCH"))
            {
                model.clear();

                Application application = Application.getInstance();

                for (User user : application.getUsers())
                {
                    if (user.getName().toLowerCase().contains(searchField.getText().toLowerCase()))
                    {
                        model.addElement(user);
                    }
                }

                for (Employee employee : application.getEmployees())
                {
                    if (employee.getName().toLowerCase().contains(searchField.getText().toLowerCase()))
                    {
                        model.addElement(employee);
                    }
                }

            }
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e)
    {
        if (list.isSelectionEmpty())
        {
            return;
        }

        Consumer selected = list.getSelectedValue();

        DefaultTreeModel treeModel = (DefaultTreeModel) tree.getModel();
        treeModel.setRoot(new DefaultMutableTreeNode("Details for selected: "));
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) treeModel.getRoot();
        {
            Consumer.Resume resume = selected.getResume();
            DefaultMutableTreeNode resumeNode = new DefaultMutableTreeNode("Resume");
            root.add(resumeNode);

            Information information = resume.getInformation();
            DefaultMutableTreeNode informationNode = new DefaultMutableTreeNode("Information");
            resumeNode.add(informationNode);
            populateInformationNode(information, informationNode);

            TreeSet<Education> educationSet = resume.getEducationSet();
            DefaultMutableTreeNode educationSetNode = new DefaultMutableTreeNode("Education");
            populateEducationSetNode(educationSet, educationSetNode);
            resumeNode.add(educationSetNode);

            TreeSet<Experience> experienceSet = resume.getExperienceSet();
            DefaultMutableTreeNode experienceSetNode = new DefaultMutableTreeNode("Experience");
            populateExperienceSetNode(experienceSet, experienceSetNode);
            resumeNode.add(experienceSetNode);

        }

    }

    private void populateExperienceSetNode(TreeSet<Experience> experienceSet, DefaultMutableTreeNode experienceSetNode)
    {
        for (Experience experience : experienceSet)
        {
            DefaultMutableTreeNode experienceNode = new DefaultMutableTreeNode(experience.getPosition() + " @ " + experience.getCompany());
            experienceSetNode.add(experienceNode);
            experienceNode.add(new DefaultMutableTreeNode("Department: " + experience.getDepartment()));
            experienceNode.add(new DefaultMutableTreeNode("Start date: " + experience.getStartDate()));
            experienceNode.add(new DefaultMutableTreeNode("End date: " + experience.getEndDate()));

        }
    }

    private void populateEducationSetNode(TreeSet<Education> educationSet, DefaultMutableTreeNode educationSetNode)
    {
        for (Education education : educationSet)
        {
            DefaultMutableTreeNode educationNode = new DefaultMutableTreeNode(education.getLevel());
            educationSetNode.add(educationNode);
            educationNode.add(new DefaultMutableTreeNode("Name: " + education.getName()));
            educationNode.add(new DefaultMutableTreeNode("Grade: " + education.getGrade()));
            educationNode.add(new DefaultMutableTreeNode("Start date: " + education.getStartDate()));
            educationNode.add(new DefaultMutableTreeNode("End date: " + education.getEndDate()));
        }
    }

    private void populateInformationNode(Information information, DefaultMutableTreeNode informationNode)
    {
        informationNode.add(new DefaultMutableTreeNode("Date of birth: " + information.getDateOfBirth()));
        informationNode.add(new DefaultMutableTreeNode("Email: " + information.getEmail()));
        informationNode.add(new DefaultMutableTreeNode("Genre: " + information.getGenre()));

        DefaultMutableTreeNode languagesNode = new DefaultMutableTreeNode("Languages");
        for (int idLang = 0; idLang < information.getLanguages().size(); ++idLang)
        {
            String language = information.getLanguages().get(idLang);
            String level = information.getLanguagesLevel().get(idLang);
            languagesNode.add(new DefaultMutableTreeNode(language + ", " + level));
        }
        informationNode.add(languagesNode);
        informationNode.add(new DefaultMutableTreeNode(information.getPhone()));
    }
}
