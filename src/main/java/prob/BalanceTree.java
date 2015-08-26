package prob;

import java.util.*;
import prob.tree.Node;
import static prob.tree.TreeUtil.*;

public class BalanceTree {

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