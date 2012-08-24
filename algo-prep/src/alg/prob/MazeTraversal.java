// =============================================================================
//  Traverse a Maze 
//
//  UP, Down R x C
//
//  Path (x, y) = Path(x-1, y) + Path(x, y-1)
// =============================================================================
package alg.prob;

public class MazeTraversal {

    public static void main(final String[] args) {
	traverse(8, 8);
	System.out.println(counter);
	// 16c8

    }

    private static int counter = 0;

    private static void traverse(final int r, final int c) {
	if (r == 0 && c == 0) {
	    counter++;
	    return;
	}
	;
	if (r > 0) {
	    traverse(r - 1, c);
	}
	if (c > 0) {
	    traverse(r, c - 1);
	}

    }

    private static void traverseDiagonal(final int r, final int c) {
	if (r == 0 && c == 0) {
	    counter++;
	    return;
	}
	;
	if (r > 0) {
	    traverseDiagonal(r - 1, c);
	}
	if (c > 0) {
	    traverseDiagonal(r, c - 1);
	}
	if (r > 0 && c > 0) {
	    traverseDiagonal(r - 1, c - 1);
	}

    }
}
