
/*

 */

import java.util.Stack;
import java.util.ArrayList;

public class Expression  {
	private ArrayList<String> tokenList;

	//  Constructor    
	/**
	 * The constructor takes in an expression as a string
	 * and tokenizes it (breaks it up into meaningful units)
	 * These tokens are then stored in an array list 'tokenList'.
	 */

	Expression(String expressionString) throws IllegalArgumentException{
		tokenList = new ArrayList<String>();
		StringBuilder token = new StringBuilder();

		//ADD YOUR CODE BELOW HERE
		//..

		for(int i = 0; i < expressionString.length(); i++) {
			if(expressionString.charAt(i) >= '0' && expressionString.charAt(i) <= '9') {
				token.append(expressionString.charAt(i));
				for(int j = i + 1; j < expressionString.length() && expressionString.charAt(j) >= '0' && expressionString.charAt(j) <= '9'; j++) {
					token.append(expressionString.charAt(j));
					i = j;
				}
			}

			else if(expressionString.charAt(i) == '-') {
				token.append(expressionString.charAt(i));
				if(expressionString.charAt(i+1) == '-') {
					token.append(expressionString.charAt(i+1));
					i++;
				}
			}

			else if(expressionString.charAt(i) == '+') {
				token.append(expressionString.charAt(i));
				if(expressionString.charAt(i+1) == '+') {
					token.append(expressionString.charAt(i+1));
					i++;
				}
			}

			else if(expressionString.charAt(i) != ' ' && expressionString.charAt(i) != '0')
				token.append(expressionString.charAt(i));

			if(token.length() != 0) {
				tokenList.add(token.toString());
				token.delete(0, token.length());
			}
		}

		//..
		//ADD YOUR CODE ABOVE HERE
	}

	/**
	 * This method evaluates the expression and returns the value of the expression
	 * Evaluation is done using 2 stack ADTs, operatorStack to store operators
	 * and valueStack to store values and intermediate results.
	 * - You must fill in code to evaluate an expression using 2 stacks
	 */
	public Integer eval(){
		Stack<String> operatorStack = new Stack<String>();
		Stack<Integer> valueStack = new Stack<Integer>();

		//ADD YOUR CODE BELOW HERE
		//..

		String op;

		for(String t : tokenList) {
			if(t.equals("(") || t.equals("["))
				continue;

			if(isInteger(t))
				valueStack.push(Integer.parseInt(t));
			else
				operatorStack.push(t);

			//if(valueStack.size() >= 1 && !operatorStack.empty() && operatorStack.peek().equals(")")) {
			if(!valueStack.empty() && !operatorStack.empty() && (operatorStack.peek().equals(")") || operatorStack.peek().equals("]"))) {
				if(operatorStack.peek().equals("]")) {
					operatorStack.pop();
					valueStack.push(-valueStack.pop());
				}

				else if(operatorStack.peek().equals(")")) {
					operatorStack.pop();

					if(!valueStack.empty() && !operatorStack.empty()) {

						//operatorStack.pop();

						op = operatorStack.peek();

						if(op.equals("+"))
							valueStack.push(valueStack.pop() + valueStack.pop());
						else if(op.equals("*"))
							valueStack.push(valueStack.pop() * valueStack.pop());
						else if(op.equals("-") || op.equals("/")) {
							Integer opr_2 = valueStack.pop();
							Integer opr_1 = valueStack.pop();
							if(op.equals("-"))
								valueStack.push(opr_1 - opr_2);
							else
								valueStack.push(opr_1 / opr_2);
						}

						else if(op.equals("++"))
							valueStack.push(valueStack.pop() + 1);
						else if(op.equals("--")) 
							valueStack.push(valueStack.pop() - 1);

						else if(op.equals("]") && valueStack.peek() < 0) {
							valueStack.push(-valueStack.pop());
						}

						operatorStack.pop();
					}
				}
			}
		}

		while(operatorStack.size() >= 1)
			operatorStack.pop();

		return valueStack.pop();

		//..
		//ADD YOUR CODE ABOVE HERE

	}

	//Helper methods
	/**
	 * Helper method to test if a string is an integer
	 * Returns true for strings of integers like "456"
	 * and false for string of non-integers like "+"
	 * - DO NOT EDIT THIS METHOD
	 */
	private boolean isInteger(String element){
		try{
			Integer.valueOf(element);
		}catch(NumberFormatException e){
			return false;
		}
		return true;
	}

	/**
	 * Method to help print out the expression stored as a list in tokenList.
	 * - DO NOT EDIT THIS METHOD    
	 */

	@Override
	public String toString(){	
		String s = new String(); 
		for (String t : tokenList )
			s = s + "~"+  t;
		return s;		
	}

}

