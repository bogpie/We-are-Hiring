import Exceptions.InvalidDatesException;
import Exceptions.ResumeIncompleteException;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Test
{

    private void batchApply()
    {
        for (User user : Application.getInstance().getUsers())
        {
            for (String companyName : user.getInterestedCompanies())
            {
                Company company = Application.getInstance().getCompany(companyName);
                company.addObserver(user);
                ArrayList<Job> jobs = company.getJobs();
                for (Job job : jobs)
                {
                    job.getCandidates().add(user);
                    job.apply(user);
                }
            }
        }

    }

    private void batchEvaluate()
    {
        for (Company company : Application.getInstance().getCompanies())
        {
            Manager manager = company.getManager();

            for (Job job : company.getJobs())
            {
                if (!job.getOpen()) continue;
                job.setOpen(false);
                manager.process(job);
            }
        }
    }


    public static void main(String[] args) throws FileNotFoundException, ResumeIncompleteException,
            InvalidDatesException
    {
        Test test = new Test();

        File input = new File(System.getProperty("user.dir") + "/src/Test_Files/consumers.json");
        ParsedInfo parsedInfo = new ParsedInfo();

        ArrayList<Employee> employees = new ArrayList<>();
        ArrayList<Recruiter> recruiters = new ArrayList<>();
        ArrayList<User> users = new ArrayList<>();
        ArrayList<Manager> managers = new ArrayList<>();

        parsedInfo.parseConsumers(input, employees, recruiters, users, managers);
        Application.getInstance().setUsers(users);
        Application.getInstance().setManagers(managers);
        Application.getInstance().setRecruiters(recruiters);
        Application.getInstance().setEmployees(employees);
        employees.addAll(recruiters);
        employees.addAll(managers);

        input = new File(System.getProperty("user.dir") + "/src/Test_Files/companies.json");
        parsedInfo.parseCompanies(input);

        input = new File(System.getProperty("user.dir") + "/src/Test_Files/users_codes.json");
        parsedInfo.parseUsersCodes(input);

        input = new File(System.getProperty("user.dir") + "/src/Test_Files/graph_edges.txt");
        parsedInfo.parseGraphEdges(input);

        input = new File(System.getProperty("user.dir") + "/src/Test_Files/jobs.json");
        parsedInfo.parseJobs(input);


        test.batchApply();

        Application application = Application.getInstance();
        application.setDefaultFrame(new DefaultFrame("Evaluation query"));

        int answer = JOptionPane.showConfirmDialog(new DefaultFrame("Evaluation"),
                "Would you like to evaluate requests automatically?");
        if (answer != JOptionPane.CANCEL_OPTION && answer != JOptionPane.CLOSED_OPTION)
        {
            if (answer == JOptionPane.YES_OPTION)
            {
                test.batchEvaluate();

                System.out.println("\n");
                System.out.println(Application.getInstance().getCompany("Google").getDepartments().get(0));
                System.out.println("\n");
                System.out.println(Application.getInstance().getCompany("Amazon").getDepartments().get(0));

            }

        }

        application.getDefaultFrame().dispose();
        application.setDefaultFrame(new DefaultFrame("Main Page"));
        application.getDefaultFrame().add(new MainPage());

        application.getDefaultFrame().setLayout(new GridLayout(2, 1));
        application.getDefaultFrame().show();
        application.getDefaultFrame().pack();
    }

}
