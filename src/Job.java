import java.util.ArrayList;

public class Job
{
    private String jobName;
    private String companyName;
    private boolean isOpen;
    private Constraint graduationConstraint;
    private Constraint experienceConstraint;
    private Constraint meanConstraint;

    public Job()
    {
    }

    public Job(String jobName, String companyName, boolean isOpen, Constraint graduationConstraint, Constraint experienceConstraint, Constraint meanConstraint)
    {
        this.jobName = jobName;
        this.companyName = companyName;
        this.isOpen = isOpen;
        this.graduationConstraint = graduationConstraint;
        this.experienceConstraint = experienceConstraint;
        this.meanConstraint = meanConstraint;
    }

    public void apply(User user)
    {
        ArrayList<Consumer> network = user.getNetwork();
        Recruiter recruiter = new Recruiter();
    }

}
