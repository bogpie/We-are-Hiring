import java.util.ArrayList;
import java.util.TreeSet;

public class Recruiter extends Employee
{
    private Double rating;

    protected Recruiter(Resume resume, ArrayList<Consumer> network, String companyName, Double salary)
    {
        super(resume, network, companyName, salary);
        setRating(5.0);
    }


    public int evaluate(Job job, User user)
    {
        Double totalScore = user.getTotalScore();
        Request<Job, Consumer> request = new Request<>(job, user, this, totalScore);
        Application.getInstance().getCompany(job.getCompany()).getManager().getRequests().add(request);
        return (int) (getRating() * totalScore);
    }

    @Override
    public String toString()
    {
        return "Recruiter: " + super.toString();
        /*
        return "Recruiter{" +
                "resume=" + resume +
                ", network=" + getNetwork() +
                ", rating=" + rating +
                '}';

         */
    }

    public Double getRating()
    {
        return rating;
    }

    public void setRating(Double rating)
    {
        this.rating = rating;
    }
}
