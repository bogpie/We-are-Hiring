public class Request<K, V> implements Comparable<Request<K,V>>
{
    private final K key;
    private final V value1;
    private final V value2;
    private final Double score;

    public Request(K key, V value1, V value2, Double score)
    {
        this.key = key;
        this.value1 = value1;
        this.value2 = value2;
        this.score = score;
    }

    public String toString()
    {
        return "Key: " + key + " ; Value1: " + value1 + " ; Value2: " + value2 + "; Score: " + score;
    }

    @Override
    public int compareTo(Request <K,V> otherRequest)
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
