import Exceptions.InvalidDatesException;

import java.time.LocalDate;

public class Experience implements Comparable<Experience>
{
    private final LocalDate start;
    private final LocalDate end;
    private final String position;
    private final Company company;

    public Experience(LocalDate start, LocalDate end, String position, Company company) throws InvalidDatesException
    {
        this.company = company;
        this.position = position;
        if (start.compareTo(end) > 0)
        {
            throw new InvalidDatesException();
        }
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Experience otherExperience)
    {
        //Experience otherExperience = (Experience) other;
        if (otherExperience.end == null || end == null)
        {
            return company.getCompanyName().compareTo(otherExperience.company.getCompanyName());
        }

        return otherExperience.end.compareTo(end);
    }

    public LocalDate getStart()
    {
        return start;
    }

    public LocalDate getEnd()
    {
        return end;
    }

    public String getPosition()
    {
        return position;
    }

    public Company getCompany()
    {
        return company;
    }
}