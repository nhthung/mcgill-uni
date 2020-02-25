package spoopy;

import java.util.ArrayList;
import java.util.List;

public class Test {
	
	public static void main(String[] arg) {
		int digit_sum = 3;
		System.out.println((int)3.9);
		System.out.println(digit_sum/2);
		System.out.println(6%3);
		System.out.println((-2)%3);
		
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		
		System.out.println(list.get(2));
		System.out.println(list.size());
	}
}
