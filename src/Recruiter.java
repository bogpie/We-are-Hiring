import java.util.ArrayList;

public class Recruiter extends Employee
{
    private Double rating;

    public Recruiter(Resume resume, String companyName, Double salary)
    {
        super(resume, companyName, salary);
        setRating(5.0);
    }

    public Recruiter(Resume resume, ArrayList<Consumer> network, String companyName, Double salary)
    {
        this(resume, companyName, salary);
        this.setNetwork(network);
    }


    public int evaluate(Job job, User user)
    {
        double totalScore = user.getTotalScore();
        double formula = getRating() * totalScore;
        Request<Job, Consumer> request = new Request<>(job, user, this, formula);
        Application.getInstance().getCompany(job.getCompanyName()).getManager().add(request);
        setRating(getRating() + 0.1);
        return (int)formula;
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
