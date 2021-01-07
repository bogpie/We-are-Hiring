public class InvalidDatesException extends Exception
{
    public InvalidDatesException()
    {
        super("Invalid dates (start after end)!");
    }
}
