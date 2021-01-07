import java.util.ArrayList;
import java.util.TreeSet;

public class Recruiter extends Employee
{
    public Double rating;

    protected Recruiter(Resume resume, ArrayList<User> network, TreeSet<Education> educationSet, TreeSet<Experience> experienceSet, String companyName, Double salary)
    {
        super(resume, network, educationSet, experienceSet, companyName, salary);
        rating = 5.0;
    }

    protected Recruiter()
    {
        super();
    }

    public int evaluate(Job job, User user)
    {
        Double totalScore = user.getTotalScore();
        Request<Job,Consumer> request = new Request<Job, Consumer>(job,user,this,totalScore);
        int result = (int) (rating * totalScore);
        return result;
    }
}
