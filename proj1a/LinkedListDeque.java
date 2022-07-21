public class LinkedListDeque<T> implements List<T> {

    private class Node<T> {
        Node<T> prev;
        T data;
        Node<T> next;

        public Node(Node<T> p, T d, Node<T> n) {
            this.prev = p;
            this.data = d;
            this.next = n;
        }

        public Node(T d) {
            this(null,d,null);
        }

        public Node<T> getPrev() {
            return this.prev;
        }

        public Node<T> getNext() {
            return this.next;
        }

        public T getData() {
            return this.data;
        }

        public void setPrev(Node<T> newP){
            this.prev = newP;
        }

        public void setNext(Node<T> newN) {
            this.next = newN;
        }

        public void setData(T newData) {
            this.data = newData;
        }
    }

    private Node<T> sentinel;
    private int size;

    public LinkedListDeque() {
        this.sentinel = new Node<>(null,(T) "null",null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        Node<T> newNode = new Node<>(item);
        if (this.size == 0){
            this.addAtEmptyList(newNode);
        }
        else {
            Node<T> current = this.sentinel.getNext();
            this.sentinel.setNext(newNode);
            newNode.setPrev(this.sentinel);
            newNode.setNext(current);
            current.setPrev(newNode);
        }
        this.size++;
    }

    @Override
    public void addLast(T item) {
        Node<T> newNode = new Node<>(item);
        if (item == null) {
            throw new IllegalArgumentException();
        }

        Node<T> current = this.sentinel.getPrev();
        current.setNext(newNode);
        newNode.setPrev(current);
        newNode.setNext(this.sentinel);
        this.sentinel.setPrev(newNode);

        this.size++;
    }

    @Override
    public boolean isEmpty() {
        return (this.size == 0);
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void printDeque() {
        Node<T> current = this.sentinel.getNext();

        while (current != sentinel) {
            System.out.print(current.getData()+ " ");
            current = current.getNext();
        }
        System.out.println(" ");

    }

    @Override
    public T removeFirst() {
        Node<T> current = this.sentinel.getNext();

            current = current.getNext();
            current.setPrev(this.sentinel);
            this.sentinel.setNext(current);
            this.size--;

        return current.getData();
    }

    @Override
    public T removeLast() {
        Node<T> prevNode = this.sentinel.getPrev();
        prevNode = prevNode.getNext();
        prevNode.setNext(this.sentinel);
        sentinel.setPrev(prevNode);
        this.size--;
        return prevNode.getData();
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > this.size - 1) {
            throw new IndexOutOfBoundsException("Index is out of bounds.");
        }

        if (index == 0) {
            return this.getFirst().getData();
        }

        else if (index == this.size - 1) {
            return this.getLast().getData();
        }

        else {
            Node<T> current = this.sentinel.getNext();
            int x = 0;
            while (x < index) {
                current = current.getNext();
                x++;
            }
            return current.getData();
        }
    }

    public T getRecursive(int index) {
        if (index > size - 1) {
            return null;
        }
        return recursiveHelper(sentinel.next,index);
    }

    private T recursiveHelper(Node<T> n, int i) {
        if (i == 0) {
            return n.getData();
        }
        return recursiveHelper(n.getNext(), i-1);
    }

    // helper method
    private void addAtEmptyList(Node<T> d) {
        d.setPrev(sentinel);
        d.setNext(sentinel);
        sentinel.setNext(d);
        sentinel.setPrev(d);
    }


    public Node<T> getFirst() {
        return this.sentinel.next;
    }

    public Node<T> getLast() {
        return this.sentinel.prev;
    }

}
