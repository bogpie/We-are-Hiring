import java.util.ArrayList;

public class Job
{
    private final String jobName;
    private final String companyName;
    private final boolean isOpen;
    private final Constraint graduationConstraint;
    private final Constraint experienceConstraint;
    private final Constraint meanConstraint;
    private ArrayList <User> candidates;
    private int noPositions;
    private Double salary;

    public boolean isOpen()
    {
        return isOpen;
    }

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


    public Job(String jobName, String companyName, boolean isOpen, Constraint graduationConstraint, Constraint experienceConstraint, Constraint meanConstraint, int noPositions)
    {
        this.jobName = jobName;
        this.companyName = companyName;
        this.isOpen = isOpen;
        this.graduationConstraint = graduationConstraint;
        this.experienceConstraint = experienceConstraint;
        this.meanConstraint = meanConstraint;
        this.noPositions = noPositions;
    }

    public void apply(User user)
    {
        Company company = Application.getInstance().getCompany(companyName);
        Recruiter evaluator = company.getRecruiter(user);

        if (evaluator != null)
            evaluator.evaluate(this, user);
    }

    public boolean meetsRequirement(User user)
    {
        if(!getExperienceConstraint().meetsRequirement(user.getTotalExperience())) return false;
        if(!getGraduationConstraint().meetsRequirement(user.getTotalExperience())) return false;
        return getMeanConstraint().meetsRequirement(user.meanGPA());
    }

    public int getNoPositions()
    {
        return noPositions;
    }

    public String getJobName()
    {
        return jobName;
    }

    public String getCompanyName()
    {
        return companyName;
    }

    public void setNoPositions(int noPositions)
    {
        this.noPositions = noPositions;
    }
}
