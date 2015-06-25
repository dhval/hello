package prob.search;

public class FindSQRoot {
	
	static int min = 1;
	static int max = 1 << 16;
	
	public static void main(String[] args) {
		
		int find = 81;
		int low = 2;
		int high = 1 << 16;
		
		while(low <= high) {
			int mid = low + (high-low)/2;
			if (mid*mid == find) {System.out.println(mid); break;}
			else if (mid*mid < find) {low = mid +1;}
			else {high = mid -1;}
		}
		
		
	}

}
