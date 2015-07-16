package prob.list;

import java.util.*;

/**
 *
 * @author Amazon
 *
 *         Given two sorted singly linked lists, implement a function to merge
 *         the two lists into a single sorted list and return its head. You may
 *         destroy the original lists if you want.
 *
 *         You may use the JDK or the standard template library. The solution
 *         will be evaluated on correctness, runtime complexity (big-O), and
 *         adherence to coding best practices. A complete answer will include
 *         the following:
 *
 *         Document your assumptions Explain your approach and how you intend to
 *         solve the problem Provide code comments where applicable Explain the
 *         big-O run time complexity of your solution. Justify your answer.
 *         Identify any additional data structures you used and justify why you
 *         used them. Only provide your best answer to each part of the
 *         question.
 *
 */

/**

Assumptions -

Assuming they are sorted, non-cyclic singly link list.

Describe your approach -

Signature head merger(head1, head2)

Check if heads are not null, return non null head.

1. Assign cur_head & head to min(head1, head2)

2. Increment smaller head to head.next

3. if (head1 || head2) is null then point cur_head.next to not null head & return head

4. Assign cur_head.next to min(head1, head2) and increment smaller head

repeat step 3

*/
/*
Enter your Big O Analysis here.

// Running time is m*n where m is size of list1, n is size of list2.
// Since we can destroy the list we need one Node object to store head.

**/

public class Prob1 {

    static Random random = new Random();

    // types must be comparable, to use compareTo method
    static class Node < T extends Comparable < T >> {
        T data;
        Node < T > next;
    }

    // returns head of a sorted list
    static Node < Integer > buildList(int size) {
        // returns null node.
        if (size <= 0) return null;
        int[] tmp = new int[size];
        for (int i = 0; i < size; i++)
            tmp[i] = random.nextInt(1000);
        Arrays.sort(tmp);
        Node < Integer > head = new Node < > ();
        head.data = tmp[size - 1];
        for (int i = size - 2; i >= 0; i--) {
            Node < Integer > node = new Node < > ();
            node.data = tmp[i];
            node.next = head;
            head = node;
        }
        return head;
    }

    static Node < Integer > merge(Node < Integer > head1, Node < Integer > head2) {
        if (head1 == null) return head2;
        if (head2 == null) return head1;
        Node < Integer > head;
        if (head1.data < head2.data) {
            head = head1;
            head1 = head1.next;
        }
        else {
            head = head2;
            head2 = head2.next;
        }
        Node < Integer > cur_head = head;
        while (head1 != null && head2 != null) {
            if (head1.data < head2.data) {
                cur_head.next = head1;
                head1 = head1.next;
            }
            else {
                cur_head.next = head2;
                head2 = head2.next;
            }
            cur_head = cur_head.next;
        }
        if (head1 == null)
            cur_head.next = head2;
        else
            cur_head.next = head1;
        return head;
    }

    static void print(Node < Integer > head) {
        while (head != null) {
            System.out.print(head.data + "->");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node < Integer > head1 = buildList(14);
        Node < Integer > head2 = buildList(17);
        print(head1);
        print(head2);
        Node < Integer > head = merge(head1, head2);
        print(head);
    }

}
