package prob.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Node<T> implements Comparable<Node<T>> {

    protected T data;
    protected Node<T> left;
    protected Node<T> right;
    protected Node<T> rand;
    private int size;

    public Node(final T data) {
	super();
	this.data = data;
	left = right = null;
    }

    public Node(final T[] data) {
	super();
	Arrays.sort(data);
	size = data.length;
	final int mid = (data.length) / 2;
	this.data = data[mid];
	left = createTree(data, 0, mid - 1);
	right = createTree(data, mid + 1, data.length - 1);
    }

    public List<T> asList() {
	final List<T> nodes = new ArrayList<T>();
	inOrder(this, nodes);
	return nodes;
    }

    @Override
    public String toString() {
	return this.asList().toString();
    }

    private Node<T> createTree(final T[] data, final int l, final int h) {
	final int mid = (h + l) / 2;
	if (mid < 0)
	    return null;
	final Node<T> tmp = new Node<T>(data[mid]);
	if (mid - l > 0) {
	    tmp.left = createTree(data, l, mid - 1);
	}
	if (h - mid > 0) {
	    tmp.right = createTree(data, mid + 1, h);
	}
	return tmp;
    }

    /**
     * Use InOrder Traversal to Print
     * 
     * @param root
     */
    public void inOrder(final Node<T> root, final List<T> nodes) {
	if (root == null) {
	    return;
	}
	inOrder(root.left, nodes);
	nodes.add(root.data);
	inOrder(root.right, nodes);
    }

    @Override
    public int compareTo(final Node<T> o) {
	if (o instanceof Node<Character>) {
	    return data - o.data;
	}
    }

}
