package prob;

public class SubArrayMaxSum {

    private static final int ARRAY1[] = { 1, 2, 3, -4, -3, 4, 5, 6, -20, 7 };
    private static final int ARRAY2[] = { -1, -2, -3, -4, -5, 0, -20, -7 };
    private static final int ARRAY3[] = { 1, 2, 3, 4, 5, 6, -20, 1, 2, 3, 4, 5, 6 };
    private static final int ARRAY[] = ARRAY3;

    public static void main(final String[] arg) {

	int maxSum = Integer.MIN_VALUE;
	int curSum = Integer.MIN_VALUE;
	for (int i = 0; i < ARRAY.length; i++) {

	    curSum = (curSum < 0) ? ARRAY[i] : curSum + ARRAY[i];
	    if (maxSum < curSum) {
		maxSum = curSum;
	    }
	}

	System.out.print(maxSum);

    }

}
