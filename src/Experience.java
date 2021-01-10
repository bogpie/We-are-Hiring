import Exceptions.InvalidDatesException;

import java.time.LocalDate;

public class Experience implements Comparable<Experience>
{
    private String company;
    private String position;
    private final String department;
    private LocalDate startDate;
    private LocalDate endDate;

    public Experience(LocalDate startDate, LocalDate endDate, String position, String company, String department) throws InvalidDatesException
    {
        this.company = company;
        this.position = position;
        this.department = department;
        if (endDate!=null && startDate.compareTo(endDate) > 0)
        {
            throw new InvalidDatesException();
        }
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public int compareTo(Experience otherExperience)
    {
        //Experience otherExperience = (Experience) other;
        if (otherExperience.endDate == null || endDate == null)
        {
            return company.compareTo(otherExperience.company);
        }

        return otherExperience.endDate.compareTo(endDate);
    }

    public LocalDate getStartDate()
    {
        return startDate;
    }

    public LocalDate getEndDate()
    {
        return endDate;
    }

    public String getPosition()
    {
        return position;
    }

    public String getCompany()
    {
        return company;
    }

    public String getDepartment()
    {
        return department;
    }

    public void setStartDate(LocalDate startDate)
    {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate)
    {
        this.endDate = endDate;
    }

    public void setPosition(String position)
    {
        this.position = position;
    }

    public void setCompany(String company)
    {
        this.company = company;
    }
}
