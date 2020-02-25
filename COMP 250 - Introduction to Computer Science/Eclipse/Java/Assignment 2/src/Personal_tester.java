import java.util.Stack;

public class Personal_tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*
		StringBuilder doodoo = new StringBuilder();
		doodoo.append("I'll be your record player");
		System.out.println(doodoo.toString());
		
		doodoo.delete(0, doodoo.length());
		System.out.println(doodoo.toString() + "\n" + doodoo.length());
		*/
		
		//Expression e = new Expression("(1(222222+98))");
		//Expression e = new Expression("((11 * (22 * (33))) / ((10 +1) * ((23 - 1) * (66 / 2))))");
		Expression e = new Expression("(++(--[((2 * 3) - ([(2 - 18)]))]))");
		
		/*
		System.out.println(e.toString());
		
		Stack<String> st = new Stack<String>();
		st.push("lution");
		st.push("evo");
		st.push(st.pop()+st.pop());
		System.out.println(st.peek());
		
		System.out.println((int)'+');
		System.out.println((int)'-');
		System.out.println((int)'*');
		System.out.println((int)'/');
		*/
		System.out.println(e.eval());
		/*
		String s = "moonshiners with whiskey breath";
		for(char c: s.toCharArray())
			System.out.println(c);
		 */
	}

}
