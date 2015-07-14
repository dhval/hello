package prob.ds;

import prob.Node;
import java.util.*;

/** 
 * Given pre order ( and in order ) traversal peint the tree.
 **/
public class PrintTree {
    static String PRE_ORDER = "FBADCEGIH";
    // IN_ORDER = "ABCDEFGHI";
    static int HEIGHT = 4;
    // assuming it has max => ( 2^(h+1) -1) + spaces
    static int X = HEIGHT + 1;
    static int Y = ((int) Math.pow(2, HEIGHT + 2)) - 1;
    private static char[][] A = new char[X][Y];

    private static int height = 0;

    /**
     * Find height of tree, tree with one node has height=1
     **/
    static < T > int heightTree(Node < T > node) {
        if (node == null) return 0;
        int height = 1 + Math.max(heightTree(node.left), heightTree(node.right));
        return height;
    }

    /**
     * In a binary search tree, search node with given value exists.
     **/
    static < T extends Comparable < T >> boolean exists(Node < T > node, T data) {
        if (node == null) return false;
        if (node.data == data) return true;
        if (node.data.compareTo(data) > 0) return exists(node.left, data);
        else return exists(node.right, data);
    }

    /**
     * Find a common ancestor.
     **/ 
    static Node<Character> findAncestor(Node<Character> root, Node<Character> node1, Node<Character> node2) {
        Character min = (node1.data>node2.data) ? node2.data : node1.data;
        Character max = (node1.data<node2.data) ? node2.data: node1.data;
        Character data = root.data;
        // when tree is a link list.
        if (root.right == null || root.left == null) return root;
        if (data>min && data<max) return root;
        else if (data>max) {
            return findAncestor(root.left, node1, node2);
        }
        return findAncestor(root.right, node1, node2);
    }

    /**
     * A BFS tree visitor.
     **/
    static void bfs(Node < Character > root) {
        List < Node < Character >> list = new ArrayList < > ();
        list.add(root);
        while (!list.isEmpty()) {
            Node < Character > node = list.remove(0);
            System.out.println(node.data);
            if (node.left != null) list.add(node.left);
            if (node.right != null) list.add(node.right);
        }
    }

    /**
     * Use BFS to print nodes according to their level or depth.
     * Uses two lists for current (popped) and next (pushed) levels.
    **/ 
    static void levelBFS(Node < Character > root) {
        List < Node < Character >> level1 = new ArrayList < > ();
        List < Node < Character >> level2 = new ArrayList < > ();
        level1.add(root);
        int height = 0;
        while (!level1.isEmpty() || !level2.isEmpty()) {
            if (level1.isEmpty()) {
                height++;
                List < Node < Character >> tmp = level1;
                level1 = level2;
                level2 = tmp;
                System.out.println();
            }
            Node < Character > node = level1.remove(0);
            System.out.print(node.data);
            if (node.left != null) level2.add(node.left);
            if (node.right != null) level2.add(node.right);
        }
        System.out.println();
    }

    // split an array into left & right, first element[0] is used as pivot. 
    private static char[][] split(char[] array) {
        char pivot = array[0];
        int k = 1;
        for (; k < array.length && array[k] < pivot; k++);
        char[] left = new char[k - 1];
        char[] right = new char[array.length - k];
        System.arraycopy(array, 1, left, 0, k - 1);
        System.arraycopy(array, k, right, 0, array.length - k);
        return new char[][]{left, right};
    }

    private static void build(Node < Character > node, char[] left, char[] right) {
        System.out.println(node.data + " & h#" + height++);
        if (left.length > 0) {
            Node < Character > nodeLeft = new Node < Character > ();
            node.left = nodeLeft;
            nodeLeft.data = left[0];
            if (left.length > 1) {
                char[][] data = split(left);
                build(nodeLeft, data[0], data[1]);
            }
        }
        if (right.length > 0) {
            Node < Character > nodeRight = new Node < Character > ();
            node.right = nodeRight;
            nodeRight.data = right[0];
            if (right.length > 1) {
                char[][] data = split(right);
                build(nodeRight, data[0], data[1]);
            }
        }
        height--;

    }

    static void print(Node < Character > root, int height, int x) {
        if (root == null)
            return;
        System.out.println(root.data + " " + height + " " + x);
        A[height][x] = root.data;
        int width = ((int) Math.pow(2, HEIGHT - height - 1)) - 1;
        print(root.left, height + 1, x - width);
        print(root.right, height + 1, x + width);
    }

    static void print(char[][] array) {
        for (char[] chArray: array) {
            System.out.println(Arrays.toString(chArray));
        }
    }

    public static void main(String[] s) {
        char[] chArray = PRE_ORDER.toCharArray();
        char[][] data = split(chArray);
        Node < Character > root = new Node < Character > ();
        root.data = chArray[0];
        build(root, data[0], data[1]);
        levelBFS(root);
        int height = heightTree(root);
        int position = ((int) Math.pow(2, height)) - 1;
        print(root, 0, position);
        print(A);
        Character ch1 = 'H';
        Character ch2 = 'I';
        Node<Character> node1 = new Node<>();
        Node<Character> node2 = new Node<>();
        node1.data = ch1;
        node2.data = ch2;
        
        System.out.println(findAncestor(root, node1, node2).data);
    }


}