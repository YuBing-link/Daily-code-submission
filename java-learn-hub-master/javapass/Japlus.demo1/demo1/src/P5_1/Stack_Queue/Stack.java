package P5_1.Stack_Queue;

import java.util.LinkedList;

public class Stack {
    private static LinkedList<Integer> stack=new LinkedList<>();

    public static void main(String[] args) {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack);
        stack.pop();
        System.out.println(stack);
        stack.addFirst(5);
        System.out.println(stack);
        stack.removeFirst();
        System.out.println(stack);

    }
}
