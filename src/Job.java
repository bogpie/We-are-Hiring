import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Job
{
    private final String name;
    private final String company;
    private boolean isOpen;

    private int noPositions;
    private Double salary;
    private final Constraint graduationConstraint;
    private final Constraint experienceConstraint;
    private final Constraint meanConstraint;
    private ArrayList <User> candidates;


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

        int formula = 0;

        if (evaluator != null)
            formula = evaluator.evaluate(this, user);

    }

    public boolean meetsRequirement(User user)
    {
        if(!getExperienceConstraint().meetsRequirement(user.getTotalExperience())) return false;
        if(!getGraduationConstraint().meetsRequirement(user.getGraduationYear())) return false;
        return getMeanConstraint().meetsRequirement(user.meanGPA());
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

    public boolean getOpen()
    {
        return isOpen;
    }

    public void setOpen(boolean open)
    {
        isOpen = open;
    }
}
