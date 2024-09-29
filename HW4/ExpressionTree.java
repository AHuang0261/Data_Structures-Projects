package hw4;

import hw2.Converter;

public class ExpressionTree {
	Node root;

	public ExpressionTree() {
		root = null;
	}

	public Node buildTree(String infix) {
		Converter con = new Converter(infix);
		String postFix = con.toPostFix();
		ArrayStack<Node> stack = new ArrayStack<Node>();

		while (postFix != "") {
			int parseInd = postFix.indexOf(' ');
			// Adding operands
			if (Character.isDigit(postFix.charAt(0))) {
				String sNum = postFix.substring(0, parseInd);
				stack.push(new Node(Integer.parseInt(sNum), null, null));
			}
			
			else {
				Node right = stack.pop();
				Node left = stack.pop();
				stack.push(new Node("" + postFix.charAt(0), left, right));
			}

			postFix = postFix.substring(parseInd + 1);
		}
		root = stack.pop();
		return root;
	}
	
	public void prefix() {
		prefix(root);
	}
	
	private void  prefix(Node n) {
		if(n.leftChild == null) {
			System.out.print(n.element.toString());
			return;
		}
		System.out.print(n.element); 
		prefix(n.leftChild);
		prefix(n.rightChild);
	}
	
	public void postfix() {
		postfix(root);
	}
	
	private void postfix(Node n) {
		if(n.leftChild == null) {
			System.out.print(n.element.toString());
			return;
		}
		postfix(n.leftChild);
		postfix(n.rightChild);
		System.out.print(n.element.toString());
	}
	
	public void infix() {
		infix(root);
	}
	
	private void infix(Node n) {
		if(n.leftChild == null) {
			System.out.print(n.element.toString());
			return;
		}
		System.out.print("(");
		infix(n.leftChild);
		System.out.print(n.element.toString());
		infix(n.rightChild);
		System.out.print(")");
	}
}
