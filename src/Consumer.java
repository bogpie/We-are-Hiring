import java.time.Period;
import java.util.*;

public abstract class Consumer
{
    private final Resume resume;
    private final ArrayList<Consumer> network;
    private final TreeSet<Education> educationSet; // comparator in education class
    private final TreeSet<Experience> experienceSet;

    public Consumer()
    {
        resume = new Resume();
        network = new ArrayList<>();
        educationSet = new TreeSet<>();
        experienceSet = new TreeSet<>();
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
        educationSet.add(education);
    }

    public void add(Experience experience)
    {
        experienceSet.add(experience);
    }

    public void add(Consumer consumer)
    {
        network.add(consumer);
    }

    public int getDegreeInFriendship(Consumer consumer)
    {
        LinkedList<Consumer> consumers = new LinkedList<>();
        HashMap<Consumer, Boolean> isVisited = new HashMap<>();
        int degree = 0;

        consumers.add(this);
        while (!consumers.isEmpty())
        {
            isVisited.put(consumers.getFirst(), true);
            for (Consumer friend : consumers)
            {
                if (isVisited.get(friend)) continue;

                if (friend.equals(consumer))
                {
                    return degree;
                }
            }

            consumers.remove(this);
            ++degree;
        }

        return degree;
    }

    public void remove(Consumer consumer)
    {
        consumer.remove(consumer);
    }

    public int getTotalExperience()
    {
        Period totalPeriod = Period.ZERO;
        for (Experience experience : experienceSet)
        {
            Period period = Period.between(experience.getStart(), experience.getEnd());
            totalPeriod.plus(period);
        }

        int nrMonths = totalPeriod.getMonths();
        int nrYears = nrMonths / 12;
        if (nrMonths % 12 != 0) ++nrYears;
        return nrYears;
    }


    public Integer getGraduationYear()
    {
        if (educationSet.first().getEndDate() != null)
        {
            return educationSet.first().getEndDate().getYear();
        }
        Iterator<Education> it = educationSet.iterator();
        if (it.hasNext())
        {
            return it.next().getEndDate().getYear();
        }
        return null;
    }

    public Double meanGPA()
    {
        Double sumGPA = 0.0;
        for (Education education : educationSet)
        {
            sumGPA += education.getGrade();
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
