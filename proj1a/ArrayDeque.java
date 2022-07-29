public class ArrayDeque<T> {
    // constant
    private static final int INI_SIZE = 8;

    // instances
    private T[] item;
    private int nextFirst;
    private int nextLast;
    private int size;

    public ArrayDeque() {
        item = (T[]) new Object[INI_SIZE];
        nextFirst = item.length - 1;
        nextLast = 0;
        size = 0;
    }

    public void addFirst(T itemToAdd) {
        // addFirst有可能到了index 0 的時候拐回去尾巴
        // （如果不斷addLast再rmFirst,first就會跑到前面去。）
        if (isFull()) {
            resize();
        }

        item[nextFirst] = itemToAdd;
        size++;

        if (nextFirst == 0) {
            nextFirst = item.length - 1;
        } else {
            nextFirst--;
        }
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        nextFirst = (nextFirst + 1) % item.length;

        T storeValue = item[nextFirst];
        item[nextFirst] = null;
        size--;

        if (needResize()) {
            resize();
        }
        return storeValue;
    }

    public void addLast(T itemToAdd) {
        if (isFull()) {
            resize();
        }

        item[nextLast] = itemToAdd;
        size++;

        nextLast = (nextLast + 1) % item.length;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }

        if (nextLast == 0) {
            nextLast = item.length - 1;
        } else {
            nextLast--;
        }

        T storeValue = item[nextLast];
        item[nextLast] = null;
        size--;

        if (needResize()) {
            resize();
        }

        return storeValue;
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }
        return item[(nextFirst + 1 + index) % item.length];
    }

    // helper method
    public int size() {
        return size;
    }

    public void printDeque() {
        for (int x = 0; x < size; x++) {
            System.out.println(item[(nextFirst + 1 + x) % size] + " ");
        }
    }

    private void resize() {
        if (isFull()) {
            resize(item.length * 2);
        }

        if (needResize()) {
            resize(item.length / 2);
        }
    }
    private void resize(int capacity) {
        T[] newList = (T[]) new Object[capacity];

        for (int x = 0; x < size; x++) {
            newList[x] = item[(nextFirst + 1 + x) % capacity];
        }

        item = newList;
        nextLast = size;
        nextFirst = capacity - 1;
    }

    private boolean isFull() {
        return (size == item.length);
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    private boolean needResize() {
        return (item.length > 8
                && (size / item.length) < (1 / 4));
    }

}
