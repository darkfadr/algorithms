package genericlinkedlist;

public class Pair<T, S>
{
    private T first;
    private S second;

    public Pair(T f, S s)
    {
        first = f;
        second = s;
    }

    public T getFirst()
    {
        return first;
    }

    public S getSecond()
    {
        return second;
    }

    public void setFirst(T v)
    {
        first = v;
    }

    public void setSecond(S v)
    {
        second = v;
    }
    
    @Override
    public String toString()
    {
        return "[" + first + ", " + second + "]";
    }
}
