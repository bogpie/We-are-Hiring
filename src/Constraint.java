public class Constraint
{
    private final Number minimum;
    private final Number maximum;

    public Constraint(Number minimum, Number maximum)
    {
        this.minimum = minimum;
        this.maximum = maximum;
    }

    public Boolean meetsRequirement(Number value)
    {
        if (minimum == null && maximum == null)
        {
            return true;
        }
        if (minimum == null)
        {
            return value.doubleValue() <= maximum.doubleValue();
        }
        if (maximum == null)
        {
            return value.doubleValue() >= minimum.doubleValue();
        }
        if(value == null)
        {
            return false;
        }
        return (value.doubleValue() >= minimum.doubleValue()) && (value.doubleValue() <= maximum.doubleValue());
    }

}
