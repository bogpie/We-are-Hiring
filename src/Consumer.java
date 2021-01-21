import Exceptions.ResumeIncompleteException;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;

public abstract class Consumer
{
    private Resume resume;
    private ArrayList<Consumer> network;
    private String code;


    public Consumer()
    {
        network = new ArrayList<>();
    }

    public Consumer(Resume resume)
    {
        this();
        this.resume = resume;
    }

    public Consumer(Resume resume, ArrayList<Consumer> network)
    {
        this.resume = resume;
        this.network = network;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();

        builder.append(getName()).append(" - code = ").append(getCode()).append(", network = ");

        for (Consumer consumer : network)
        {
            builder.append(consumer.getName()).append(" - ");
            builder.append(consumer.getCode()).append(", ");
        }
        builder.append("\n");
        return builder.toString();
    }

    public static class Resume
    {

        private final Information information;

        private final TreeSet<Education> educationSet;
        private final TreeSet<Experience> experienceSet;

        private Resume(ResumeBuilder builder)
        {
            this.information = builder.information;
            this.educationSet = builder.educationSet;
            this.experienceSet = builder.experienceSet;
        }

        public String getCurrentCompany()
        {
            if (information.getGivenName().equals("Molly"))
            {
                int breakPoint = 0;
            }
            for (Experience experience : experienceSet)
            {
                if (experience.getEndDate() == null)
                {
                    return experience.getCompany();
                }
            }
            return null;
        }

        @Override
        public String toString()
        {
            return getInformation().getName();
        }

        public static class ResumeBuilder
        {

            private final Information information;


            private TreeSet<Education> educationSet;
            private TreeSet<Experience> experienceSet;

            public ResumeBuilder(Information information)
            {
                this.information = information;
            }

            public ResumeBuilder educationSet(TreeSet<Education> educationSet)
            {
                this.educationSet = educationSet;
                return this;
            }

            public ResumeBuilder experienceSet(TreeSet<Experience> experienceSet)
            {
                this.experienceSet = experienceSet;
                return this;
            }

            public Resume build() throws ResumeIncompleteException
            {
                if (this.educationSet == null || information == null)
                {
                    throw new ResumeIncompleteException();
                }
                return new Resume(this);
            }


        }

        public Information getInformation()
        {
            return information;
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


    public void add(Education education)
    {
        resume.educationSet.add(education);
    }

    public void add(Experience experience)
    {
        resume.experienceSet.add(experience);
    }

    public void add(Consumer consumer)
    {
        network.add(consumer);
    }

    public String getName()
    {
        return resume.information.getGivenName() + " " + resume.information.getFamilyName();
    }

    public int getDegreeInFriendship(Consumer consumer)
    {
        ArrayList<Consumer> consumers = new ArrayList<>();
        HashMap<Consumer, Boolean> isVisited = new HashMap<>();

        int degree = 0;
        consumers.add(this);
        while (!consumers.isEmpty())
        {
            isVisited.put(consumers.get(0), true);
            for (Consumer friend : network)
            {
                if (isVisited.get(friend) == null || !isVisited.get(friend))
                {
                    if (friend.equals(consumer))
                    {
                        return degree;
                    }

                }
            }
            consumers.remove(consumers.get(0));
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
        for (Experience experience : resume.experienceSet)
        {
            LocalDate endDate = experience.getEndDate();
            if (endDate == null) endDate = LocalDate.now();
            Period period = Period.between(experience.getStartDate(), endDate);
            totalPeriod = totalPeriod.plus(period);
        }

        int noMonths = ((int) totalPeriod.toTotalMonths());
        int nrYears = noMonths / 12;
        if (noMonths % 12 != 0) ++nrYears;
        return nrYears;
    }


    public Integer getGraduationYear()
    {
        if (resume.educationSet.first().getEndDate() != null)
        {
            return resume.educationSet.first().getEndDate().getYear();
        }
        /*Iterator<Education> it = resume.educationSet.iterator();
        if (it.hasNext())
        {
            return it.next().getEndDate().getYear();
        }*/
        return null;
    }

    public Double meanGPA()
    {
        Double sumGPA = 0.0;
        for (Education education : resume.educationSet)
        {
            sumGPA += education.getGrade();
        }
        return sumGPA / resume.educationSet.size();
    }


    public Resume getResume()
    {
        return resume;
    }

    public ArrayList<Consumer> getNetwork()
    {
        return network;
    }

    public void setNetwork(ArrayList<Consumer> network)
    {
        this.network = network;
    }


    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

}