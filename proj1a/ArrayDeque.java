public class ArrayDeque<T> {

    private T[] items;
    private int head;
    private int tail;
    private int size;

    //Creates an empty array deque
    public ArrayDeque() {
        items = (T[]) new Object[8];
        head = 0;
        tail = 1;
        size = 0;
    }

    //Adds an item of type T to the front of the deque
    public void addFirst(T item) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        items[head] = item;
        size++;
        head = minusOne(head);
    }

    // Adds an item of type T to the back of the deque
    public void addLast(T item) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        items[tail] = item;
        size++;
        tail = plusOne(tail);
    }

    // Returns true if deque is empty, false otherwise
    public boolean isEmpty() {
        return size == 0;
    }

    //Returns the number of items in the deque
    public int size() {
        return size;
    }

    //Prints the items in the deque from first to last, separated by a space
    public void printDeque() {
        for (int i = plusOne(head); i != tail; i = plusOne(i)) {
            System.out.println(items[i] + " ");
        }
        System.out.println();
    }

    //Removes and returns the item at the front of the deque. If no such item exists, returns null
    public T removeFirst() {
        head = plusOne(head);
        T removeItem = items[head];
        items[head] = null;
        if (!isEmpty()) {
            size--;
        }
        return removeItem;
    }

    //Removes and returns the item at the back of the deque. If no such item exists, returns null
    public T removeLast() {
        tail = minusOne(tail);
        T removeItem = items[tail];
        items[tail] = null;
        if (!isEmpty()) {
            size--;
        }
        return removeItem;
    }

    /*
        Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
        If no such item exists, returns null. Must not alter the deque
     */
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        int begin = plusOne(head);
        return items[(begin + index) % items.length];
    }

    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        System.arraycopy(items, 0, a, 0, size);
        items = a;
        head = items.length - 1;
        tail = size;
    }

    private int plusOne(int x) {
        return (x + 1) % items.length;
    }

    private int minusOne(int x) {
        return (x - 1 + items.length) % items.length;
    }
}
