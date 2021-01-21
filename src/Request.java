public class Request<K, V> implements Comparable<Request<K, V>>
{
    private K key;
    private V value1;
    private V value2;
    private Double score;

    public Request(K key, V value1, V value2, Double score)
    {
        this.key = key;
        this.value1 = value1;
        this.value2 = value2;
        this.score = score;
    }

    public Request()
    {

    }

    public String toString()
    {
        return "Request for " + ((Job) key).getName()
                + " , from " + ((Consumer) getValue1()).getName()
                + ", evaluated by " + ((Consumer) getValue2()).getName();
    }

    @Override
    public int compareTo(Request<K, V> otherRequest)
    {
        //return ((Request) other).getScore().compareTo(getScore());
        return otherRequest.getScore().compareTo(getScore());
    }

    public K getKey()
    {
        return key;
    }

    public V getValue1()
    {
        return value1;
    }

    public V getValue2()
    {
        return value2;
    }

    public Double getScore()
    {
        return score;
    }
}
