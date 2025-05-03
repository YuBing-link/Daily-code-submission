package P5_1.Stack_Queue;

import java.util.LinkedList;

public class Queue {
    private static LinkedList<Integer> queue = new LinkedList<>();

    public static void main(String[] args) {
        queue.addLast(1);
        queue.addLast(3);
        queue.addLast(5);
        queue.addLast(7);
        System.out.println(queue);
        queue.removeFirst();
        System.out.println(queue);

    }
}
