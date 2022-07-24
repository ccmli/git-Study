

public class ArrayDequeTest {


    public static void getTest() {

        ArrayDeque<String> input = new ArrayDeque<>();
        input.addFirst("2");
        input.addLast("3");
        input.addFirst("B");
        input.addLast("Last item");

        String output = input.get(2);
        System.out.println(output);
        String expected = "3";


    }

    public static void removeTest() {

        ArrayDeque<String> input = new ArrayDeque<>();
        input.addFirst("2");
        input.addLast("3");
        input.addFirst("B");
        input.addLast("Last item");

        System.out.println(input.removeFirst());
        System.out.println(input.get(0));
        System.out.println(input.removeLast());
        System.out.println(input.get(1));
    }


    public static void resizeTest() {
        ArrayDeque<Integer> resizeList = new ArrayDeque<>();
        int x = 0;

        while (x < 5000) {
            resizeList.addFirst(x);
            x++;
        }
        System.out.println(resizeList.size());

        while (x > 1) {
            resizeList.removeLast();
            x--;
        }
        System.out.println(resizeList.size());

        while (x < 400) {
            resizeList.addLast(x);
            x++;
        }
        System.out.println(resizeList.size());

    }

    public static void printDequeTest() {
        ArrayDeque<String> input = new ArrayDeque<>();
        input.addFirst("2");
        input.addLast("3");
        input.addFirst("B");
        input.addLast("Last item");
        input.addFirst("Hi");
        input.addLast("I m Lili");

        input.printDeque();
        System.out.println(input.size());

    }

    public static void Test() {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        arrayDeque.addLast(0);
        arrayDeque.addLast(1);
        arrayDeque.addLast(2);
        arrayDeque.addLast(3);
        System.out.println(arrayDeque.removeFirst());
        arrayDeque.addLast(5);
        arrayDeque.addLast(6);
        arrayDeque.addLast(7);
        arrayDeque.addLast(8);
        arrayDeque.addLast(9);
        System.out.println(arrayDeque.get(0));
        System.out.println(arrayDeque.removeFirst());
    }
    public static void main(String[] args) {
        Test();
    }
}
