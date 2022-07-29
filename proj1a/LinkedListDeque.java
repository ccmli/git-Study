public class LinkedListDeque<T> {
    private class Node {
        Node prev;
        T item;
        Node next;

        public Node(Node p, T d, Node n) {
            this.prev = p;
            this.item = d;
            this.next = n;
        }

        public Node(T d) {
            this(null, d, null);
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public void addFirst(T item) {
        Node newNode = new Node(sentinel, item ,sentinel.next);

        if (isEmpty()) {
            sentinel.prev = newNode;
        } else {
            sentinel.next.prev = newNode;
        }
        sentinel.next = newNode;
        size++;
    }

    public void addLast(T item) {
        Node newNode = new Node(sentinel.prev, item, sentinel);

        if (isEmpty()) {
            addFirst(item);
         } else {
            sentinel.prev.next = newNode;
            sentinel.prev = newNode;
        }
        this.size++;
    }

    public void printDeque() {
        Node current = sentinel.next;

        while (current != sentinel) {
            System.out.print(current.item);
            System.out.print(" ");
            current = current.next;
        }
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        } else {
            T storeItem = sentinel.next.item;
            sentinel.next = sentinel.next.next;
            sentinel.next.prev = sentinel;
            size--;
            return storeItem;
        }
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        } else {
            T storeItem = sentinel.prev.item;
            sentinel.prev = sentinel.prev.prev;
            sentinel.prev.next = sentinel;
            size--;
            return storeItem;
        }
    }

    public T get(int index) {
        if (index < 0 || index > this.size - 1) {
            throw new IndexOutOfBoundsException("Index is out of bounds.");
        }

        if (index == 0) {
            return sentinel.next.item;
        } else if (index == this.size - 1) {
            return sentinel.prev.item;
        } else {
            Node current = sentinel.next;
            int x = 0;
            while (x < index) {
                current = current.next;
                x++;
            }
            return current.item;
        }
    }

    public T getRecursive(int index) {
        if (index == size - 1) {
            return sentinel.prev.item;
        }
        return getRecursive(sentinel.next, index);
    }

    private T getRecursive(Node n, int i) {
        if (i == 0) {
            return n.item;
        }
        return getRecursive(n.next, i - 1);
    }

    public boolean isEmpty() {
        return (this.size == 0);
    }

    public int size() {
        return this.size;
    }
    
}
