package sandbox;

public class ArrayMax {
	public static void main(String[] arg) {
		int[] n = {4,7,3,9,6,4,12,5,18};

		int max = -1;

		for (int i = 0 ; i < n.length-1 ; i++){

			if (n[i]>max){

				max = n[i];

			}

		}
		System.out.println(max);
	}

	public static void poopoo() {
		return 0;
		return 1;

	}
}
