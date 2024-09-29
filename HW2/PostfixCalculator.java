package hw2;

public class PostfixCalculator {

	
	public PostfixCalculator() {
		
	}
	
	public static double evaluate(String postFix) {
		ArrayStack<Double> numStack = new ArrayStack<>();
		while(postFix != "") {
			int parseInd = postFix.indexOf(' ');
			if(Character.isDigit(postFix.charAt(0))) {
				String sNum = postFix.substring(0, parseInd);
				numStack.push(Double.parseDouble(sNum));
				
			}
			//We should have only operators now, and they should be the first character
			//the first is the first off the stack, but the second of the numbers in the actual postfix expression
			else if(postFix.charAt(0) == '+') {
				double first = numStack.pop();
				double second = numStack.pop();
				numStack.push(first + second);
			}
			else if(postFix.charAt(0) == '-') {
				double first = numStack.pop();
				double second = numStack.pop();
				numStack.push(second - first);
			}
			else if(postFix.charAt(0) == '*') {
				double first = numStack.pop();
				double second = numStack.pop();
				numStack.push(first * second);
			}
			else if(postFix.charAt(0) == '/') {
				double first = numStack.pop();
				double second = numStack.pop();
				numStack.push(second / first);
			}
			else if(postFix.charAt(0) == '^') {
				double first = numStack.pop();
				double second = numStack.pop();
				numStack.push(Math.pow(second, first));
			}
			
			postFix = postFix.substring(parseInd + 1);
		}
		
		return numStack.pop();//If the expression is valid, there should only be one element in the stack at this time
	}
}
