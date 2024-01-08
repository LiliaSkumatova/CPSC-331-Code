package ca.ucalgary.cpsc331.a2;

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


//
//@Override
//public boolean insert(int k) {
//	if(member(k)) {
//		return false;
//	}else {
//		if(root == null) {
//			root = new Node(k);
//		}else {
//			insertHelper(k, root);
//			fixInsert()
//		}
////		Node newNode = new Node(k);
////		insertNode(newNode);
////		fixInsert(newNode);
//		return true;
//	}
//	
//	
//	//check if member
//	
//}
//
//private void insertHelper(int k, Node node){
//	if(node.key ==)
//	
//	
//	
//	Node y = null;
//	Node x = root;
//	
//	while(x != null) {
//		y = x;
//		if (newNode.key < x.key) {
//			x = x.left;
//		}else {
//			x = x.right;
//		}
//	}
//	newNode.parent = y;
//	if (y == null) {
//		root = newNode;
//	}else if(newNode.key < y.key) {
//		y.left = newNode;
//	}else {
//		y.right = newNode;
//	}
//}
//
//private void fixInsert(Node newNode) {
//	while(newNode != root && newNode.parent.red == true) {
//		if(newNode.parent == newNode.parent.parent.left) {
//			Node uncle = newNode.parent.parent.right;
//			
//			if( uncle.red == true) {
//				newNode.parent.red = false;
//				uncle.red = false;
//				newNode.parent.parent.red = true;
//				newNode = newNode.parent.parent;
//			}else {
//				if(newNode==newNode.parent.right) {
//					newNode = newNode.parent;
//					leftRotate(newNode);
//				}
//				newNode.parent.red = false;
//				newNode.parent.parent.red = true;
//				rightRotate(newNode.parent.parent);
//			}
//		}else {
//			Node uncle = newNode.parent.parent.left;
//			if(uncle.red==true) {
//				newNode.parent.red = false;
//				uncle.red = false;
//				newNode.parent.parent.red = true;
//				newNode = newNode.parent.parent;
//			}else {
//				if(newNode==newNode.parent.left) {
//					newNode = newNode.parent;
//					rightRotate(newNode);
//				}
//				newNode.parent.red = false;
//				newNode.parent.parent.red = true;
//				leftRotate(newNode.parent.parent);
//				
//			}
//		}
//	}
//	root.red = false;
//}
//
//@Override
//public boolean delete(int k) {
//	if(member(k)) {
//		return false;
//	}else {
//		Node nodeToDelete = search(k);
//		if(nodeToDelete == NIL) {
//			throw new RuntimeException("Key does not exsit tree is empty");
//		}
//		deleteNode(nodeToDelete);
//		return true;
//	}
//	
//}
//
//private void deleteNode(Node nodeToDelete) {
//	Node y = nodeToDelete;
//	Node x;
//	boolean OGColourOfy = y.red;
//	
//	if(nodeToDelete.left == NIL) {
//		x = nodeToDelete.right;
//		transplant(nodeToDelete, nodeToDelete.right);
//	}else if(nodeToDelete.right == NIL) {
//		x = nodeToDelete.left;
//		transplant(nodeToDelete, nodeToDelete.left);
//	}else {
//		y = minimum(nodeToDelete.right);
//		OGColourOfy = y.red;
//		x = y.right;
//		
//		if(y.parent == nodeToDelete) {
//			x.parent = y;
//		}else {
//			transplant(y, y.right);
//			y.right = nodeToDelete.right;
//			y.right.parent = y;
//		}
//		
//		transplant(nodeToDelete, y);
//		y.left= nodeToDelete.left;
//		y.left.parent = y;
//		y.red = nodeToDelete.red;
//	}
//	if(OGColourOfy == false) {
//		fixDelete(x);
//	}
//}
//
//private void fixDelete(Node x) {
//	while(x != root && x.red == false) {
//		if(x == x.parent.left) {
//			Node w = x.parent.right;
//			
//			if(w.red == true) {
//				w.red = false;
//				x.parent.red = true;
//				leftRotate(x.parent);
//				w = x.parent.right;
//			}
//			if(w.left.red == false && w.right.red == false) {
//				w.red = true;
//				x = x.parent;
//			}else {
//				if(w.right.red == false) {
//					w.left.red = false;
//					w.red = true;
//					rightRotate(w);
//					w = x.parent.right;
//				}
//				w.red = x.parent.red;
//				x.parent.red = false;
//				w.right.red = false;
//				leftRotate(x.parent);
//				x = root;
//			}
//		}else {
//			Node w = x.parent.left;
//			
//			if(w.red == true) {
//				w.red = false;
//				x.parent.red = true;
//				rightRotate(x.parent);
//				w = x.parent.left;
//			}
//			if(w.right.red == false && w.left.red == false) {
//				w.red = true;
//				x = x.parent;
//			}else {
//				if(w.left.red == false) {
//					w.right.red = false;
//					w.red = true;
//					leftRotate(w);
//					w = x.parent.left;
//				}
//				w.red = x.parent.red;
//				x.parent.red = false;
//				w.left.red = false;
//				rightRotate(x.parent);
//				x = root;
//			}
//		}
//	}
//	x.red = false;
//}
//
////returns true if k is a member
//@Override
//public boolean member(int k) {
//	Node tmp = search(root, k);
//	if( tmp == null) {
//		return false;
//	}else if( k == tmp.key) {
//		return true;
//	}else {
//		return false;
//	}
//}
//
//private Node search(Node node, int k) {
//	if(node == null || k == node.key) {
//		return node;
//	}
//	if (k < node.key) {
//		return search(node.left, k);
//	}else {
//		return search(node.right, k);
//	}
//	
//}
//private Node minimum(Node node) {
//	while(node.left != NIL) {
//		node = node.left;
//	}
//	return node;
//}
//
//private void transplant(Node u, Node v) {
//	if (u.parent == NIL) {
//		root = v;
//	}else if(u == u.parent.left) {
//		u.parent.left = v;
//	}else {
//		u.parent.right = v;
//	}
//	v.parent = u.parent;
//}
//
//private void leftRotate(Node x) {
//	Node y = x.right;
//	x.right = y.left;
//	
//	if(y.left != NIL) {
//		y.left.parent = x;
//	}
//	y.parent = x.parent;
//	
//	if(x.parent == NIL) {
//		root = y;
//	}else if(x == x.parent.left) {
//		x.parent.left = y;
//	}else {
//		x.parent.right = y;
//	}
//	y.left = x;
//	x.parent = y;
//}
//
//private void rightRotate(Node y) {
//	Node x = y.left;
//	y.left = x.right;
//	
//	if(x.right != NIL) {
//		x.right.parent = y;
//	}
//	x.parent = y.parent;
//	
//	if(y.parent == NIL) {
//		root = x;
//	}else if(y== y.parent.right) {
//		y.parent.right = x;
//	}else {
//		y.parent.left = x;
//	}
//	x.right = y;
//	y.parent = x;
//}
//
//@Override
//public String toString() {
//	StringBuilder result = new StringBuilder();
//	if(!empty()) {
//		toStringHelper(root, result, "*");
//	}
//	return result.toString();
//}
//
//private void toStringHelper(Node node, StringBuilder result, String address) {
//	result.append(address).append(":").append(node.red == true ? "red" : "black").append(":").append(node.key).append("\n");
//	toStringHelper(node.left, result, address + "L");
//	toStringHelper(node.right, result, address + "R");
//}
