package alg.prob;

/**
 * 
 * 90 degree = > n = N-1
 * 
 * x2 = y1, y2 = n-x
 * 
 * 
 * Reflection = >
 * 
 * x2 = n-y1, y2 = n-x1
 * 
 * @author Dhval
 */
public class ArrayRotate {

    private static final int N = 5;

    public static void main(final String[] args) {
	final char array[][] = new char[N][N];
	int count = 0;
	final char ch = 'A';
	for (int x = 0; x < N; x++) {
	    for (int y = 0; y < N; y++) {
		array[x][y] = (char) (ch + (count++));
		System.out.print(array[x][y] + " ");
	    }
	    System.out.print("\n");
	}
	System.out.print("\n\n\n");
	rotateClockWise(array);

    }

    public static void rotateClockWise(final char[][] array) {
	for (int x = 0; x < N; x++) {
	    for (int y = 0; y < N; y++) {
		System.out.print(array[N - 1 - y][x] + " ");
	    }
	    System.out.print("\n");
	}
    }
}
