public interface Queue<T>
{
    void add(T item);
    T remove();
    T getFirst();
    int getSize();
    boolean isEmpty();
    void clear();
}