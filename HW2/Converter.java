package hw2;
import java.util.*;
public class Converter {

	private String input;
	private List<String> list;
	
	
	public Converter(String in) {
		input = in;
		char[] arr = new char[input.length()];
		for(int i = 0; i < input.length(); i++) {
			arr[i] = input.charAt(i);
		}
		list = ParserHelper.parse(arr);
	}
	
	public String toPostFix() {
		String output = "";
		ArrayStack<String> stk = new ArrayStack<String>(list.size());
		for(String curr: list) {
			if(Character.isDigit(curr.charAt(0))) {
				output += curr + " ";
				
				continue;
			}
			if(opValue(curr) == -1) {
				System.out.println("oh no");
				continue;
			}
			
			//at this point there will only be operators
			if(stk.isEmpty()) {
				stk.push(curr);
				
			}
			else if(opValue(curr) == 3) stk.push(curr);
			else if(opValue(curr) == 4) {
				while(!stk.isEmpty() && opValue(stk.top()) != 3) {
					if(opValue(stk.top()) != 3) output += stk.pop() + " ";
						
				}
				stk.pop();
			}
			else if(opValue(curr) > opValue(stk.top())) stk.push(curr);
			
			else {
				while(!stk.isEmpty() && opValue(curr) <= opValue(stk.top()) && opValue(stk.top())!= 3) {
					output += stk.pop() + " ";
				}
				stk.push(curr);
			}
			
		}
		while(!stk.isEmpty()) {
			output += stk.pop() + " ";
		}
		return output;
	}
	
	private int opValue(String in) {
		
		if(in.equals("+") || in.equals("-"))return 0;
		if(in.equals("*") || in.equals("/")) return 1;
		if(in.equals("^")) return 2;
		if(in.equals("(")) return 3;
		if(in.equals(")")) return 4;
		return -1;
	}
	
	
	
}
