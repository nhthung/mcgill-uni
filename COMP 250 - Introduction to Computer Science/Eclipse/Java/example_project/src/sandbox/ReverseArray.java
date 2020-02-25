package sandbox;

public class ReverseArray {
	public static void main(String[] arg) {
		int[] n = {9,8,7,6,5,4,3,2,1};

		for (int i = 0 ; i < n.length ; i++){

			int tmp = n[n.length - i];

			n[n.length-i] = n[i];

			n[i] = tmp;

		}
	}
}
