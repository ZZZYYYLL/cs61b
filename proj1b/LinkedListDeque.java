public class LinkedListDeque<T> implements Deque<T> {

    private class Node {
        private T item;
        private Node prev;
        private Node next;

        private Node(T x, Node p, Node n) {
            item = x;
            prev = p;
            next = n;
        }

    }

    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    // Adds an item of type T to the front of the deque
    public void addFirst(T item) {
        sentinel.next = new Node(item, sentinel, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size++;
    }

    //Adds an item of type T to the back of the deque
    public void addLast(T item) {
        sentinel.prev = new Node(item, sentinel.prev, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size++;
    }

    //Returns true if deque is empty, false otherwise
    public boolean isEmpty() {
        return size == 0;
    }

    //Returns the number of items in the deque
    public int size() {
        return size;
    }

    //Prints the items in the deque from first to last, separated by a space
    public void printDeque() {
        Node beginNode = sentinel.next;
        for (int i = 0; i < size; i++) {
            System.out.print(beginNode.item + " ");
            beginNode = beginNode.next;
        }
        System.out.println();
    }

    //Removes and returns the item at the front of the deque. If no such item exists, returns null
    public T removeFirst() {
        T removeItem = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        if (!isEmpty()) {
            size--;
        }
        return removeItem;
    }

    //Removes and returns the item at the back of the deque. If no such item exists, returns null
    public T removeLast() {
        T removeItem = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
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
        Node beginNode = sentinel.next;
        if (index + 1 > size) {
            return null;
        }
        for (int i = 0; i < index; i++) {
            beginNode = beginNode.next;
        }
        return beginNode.item;
    }

    private T getRecursive(int index, Node rest) {
        if (index == 0) {
            return rest.item;
        }
        return getRecursive(index - 1, rest.next);
    }

    public T getRecursive(int index) {
        return getRecursive(index, sentinel.next);
    }

}

