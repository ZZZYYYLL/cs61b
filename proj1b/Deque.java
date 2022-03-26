public interface Deque<T> {

    void addFirst(T item);

    void addLast(T item);

    void printDeque();

    int size();

    T removeFirst();

    T removeLast();

    T get(int index);

    default boolean isEmpty(){
        return size() == 0;
    }

}
