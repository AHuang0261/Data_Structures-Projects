package hw5;

import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

//constructor
public class HuffmanConverter {

	// ASCII number of characters
	public static final int NUMBER_OF_CHARACTERS = 256;

	private String contents;
	private HuffmanTree huffmanTree;
	private int count[];
	private String code[];

	// Construct using an input string.
	// Initialize count and code.
	public HuffmanConverter(String input) {
		this.contents = input;
		this.count = new int[NUMBER_OF_CHARACTERS];
		this.code = new String[NUMBER_OF_CHARACTERS];
	}

	// Record how often each character occurs and store in count.
	public void recordFrequencies() {
		for (int i = 0; i < contents.length(); i++) {
			int num = (int) contents.charAt(i);
			count[num]++;
		}
	}

	public void printFrequencies() {
		for (int i = 0; i < count.length; i++) {
			if (count[i] != 0) {
				System.out.println("" + (char) i + ": " + count[i]);
			}
		}
	}

	// Construct a Huffman tree from count and
	// store the tree in huffmanTree.
	public void frequenciesToTree() {
		BinaryHeap<HuffmanNode> heap = new BinaryHeap<HuffmanNode>();
		for (int i = 0; i < count.length; i++) {
			if (count[i] != 0) {
				heap.insert(new HuffmanNode("" + (char) i, count[i]));
			}
		}
		this.huffmanTree = HuffmanTree.createFromHeap(heap);
	}

	// Construct code from huffmanTree.
	public void treeToCode() {
		for(int i = 0; i < code.length; i++) {
			code[i] = "";
		}
		treeToCode(huffmanTree.root, "");
	}

	private void treeToCode(HuffmanNode t, String encoding) {
		if(t.left == null && t.right == null) {
			code[(int) t.letter.charAt(0)] = encoding;
		}
		if(t.left != null) treeToCode(t.left, encoding + "0");
		
		if(t.right != null) treeToCode(t.right, encoding + "1");
	}

	// Encode content using code.
	public String encodeMessage() {
		String message = "";
		int huffSize = 0;
		int asciiSize = 0;
		for (int i = 0; i < contents.length(); i++) {
			asciiSize += 8;
			String c = code[(int) contents.charAt(i)];//c is the huffman code for the character of interest
			message += c;
			huffSize += c.length();
		}
//		System.out.println("Huffman encoding size: " + huffSize);
//		System.out.println("ASCII size: " + asciiSize);
		return message;
	}
	
	public void printCode() {
		for (int i = 0; i < code.length; i++) {
			if (!code[i].equals("")) {
				System.out.println("" + (char) i + ": " + code[i]);
			}
		}
	}

	// Decode a Huffman encoding.
	public String decodeMessage(String encodedStr) {
		HuffmanNode walk = huffmanTree.root;
		String message = "";
		for(int i = 0; i < encodedStr.length(); i++) {
			char curr = encodedStr.charAt(i);
//			if(curr == ' ') continue;
			if(curr == '0') walk = walk.left;
			else walk = walk.right;
			if(walk.left == null && walk.right == null) {
				message += walk.letter;
				walk = huffmanTree.root;
			}
		}
		return message;
	}

	// Read an input file.
	public static String readContents(String filename) {
		String temp = "";
		try {
			File file = new File(filename);
			Scanner sc = new Scanner(file);
			while (sc.hasNextLine()) {
				temp += sc.nextLine();
				temp += "\n";
			}
			sc.close();
			return temp;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return "";
	}

	public static void main(String args[]) {
		String input = HuffmanConverter.readContents(args[0]);
//		String input = HuffmanConverter.readContents("love_poem_80.txt");
		HuffmanConverter h = new HuffmanConverter(input);
		h.recordFrequencies();
		h.printFrequencies();
		// Print a list of characters and frequencies here!
		h.frequenciesToTree();
		h.treeToCode();
		h.printCode();
		// Print the Huffman encoding here!
		String encoded = h.encodeMessage();
		System.out.println(encoded + "\n");
		System.out.println("Message size in ASCII encoding: " + h.contents.length() * 8);
		System.out.println("Message size in Huffman coding: " + encoded.length() + "\n");
		System.out.println(h.decodeMessage(encoded));
	}

}
