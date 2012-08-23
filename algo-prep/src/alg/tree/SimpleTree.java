package alg.tree;

public class SimpleTree {
	
	private static char[] array = "ABCDEFGHIJKL".toCharArray();
	
		
	public static void main(String[] arg) {
		Node tree = new Node (array);
		
		System.out.println(tree);
	}

}
