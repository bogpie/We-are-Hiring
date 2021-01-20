import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Job
{
    private final String name;
    private final String company;
    private final boolean isOpen;
    private int noPositions;
    private Double salary;
    //@SerializedName("graduation_constraint")
    private final Constraint graduationConstraint;
    //@SerializedName("experience_constraint")
    private final Constraint experienceConstraint;
    //@SerializedName("mean_constraint")
    private final Constraint meanConstraint;

    private ArrayList <User> candidates;

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


    public Job(String name, String company, boolean isOpen, Constraint graduationConstraint, Constraint experienceConstraint, Constraint meanConstraint, int noPositions)
    {
        this.name = name;
        this.company = company;
        this.isOpen = isOpen;
        this.graduationConstraint = graduationConstraint;
        this.experienceConstraint = experienceConstraint;
        this.meanConstraint = meanConstraint;
        this.noPositions = noPositions;
    }

    public void apply(User user)
    {
        Company company = Application.getInstance().getCompany(this.company);
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

    public String getName()
    {
        return name;
    }

    public String getCompany()
    {
        return company;
    }

    public void setNoPositions(int noPositions)
    {
        this.noPositions = noPositions;
    }

    @Override
    public String toString()
    {
        return "Job{" +
                "name='" + name + '\'' +
                ", company='" + company + '\'' +
                ", noPositions=" + noPositions +
                '}';
    }
}
