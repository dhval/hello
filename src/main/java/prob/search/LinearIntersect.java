package prob.search;
/**
 * A4I:  1.7 - Array Intersection when
 * 
 * Two arrays are app. same size
 * 
 * @author c-dmudawal
 *
 */
public class LinearIntersect {
	
public static int[] array1 = {2,4,5,6,8,9,11,23,43,43,67};
public static int[] array2 = {2,3,4,6,8,9,11,12,18,23,25,32,40,43,43,67,81,90};

	
	public static void main(String[] args) {
		int lastMatch1 = 0;
		int lastMatch2 = 0;
		
		while(lastMatch1 < array1.length && lastMatch2 < array2.length) {
			
			if (array1[lastMatch1] == array2[lastMatch2]) {
				System.out.println(array1[lastMatch1]);
				lastMatch1++; lastMatch2++;
			} else if (array1[lastMatch1] < array2[lastMatch2]) {
				lastMatch1++;
			} else {
				lastMatch2++;
			}
			
		}
		
		
	}

}
