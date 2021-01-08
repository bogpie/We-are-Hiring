import Exceptions.InvalidDatesException;

import java.time.LocalDate;

public class Education implements Comparable<Education>
{
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String institution;
    private final String level;
    private final Double grade;

    public Education(LocalDate start, LocalDate end, String institution, String level, Double grade) throws InvalidDatesException
    {
        this.level = level;
        this.grade = grade;
        this.institution = institution;
        if(start.compareTo(end) > 0)
        {
            throw new InvalidDatesException();
        }
        this.startDate = start;
        this.endDate = end;
    }

    public int compareTo(Education otherEducation)
    {
        //Education otherEducation = (Education) other;

        if (endDate == null || otherEducation.endDate == null)
        {
            return otherEducation.startDate.compareTo(startDate);
        }
        if (otherEducation.endDate == endDate)
        {
            return otherEducation.grade.compareTo(grade);
        }
        else
        {
            return otherEducation.endDate.compareTo(endDate);
        }
    }

    public LocalDate getStartDate()
    {
        return startDate;
    }

    public String getInstitution()
    {
        return institution;
    }

    public String getLevel()
    {
        return level;
    }

    public Double getGrade()
    {
        return grade;
    }

    public LocalDate getEndDate()
    {
        return endDate;
    }
}
