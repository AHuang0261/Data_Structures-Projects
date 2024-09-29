package hw5;

public class HuffmanNode implements Comparable<HuffmanNode>{
	public String letter;
	public Double frequency;
	public HuffmanNode left, right;
	
	public HuffmanNode(String letter, double frequency) {
		this.letter = letter;
		this.frequency = frequency;
		//left and right are null by default, which is what we want
	}
	
	public HuffmanNode(HuffmanNode left, HuffmanNode right) {
		this.left = left;
		this.right = right;
		this.letter = left.letter + right.letter;
		this.frequency = left.frequency + right.frequency;
	}
	
	 public int compareTo(HuffmanNode o) {
		 return this.frequency.compareTo(o.frequency);
	 }
	 
	 public String toString() {
		 return letter + " " + frequency;
	 }


}
