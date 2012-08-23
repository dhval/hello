package alg.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Node implements Comparable<Node>{
	
	protected char data;
	protected Node left;
	protected Node right;
	protected Node rand;
	private int size;
	
	public Node(char data) {
		super();
		this.data = data;
		left = right = null;
	}

	public Node(char[] data) {
		super();
		Arrays.sort(data);
		size = data.length;
		int mid = (data.length)/2;
		this.data = data[mid];
		left = createTree(data, 0, mid-1);
		right = createTree(data, mid+1, data.length-1);
	}

	public List<Character> asList() {
		List<Character> nodes = new ArrayList<Character>();
		inOrder(this, nodes);
		return nodes;
	}
	
	
	public String toString() {
		return this.asList().toString();
	}
	
	private Node createTree(char[] data, int l, int h) {
		int mid = (h+l)/2;
		if (mid < 0) return null;
		Node tmp = new Node(data[mid]);
		if (mid-l > 0) {tmp.left = createTree(data, l, mid-1);}	
		if (h-mid > 0) {tmp.right = createTree(data, mid+1, h);}
		return tmp;
	}
	
	/**
	 * Use InOrder Traversal to Print
	 * @param root
	 */
	public void inOrder(Node root, List<Character> nodes) {	
		if (root == null) {
			return;
		}
		inOrder(root.left, nodes);
		nodes.add(root.data);
		inOrder(root.right, nodes);		
	}

	@Override
	public int compareTo(Node o) {
		return data - o.data;
	}

}
