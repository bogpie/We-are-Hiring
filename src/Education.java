import Exceptions.InvalidDatesException;
import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;

public class Education implements Comparable<Education>
{
    private String level;
    private String name;

    @SerializedName("start_date")
    private LocalDate startDate;

    @SerializedName("end_date")
    private LocalDate endDate;

    private Double grade;

    public Education(LocalDate startDate, LocalDate endDate, String institution, String level, Double grade) throws InvalidDatesException
    {
        this.level = level;
        this.grade = grade;
        this.name = institution;
        if(endDate!=null && startDate.compareTo(endDate) > 0)
        {
            throw new InvalidDatesException();
        }
        this.startDate = startDate;
        this.endDate = endDate;
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

    public String getName()
    {
        return name;
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

    public void setStartDate(LocalDate startDate)
    {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate)
    {
        this.endDate = endDate;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setLevel(String level)
    {
        this.level = level;
    }

    public void setGrade(Double grade)
    {
        this.grade = grade;
    }
}
