package sample.algo.ds;

import sample.algo.Utility;
import java.util.Arrays;
import sample.algo.Node;

public class ListReverse {

    /**
     * Reverse a singly linked list.
     **/
    static Node < Integer > reverseList(Node < Integer > head) {
        if (head == null || head.next == null)
            return head;
        Node < Integer > node = head.next;
        // Must set this else would form a cycle.
        head.next = null;
        while (node != null) {
            Node < Integer > tmp = node.next;
            node.next = head;
            head = node;
            node = tmp;
        }
        return head;
    }
    
    public static Node<Integer> recursiveList(Node <Integer> start, Node<Integer> head) {
        if (head == null)
            return start;
        Node<Integer> node = head.next;
        head.next = start;
        return recursiveList(head, node);
    }

    public static void main(String[] s) {
        Integer[] array = Utility.randArray(10);
        System.out.println(Arrays.toString(array));

        Node < Integer > head = Utility.build(array);
        Utility.print(head);
        head = reverseList(head);
        Utility.print(head);
        head = recursiveList(null, head);
        Utility.print(head);
    }

}