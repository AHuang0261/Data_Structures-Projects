package hw5;

public class HuffmanTree {
	HuffmanNode root;

	public HuffmanTree(HuffmanNode huff) {
		root = huff;
	}

	public void printLegend() {
		printLegend(root, "");
	}

	private void printLegend(HuffmanNode node, String code) {
		if (node.letter.length() > 1) {
			printLegend(node.left, code + "0");
			printLegend(node.right, code + "1");
		}
		System.out.print(node.letter + " = " + code + " ");
	}

	public static BinaryHeap<HuffmanNode> legendToHeap(String legend) {
		BinaryHeap<HuffmanNode> heap = new BinaryHeap<HuffmanNode>();
		int parseInd;
		while (!legend.isEmpty()) {
			// getting the letter
			parseInd = legend.indexOf(' ');
			String let = legend.substring(0, parseInd);
			legend = legend.substring(parseInd + 1);
			// getting the frequency
			parseInd = legend.indexOf(' ');
			double freq;
			if (parseInd == -1) {
				freq = Double.parseDouble(legend);
				parseInd = 0; //this will cause the substring to delete the string which is what we want
			}
				
			else
				freq = Double.parseDouble(legend.substring(0, parseInd));
			legend = legend.substring(parseInd + 1);
			heap.insert(new HuffmanNode(let, freq));

		}
		return heap;
	}
	 public static HuffmanTree createFromHeap(BinaryHeap<HuffmanNode> heap) {
		 HuffmanNode a;
		 HuffmanNode b;
		 while(true) {
			 a = heap.deleteMin();
			 if(heap.isEmpty()) break;
			 b = heap.deleteMin();
			 heap.insert(new HuffmanNode(a,b));
		 }
		 return new HuffmanTree(a);
		 
	 }
	 public static void main(String[] args) {
		 BinaryHeap<HuffmanNode> heap = new BinaryHeap<HuffmanNode>();
		 heap = legendToHeap("A 20 E 24 G 3 H 4 I 17 L 6 N 5 O 10 S 8 V 1 W 2");
//		 heap = legendToHeap("E 1 R 3 O 1");
		 heap.printHeap();
		 HuffmanTree tree = createFromHeap(heap);
		 tree.printLegend();
	 }
}
