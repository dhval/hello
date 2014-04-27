package amzn.intv;

/**
 * 
 * @author Dhval
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
 * 
 */

//* Describe your approach here. */

Assuming they are sorted, non-cyclic singly link list.

1. Check if heads are not null. Use a Node object to store "current head".

2. Move forward until, one of list head is null. Make current head point to head (of
   lists) with lowest value, incrementing this list to next node.
    
3. Make current head point to non-empty list and return head    
 
/* Enter your Big O Analysis here. */
    
Running time is m*n where m is size of list1, n is size of list2.
Since we can destroy the list we need one Node object to store head.    
 
/* Enter your code here. */
    
if (head1 == null && head2 == null) return null;
if (head1 == null) return head2;
if (head2 == null) return head1;

// find the list with smallest head.
Node head = null;
if (head1.value <= head2.value) {
    head = head1; 
    head1 = head1.next;
} else {
    head = head2; 
    head2 = head2.next;
}    

while(head1 != null || head2 != null) {
    if (head1.value <= head2.value) {
        head.next = head1; 
        head1 = head1.next;
    } else {
        head.next = head2; 
        head2 = head2.next;
    } 
}

if (head1 != null) {
    head.next = head1;
} else if (head2 != null) {
    head.next = head2;
}

    
    
	
	public static void main(String[] args) {
		
	}
	
}
