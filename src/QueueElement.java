public class QueueElement
{
    private Consumer consumer;
    private final int degree;

    public QueueElement(Consumer consumer, int degree)
    {
        this.consumer=consumer;
        this.degree=degree;
    }

    public Consumer getConsumer()
    {
        return consumer;
    }

    public void setConsumer(Consumer consumer)
    {
        this.consumer = consumer;
    }

    public int getDegree()
    {
        return degree;
    }

}
