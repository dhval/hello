package prob;

public class AllPerm {

	static int arrayOrigin[] = { 1, 2, 3};

	public static void main(String[] args) {
		nextPerm(0, 0);

	}

	public static void nextPerm(int number, int start) {
		if (start >= arrayOrigin.length) {
			System.out.println(number);
			return;
		}
		
		for(int i =0; i < arrayOrigin.length; i++) {
			int value = arrayOrigin[i];
			int nextNumber =  number * ((int) Math.pow(10, start)) + value;
			nextPerm(nextNumber, start+1);
		}

	}

}
