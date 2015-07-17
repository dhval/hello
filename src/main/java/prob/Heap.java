package prob;

import java.util.*;
import static prob.Utility.*;

/**
 *
 *
 **/

public class Heap {
	static int HEIGHT = 4;
	static int SIZE = (int) Math.pow(2, HEIGHT + 1) - 1;
	static int LEAF_NODE = (int) Math.pow(2, HEIGHT) - 1;
	static int DIGIT_SIZE = (int) Math.log10(SIZE) + 1;

	// We are using array from 0
	static int parent(int i) {
		return (i - 1) / 2;
	}

	// returns left child
	static int child(int i) {
		return 2 * (i + 1) - 1;
	}

	// size of text in bottom row assuming we separate two digit word by two digit space e.g. 00  01  02  03
	// in (0) --> (0   ), (45) --> (45  )
	static int LEAF_ROW_SIZE = ((int) Math.pow(2, HEIGHT)) * (2 * DIGIT_SIZE);
	static String pad(int n) {
		String str = String.format("%0" + DIGIT_SIZE + "d", n);
		return String.format("%-" + 2 * DIGIT_SIZE + "s", str);
	}

	static void print(Integer[] A, int level) {
		if (level > HEIGHT) return;
		int count = (int) Math.pow(2, level);
		int width = LEAF_ROW_SIZE / (count + 1);
		StringBuilder sb = new StringBuilder();
		// System.out.println(width + " " + level);
		for (int i = count - 1; i < 2 * count - 1; i++) {
			String str;
			str = String.format("%" + width + "s", pad(A[i]));
			//for(int j=0; j<width; j++) 	sb.append("-");
			sb.append(str);
		}
		System.out.println(sb.toString());
		print(A, level + 1);
	}

	static void heapify(Integer[] A, int i) {
		// assuming left & right sub tree of A(i) satify heap property
		if (i >= LEAF_NODE) return;
		int left = child(i);
		int right = left + 1;
		// debugger(i);
		if (A[left] > A[right]) {
			if (A[i] < A[left]) swap(A, i, left);
			heapify(A, left);
		}
		else {
			if (A[i] < A[right]) swap(A, i, right);
			heapify(A, right);
		}
	}

	static void sort(Integer[] A) {
		int len = A.length - 1;
		// all leaf nodes satisfy heap property.
		int firstNoLeaf = LEAF_NODE - 1;
		for (int i = firstNoLeaf; i >= 0; i--)
			heapify(A, i);
	}

	public static void main(String[] str) {
		Integer[] A = Utility.sortArray(SIZE);
		print(A, 0);
		sort(A);
		System.out.printf("%" + LEAF_ROW_SIZE / 2 + "s %n", "---- Sorted ----");
		print(A, 0);
	}

}
