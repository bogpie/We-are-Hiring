import java.time.LocalDate;

public class Education implements Comparable
{
    private LocalDate start;
    private LocalDate end;
    private String institution;
    private String level;
    private Double average;

    public Education(LocalDate start, LocalDate end, String institution, String level, Double average) throws InvalidDatesException
    {
        this.level = level;
        this.average = average;
        this.institution = institution;
        if(start.compareTo(end) > 0)
        {
            throw new InvalidDatesException();
        }
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Object other)
    {
        Education otherEducation = (Education) other;

        if (end == null || otherEducation.end == null)
        {
            return otherEducation.start.compareTo(start);
        }
        if (otherEducation.end == end)
        {
            return otherEducation.average.compareTo(average);
        }
        else
        {
            return otherEducation.end.compareTo(end);
        }
    }

    public LocalDate getStart()
    {
        return start;
    }

    public String getInstitution()
    {
        return institution;
    }

    public String getLevel()
    {
        return level;
    }

    public Double getAverage()
    {
        return average;
    }

    public LocalDate getEnd()
    {
        return end;
    }
}
