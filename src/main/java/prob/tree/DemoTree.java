package prob.tree;

import java.util.*;
import static prob.tree.TreeUtil.*;

public class DemoTree {

	private static char[] array = "ABCDEFGHIJKL".toCharArray();


	public static void main(String[] arg) {
		final int size = 16;
		Character ch = 'A';
		final List < Character > chList = new ArrayList < Character > ();
		for (int i = 0; i < size; i++, ch++) {
			chList.add(ch);
		}
		final Node < Character > root = giveATree(chList);
		printTree(root);
		//
	}

}
