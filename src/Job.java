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
        ArrayList<Consumer> network = user.getNetwork();
        Company company = Application.getInstance().getCompany(companyName);
        ArrayList<Recruiter> recruiters = company.getRecruiters();

        int maxDegree = -1;
        Double maxRating = -1.0;
        Recruiter evaluator = null;

        for (Recruiter recruiter : recruiters)
        {
            int degree = user.getDegreeInFriendship(recruiter);
            if (degree > maxDegree)
            {
                evaluator = recruiter;
                maxDegree = degree;
                maxRating = recruiter.getRating();
            }
            else if (degree == maxDegree)
            {
                if (recruiter.getRating() > maxRating)
                {
                    evaluator = recruiter;
                    maxRating = recruiter.getRating();
                }
            }
        }

        if (evaluator != null)
            evaluator.evaluate(this, user);
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
