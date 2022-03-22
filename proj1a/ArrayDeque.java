public class ArrayDeque<T> {

    private T[] items;
    private int size;

    //Creates an empty array deque
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
    }

    //Adds an item of type T to the front of the deque
    public void addFirst(T item) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        for (int i = size; i > 0; i--) {
            items[i] = items[i - 1];
        }
        items[0] = item;
        size++;
    }

    // Adds an item of type T to the back of the deque
    public void addLast(T item) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        items[size] = item;
        size++;
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
        for (int i = 0; i < size; i++) {
            System.out.println(items[i] + " ");
        }
        System.out.println();
    }

    //Removes and returns the item at the front of the deque. If no such item exists, returns null
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T removeItem = items[0];
        if (items.length > 16 && (float) (size - 1) / items.length < 0.25) {
            resize(items.length / 2);
        }
        for (int i = 0; i < size - 1; i++) {
            items[i] = items[i + 1];
        }
        items[size - 1] = null;
        size--;
        return removeItem;
    }

    //Removes and returns the item at the back of the deque. If no such item exists, returns null
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T removeItem = items[size-1];
        if (items.length > 16 && (float) (size - 1) / items.length < 0.25) {
            resize(items.length / 2);
        }
        items[size - 1] = null;
        size--;
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
        return items[index];
    }

    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        System.arraycopy(items, 0, a, 0, size);
        items = a;
    }


}
