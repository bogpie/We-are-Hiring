import java.time.Period;
import java.util.ArrayList;
import java.util.TreeSet;

public abstract class Consumer
{
    private Resume resume;
    private ArrayList<Consumer> network;
    private TreeSet<Education> educationSet; // comparator
    private TreeSet<Experience> experienceSet;

    public Consumer()
    {
    }

    class Resume
    {
        Information information;
    }

    protected Consumer(Resume resume, ArrayList<Consumer> network, TreeSet<Education> educationSet, TreeSet<Experience> experienceSet)
    {
        this.resume = resume;
        this.network = network;
        this.educationSet = educationSet;
        this.experienceSet = experienceSet;
    }

    public void add(Education education)
    {

    }

    public void add(Experience experience)
    {

    }

    public void add(Consumer consumer)
    {

    }

    public int getDegreeInFriendship(Consumer consumer)
    {
        return 0;
    }

    public void remove(Consumer consumer)
    {

    }

    public int getTotalExperience()
    {
        Period totalPeriod = null;
        for (Experience experience : experienceSet)
        {
            Period period = Period.between(experience.getStart(), experience.getEnd());
            totalPeriod.plus(period);
        }
        int nrMonths = totalPeriod.getMonths();
        int nrYears = nrMonths / 12;
        if(nrMonths % 12 != 0) ++nrYears;
        return nrYears;
    }


    public Integer getGraduationYear()
    {
        return null;
    }

    public Double meanGPA()
    {
        Double sumGPA = 0.0;
        for (Education education : educationSet)
        {
            sumGPA += education.getAverage();
        }
        return sumGPA / educationSet.size();
    }


    public Resume getResume()
    {
        return resume;
    }

    public ArrayList<Consumer> getNetwork()
    {
        return network;
    }

    public TreeSet<Education> getEducationSet()
    {
        return educationSet;
    }

    public TreeSet<Experience> getExperienceSet()
    {
        return experienceSet;
    }
}
