package hw4;
import java.util.*;
public class main {
	
	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		while (true) {
			ExpressionTree tree = new ExpressionTree();
			System.out.println("Type in your expression");
			tree.buildTree(kb.nextLine());
			System.out.println("Infix:");
			tree.infix();
			
			System.out.println("\nPrefix:");
			tree.prefix();
			
			System.out.println("\nPostfix:");
			tree.postfix();
			System.out.println();
		}
		
	}
}
