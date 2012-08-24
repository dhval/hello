package alg.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import alg.Node;

public class TreeUtil {

    public static void printTree(final Node<Character> root) {
	final int height = heightTree(root);
	final double maxLeafNode = Math.pow(2, height - 1);
	final int width = ((int) maxLeafNode) * 2 - 1;
	final char chArray[][] = new char[height][width];

	System.out.println(" Printing a tree height # " + height + " max size #" + width);

	printTree(root, chArray, height - 2, 0, width / 2);

	for (int x = 0; x < height; x++) {
	    for (int y = 0; y < (width); y++) {
		final char ch = (chArray[x][y] == '\u0000') ? '-' : chArray[x][y];
		System.out.print(ch + " ");
	    }
	    System.out.print("\n");
	}

    }

    public static void printTree(final Node<Character> root, final char ch[][], final int h, final int x, final int y) {
	if (root == null)
	    return;
	ch[x][y] = root.data;
	final int width = (int) Math.pow(2, h);
	printTree(root.left, ch, h - 1, x + 1, y - width);
	printTree(root.right, ch, h - 1, x + 1, y + width);
    }

    public static <T> int heightTree(final Node<T> root) {
	if (root == null)
	    return 0;
	final int heightL = heightTree(root.left);
	final int heightR = heightTree(root.right);
	final int height = (heightL > heightR) ? heightL + 1 : heightR + 1;
	return height;
    }

    public static <T> void inOrder(final Node<T> root, final List<T> result) {
	if (result == null || root == null)
	    return;
	inOrder(root.left, result);
	result.add(root.data);
	inOrder(root.right, result);
    }

    public static Node<Character> giveATree(final List<Character> list) {
	if (list == null)
	    return null;
	Collections.sort(list);

	final Character chArray[] = list.toArray(new Character[list.size()]);
	return createTree(chArray, 0, chArray.length - 1);

    }

    private static Node<Character> createTree(final Character chArray[], final int l, final int h) {
	if (l > h)
	    return null;
	final int mid = l + (h - l) / 2;
	final Node<Character> root = new Node<Character>(chArray[mid]);
	root.left = createTree(chArray, l, mid - 1);
	root.right = createTree(chArray, mid + 1, h);
	return root;
    }

    public static void main(final String[] args) {
	final int size = 16;
	Character ch = 'A';
	final List<Character> chList = new ArrayList<Character>();
	for (int i = 0; i < size; i++, ch++) {
	    chList.add(ch);
	}
	final Node<Character> root = giveATree(chList);

	printTree(root);
    }

}
