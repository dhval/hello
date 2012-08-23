package alg.search;

public class BinarySearch {
	
	public static int[] array = {2,4,6,8,9,11,23,43,43,67};

	
	public static void main(String[] args) {
		
		int find = 23;
		int low = 0;
		int high = array.length -1;
		do {
			System.out.println(low + " " + high);
			int mid = (low + high) / 2;
			if (array[mid] == find) {System.out.print(mid); break;}
			else if (array[mid] < find) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
			
		} while (low <= high);
		
		
	}
	
}
