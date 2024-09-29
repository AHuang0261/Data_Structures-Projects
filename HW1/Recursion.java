
public class Recursion {
	public static void main(String args){
		
		System.out.println(findFactorial(5));
		
	}
	public static int findFactorial(int n) {
		if(n == 1) return 1;
		return n * findFactorial(n-1);
	}
	
	public static int fibonacci(int n) {
		if(n == 0|| n == 1) return n;
		//this will 
		return(fibonacci(n-1) + fibonacci(n-2));
	}
}
