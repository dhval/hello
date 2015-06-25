package sample.algo.ds;

import java.util.Random;

/**
 * Implement a queue using two stacks.
 * 
 * https://www.interviewcake.com/question/queue-two-stacks
 **/

public class StackQueue {

    private Stack < Integer > s1 = new Stack < > ();
    private Stack < Integer > s2 = new Stack < > ();

    private static Random rand = new Random();

    private static int countPush = 0;
    private static int countPop = 0;

    private void enqueue(Integer item) {
        s1.push(item);
        countPush++;
    }

    private Integer dequeue() {
        countPop++;
        if (!s2.isEmpty()) {
            return s2.pop();
        }
        else {
            Integer item = s1.pop();
            while (!s1.isEmpty()) {
                s2.push(item);
                item = s1.pop();
                countPop++;
            }
            return item;
        }
    }

    public static void main(String[] s) {
        StackQueue impl = new StackQueue();

        System.out.println("LIFO - Since we enter in increasing order we must dequeue in same order.");

        for (int i = 0; i < 25;) {
            Boolean bool = rand.nextInt(10) < 3;
            if (bool) {
                impl.enqueue(i++);
            }
            else {
                System.out.format(" %d - ", impl.dequeue());
            }
        }

        System.out.println("\nPop #" + countPop);
        System.out.println("Push #" + countPush);
    }

}