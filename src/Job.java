import java.util.ArrayList;
import java.util.HashMap;

public class Job
{
    private final String name;
    private final String companyName;
    private boolean isOpen;

    private int noPositions;
    private Double salary;
    private final Constraint graduationConstraint;
    private final Constraint experienceConstraint;
    private final Constraint meanConstraint;
    private ArrayList<User> candidates;


    public Constraint getGraduationConstraint()
    {
        return graduationConstraint;
    }

    public Constraint getExperienceConstraint()
    {
        return experienceConstraint;
    }

    public Constraint getMeanConstraint()
    {
        return meanConstraint;
    }


    public Job(String name, String companyName, boolean isOpen, Constraint graduationConstraint, Constraint experienceConstraint, Constraint meanConstraint, int noPositions)
    {
        this.name = name;
        this.companyName = companyName;
        this.isOpen = isOpen;
        this.graduationConstraint = graduationConstraint;
        this.experienceConstraint = experienceConstraint;
        this.meanConstraint = meanConstraint;
        this.noPositions = noPositions;
    }

    public void apply(User user)
    {
        Company company = Application.getInstance().getCompany(this.companyName);
        Recruiter evaluator = company.getRecruiter(user);

        int formula = 0;

        if (evaluator != null)
            formula = evaluator.evaluate(this, user);

    }

    public boolean meetsRequirement(User user)
    {
        if (!getExperienceConstraint().meetsRequirement(user.getTotalExperience())) return false;
        if (!getGraduationConstraint().meetsRequirement(user.getGraduationYear())) return false;
        return getMeanConstraint().meetsRequirement(user.meanGPA());
    }

    @Override
    public String toString()
    {
        return name + " @ " + companyName + " ";
    }

    public int getNoPositions()
    {
        return noPositions;
    }

    public String getName()
    {
        return name;
    }

    public String getCompanyName()
    {
        return companyName;
    }

    public void setNoPositions(int noPositions)
    {
        this.noPositions = noPositions;
    }

    public boolean getOpen()
    {
        return isOpen;
    }

    public void setOpen(boolean open)
    {
        isOpen = open;
        Company company = Application.getInstance().getCompany(companyName);

        String title = "Job closed";
        if (open) title = "Job opened";

        company.notifyAllObservers(new Notification(title, this.toString()));
    }
}
