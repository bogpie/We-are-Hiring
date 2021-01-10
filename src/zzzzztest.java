import java.util.ArrayList;
import java.util.Locale;


class Basket
{
    private ArrayList<Apple> apples;

    public Basket(ArrayList<Apple> apples)
    {
        this.apples = apples;
    }

    public Basket()
    {
        apples = new ArrayList<>(10);
    }

    public ArrayList<Apple> getApples()
    {
        return apples;
    }

    public void setApples(ArrayList<Apple> apples)
    {
        this.apples = apples;
    }


}

class Apple
{
    private String taste;
    private String color;

    public Apple(String taste, String color)
    {
        this.taste = taste;
        this.color = color;
    }

    public String getTaste()
    {
        return taste;
    }

    public void setTaste(String taste)
    {
        this.taste = taste;
    }

    public String getColor()
    {
        return color;
    }

    public void setColor(String color)
    {
        this.color = color;
    }
}

class MethodsClass
{
    public MethodsClass()
    {
    }

    public void work(ArrayList<Apple> apples)
    {
        apples.add(new Apple("bad", "yellow"));
        apples.get(0).setTaste(apples.get(0).getTaste().toUpperCase());
    }
}

class UnrelatedTest
{
    public static void mains(String[] args)
    {
        Basket basket = new Basket();
        ArrayList<Apple> apples = basket.getApples();
        apples.add(new Apple("good", "red"));


        MethodsClass methodsClass = new MethodsClass();
        methodsClass.work(apples);
    }
}


