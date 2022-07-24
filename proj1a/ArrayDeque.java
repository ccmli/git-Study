public class ArrayDeque<T> {
    private T[] aList;
    private int nextFirst;
    private int nextLast;
    private int size;
    private final int INITIALSIZE = 8;
    private int firstSize;
    private int lastSize;
    private final double NARROWRATIO = 1 / 4;

    public ArrayDeque() {
        aList = (T[]) new Object[INITIALSIZE];
        nextFirst = aList.length - 1;
        nextLast = 0;
        size = 0;
    }

    public void addFirst(T item) {
        if (size == aList.length - 1) {
            resize();
        }

        aList[nextFirst] = item;

        if (nextFirst - 1 < -1) {
            nextFirst = aList.length - 1;
        } else {
            nextFirst--;
        }

        firstSize++;
        size++;
    }

    public void addLast(T item) {
        if (size == aList.length - 1) {
            resize();
        }

        aList[nextLast] = item;
        if (nextLast + 1 >= aList.length) {
            nextLast = 0;
        } else {
            nextLast++;
        }

        lastSize++;
        size++;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        if (size < 0) {
            return 0;
        }
        return size;
    }

    public void printDeque() {
        for (int x = nextFirst + 1; x < aList.length; x++) {
            System.out.print(aList[x] + " ");
        }
        for (int x = 0; x < nextLast; x++) {
            System.out.print(aList[x] + " ");
        }
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }

        // 將 nextFirst移動到想刪除的項目
        if (nextFirst + 1 >= aList.length) {
            nextFirst = 0;
        } else {
            nextFirst++;
        }


        T storeData = aList[nextFirst];
        aList[nextFirst] = (T) null;
        this.size--;
        if (firstSize > 0) {
            firstSize--;
        } else {
            lastSize--;
        }

        // 檢查數組使用空間是否少於25%
        if (aList.length > INITIALSIZE
            && currentUsageRatio() < NARROWRATIO) {
            resize();
        }

        return storeData;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }

        if (nextLast - 1 == -1) {
            nextLast = aList.length - 1;
        } else {
            nextLast--;
        }

        T storeData = aList[nextLast];
        aList[nextLast] = (T) null;
        this.size--;

        if (lastSize > 0) {
            lastSize--;
        } else {
            firstSize--;
        }

        if (aList.length > INITIALSIZE
                && currentUsageRatio() < NARROWRATIO) {
            resize();
        }
        return storeData;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }

        if (firstSize == 0) {
            return aList[index];
        } else if (index < this.firstSize) {
            return aList[nextFirst + index + 1];
        }
        return aList[index - firstSize];
    }

    private void resize() {
        if (this.size == aList.length - 1) {
            resize(aList.length * 2);
        } else if (aList.length > INITIALSIZE
                && currentUsageRatio() < NARROWRATIO) {
            resize(aList.length / 2);
        }

    }

    private void resize(int capacity) {
        // 將整個舊alist copy到頭，nextLast = size - 1 ; nextFirst = newlist.length - 1;
        T[] newList = (T[]) new Object[capacity];

        System.arraycopy(aList,nextFirst + 1, newList, 0, firstSize);

        System.arraycopy(aList, 0, newList, firstSize, lastSize);

        aList = newList;
        nextFirst = capacity - 1;
        nextLast = size;

        lastSize = size;
        firstSize = 0;
    }

    private double currentUsageRatio() {
        return size / aList.length;
    }

}
