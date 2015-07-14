package prob.count;

/**
 * http://www.programcreek.com/2015/03/rotate-array-in-java/
 * http://www.geeksforgeeks.org/array-rotation/
 **/ 

public class ArrayRotate {

Integer[] create(int size) {
    Integer A[] = new Integer[size];
    for(int i=0; i<A.length; A[i]=i++);
    return A;
}

void print(Integer A[]) {
    for(int i=0; i<A.length; System.out.print(A[i++] + " "));
    System.out.println();
}

// Reverse an array from i (=0) to j (=n-1)

void reverse(Integer A[], int i, int j) {
    if ( i>A.length || i<0 )
        throw new IllegalArgumentException("Invalid start");
    if ( j>A.length || j<0 || j<i )
        throw new IllegalArgumentException("Invalid end");
    while(i<j) {
        int tmp = A[i];
        A[i++] = A[j];
        A[j--] = tmp;
    }    
}

/**
 * Find array rotation using binary search. 
 * Additionally find maximum number in a sorted and rotated array. 
 **/
 
int findRotation(Integer A[]) {
    int size = A.length-1;
    if( A[0]<=A[size] )
        return 0;
    int i=0, j=size;
    while(i<j) {
        int m = i + (j-i)/2;
        if (A[m] > A[m+1])
            return m;
        if (A[0]<=A[m]) { // rotated in right half
            i = m+1;
        } else {
            j = m;
        }
    }
    return i;
} 

/**
 * Rotate an array in O(n) time and O(1) space.
 **/ 
Integer[] rotate(Integer A[], int k) {
    if ( k<=0 || k>=A.length-1 )
        return A;
    reverse(A, 0, k-1);
    reverse(A, k, A.length-1);
    reverse(A, 0, A.length-1);
    return A;
}


public static void main(String [] s) {
    ArrayRotate instance = new ArrayRotate();
    Integer A[] = instance.create(10);
    instance.rotate(A, 5);
    instance.print(A);
    int rotatedPos = instance.findRotation(A);
    System.out.println(rotatedPos + " = " + A[instance.findRotation(A)]);
}
}