public class Node {
	Node parent;
	Node left;
	Node right;
	boolean red; // true iff node is red
	int key;
	
	Node(int k) {
	key = k; red = false;
	parent = left = right = null;
	}
}
