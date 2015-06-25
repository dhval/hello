package sample.algo;

import java.util.Random;

/**
 *
 * @author Dhval
 */
public class Utility {

    /**
     * 
     * @return A random integer array. 
     */
    public static Integer[] randArray(int len) {
        Integer[] A = new Integer[len];
        Random rand = new Random();
        for (int i = 0; i < len; i++) {
            A[i] = rand.nextInt(len + 1);
        }
        return A;
    }

    /**
     * 
     * @param A, array to be swapped
     * @param i, index of first element
     * @param j, index of second element 
     */
    public static void swap(Integer[] A, int i, int j) {
        if (i == j) {
            return;
        }
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }

    /**
     * Partion the array at k'th element. 
     * Store bigger elements towards the end. Partition element(s) toward begining.
     * 
     **/
    public static void partition(Integer[] A, int k) {
        int el = k;
        int i = 0, start = 0, end = A.length - 1;
        while (i <= end) {
            int current = A[i];
            if (current > el) {
                swap(A, i, end--);
            }
            else if (current == el) {
                swap(A, i++, start++);
            }
            else {
                i++;
            }
        }
        for (i = 0; i < start; i++)
            swap(A, i, end--);
    }

    /**
     * Print a list of nodes.
     **/
    public static < E > void print(Node < E > head) {
        System.out.format("\nHead-%s\n", head.data);
        int counter = 0;
        while (head != null && counter++ < 50) {
            System.out.format("%s->", head.data);
            head = head.next;
        }
        System.out.println("\t Total#" + counter);
    }

    /**
     * Create a list of nodes from array.
     **/
    public static <E> Node < E > build(E[] array) {
        Node < E > last = null, head = null;
        for (E i: array) {
            Node < E > node = new Node < > ();
            node.data = i;
            node.next = null;
            if (head == null)
                head = node;
            if (last != null)
                last.next = node;
            last = node;
        }
        return head;
    }

}
