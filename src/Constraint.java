public class Constraint
{
    private Number minimum;
    private Number maximum;

    public Constraint(Number minimum, Number maximum)
    {
        this.minimum = minimum;
        this.maximum = maximum;
    }

    public Boolean meetsRequirement(Number value)
    {
        return !(value.doubleValue() < minimum.doubleValue()) && !(value.doubleValue() > minimum.doubleValue());
    }

    public Number getMinimum()
    {
        return minimum;
    }

    public void setMinimum(Number minimum)
    {
        this.minimum = minimum;
    }

    public Number getMaximum()
    {
        return maximum;
    }

    public void setMaximum(Number maximum)
    {
        this.maximum = maximum;
    }

}
