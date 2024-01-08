package ca.ucalgary.cpsc331.a2;

public class RedBlackTree implements Dictionary {
    private Node root;
    private Node NIL;

    public RedBlackTree() {
        NIL = new Node(0);
        NIL.red = false;		
        root = NIL;
    }

    /**
     * checks if the tree is empty
     * @return true if empty false if not
     */
    public boolean empty() {
        return root == NIL;
    }

    /**
     * Inserts a new node that does not exist in the tree yet
     * @param k is the value of the new node we want to create
     * @return true if new node is inserted false if node already exists
     */
    public boolean insert(int k) {
        Node z = new Node(k);
        Node y = NIL;
        Node x = root;

        while (x != NIL) {
            y = x;
            if (z.key < x.key) {
                x = x.left;
            } else if (z.key > x.key) {
                x = x.right;
            } else {
                return false; // Key already exists
            }
        }

        z.parent = y;
        
        if (y == NIL) {
            root = z;
        } else if (z.key < y.key) {
            y.left = z;
        } else {
            y.right = z;
        }

        z.left = NIL;
        z.right = NIL;
        z.red = true;

        fixInsert(z);
        return true;
    }

    /**
     * Checks if node exists in the tree and if the node
     * exists calls helper method
     * @param k is the key of the node user wants to delete
     * @return return true if node deleted false if not
     */
    public boolean delete(int k) {
        Node z = search(k);
        
        if (z == NIL)
            return false; // Key does not exist
        deleteHelper(z);
        
        return true;
    }

    /**
     * Checks if the key is a key in the tree
     * @param k the key we want to check exists in tree
     * @return true if exists and false if not
     */
    public boolean member(int k) {
        return search(k) != NIL;
    }

    /**
     * searches the tree to see if node exists
     * @param k the key of the node we want to find
     * @return the node if it is found or NIL if not
     */
    private Node search(int k) {
        Node x = root;
        
        while (x != NIL) {
            if (k < x.key) {
                x = x.left;
            } else if (k > x.key) {
                x = x.right;
            } else {
                return x;
            }
        }
        return NIL;
    }

    /**
     * Helper method for delete
     * @param z node we want to delete
     */
    private void deleteHelper(Node z) {
        Node y = z;
        Node x;
        boolean yOriginalColor = y.red;

        if (z.left == NIL) {
            x = z.right;
            transplant(z, z.right);
        } else if (z.right == NIL) {
            x = z.left;
            transplant(z, z.left);
        } else {
            y = minimum(z.right);
            yOriginalColor = y.red;
            x = y.right;
            
            if (y.parent == z) {
                x.parent = y;
            } else {
                transplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }
            transplant(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.red = z.red;
        }

        if (!yOriginalColor) {
            fixDelete(x);
        }
    }

    
    /**
     * The arrangement of the nodes on the right are transformed into
     * the arrangements on the left node
     * @param x is the node the rotation is happening on
     */
    private void leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;
        
        if (y.left != NIL) {
            y.left.parent = x;
        }
        
        y.parent = x.parent;
        
        if (x.parent == NIL) {
            root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }

    
    /**
     * The arrangement of the nodes on the left is transformed into the
     * arrangements on the right node
     * @param y is the node the rotation is happening on
     */
    private void rightRotate(Node y) {
        Node x = y.left;
        y.left = x.right;
        
        if (x.right != NIL) {
            x.right.parent = y;
        }
        
        x.parent = y.parent;
        
        if (y.parent == NIL) {
            root = x;
        } else if (y == y.parent.left) {
            y.parent.left = x;
        } else {
            y.parent.right = x;
        }
        x.right = y;
        y.parent = x;
    }

    /**
     * This method fixes any violations that occur after
     * inserting a new node
     * @param z new node inserted to check it's violations
     */
    private void fixInsert(Node z) {
        while (z.parent.red) {
            if (z.parent == z.parent.parent.left) {
                Node y = z.parent.parent.right;
                
                if (y.red) {
                    z.parent.red = false;
                    y.red = false;
                    z.parent.parent.red = true;
                    z = z.parent.parent;
                } else {
                	if (z == z.parent.right) {
                        z = z.parent;
                        leftRotate(z);
                    }
                    z.parent.red = false;
                    z.parent.parent.red = true;
                    rightRotate(z.parent.parent);
                }
                
            } else {
                Node y = z.parent.parent.left;
                if (y.red) {
                    z.parent.red = false;
                    y.red = false;
                    z.parent.parent.red = true;
                    z = z.parent.parent;
                } else {
                    if (z == z.parent.left) {
                        z = z.parent;
                        rightRotate(z);
                    }
                    z.parent.red = false;
                    z.parent.parent.red = true;
                    leftRotate(z.parent.parent);
                }
            }
        }
        root.red = false;
    }

    
    /**
     * helper method for delete to fix any violations after deleting a node
     * @param x node we want to check violations on
     */
    private void fixDelete(Node x) {
        while (x != root && !x.red) {
            if (x == x.parent.left) {
                Node w = x.parent.right;
                if (w.red) {
                    w.red = false;
                    x.parent.red = true;
                    leftRotate(x.parent);
                    w = x.parent.right;
                }
                
                if (!w.left.red && !w.right.red) {
                    w.red = true;
                    x = x.parent;
                } else {
                    if (!w.right.red) {
                        w.left.red = false;
                        w.red = true;
                        rightRotate(w);
                        w = x.parent.right;
                    }
                    w.red = x.parent.red;
                    x.parent.red = false;
                    w.right.red = false;
                    leftRotate(x.parent);
                    x = root;
                }
                
            } else {
                Node w = x.parent.left;
                if (w.red) {
                    w.red = false;
                    x.parent.red = true;
                    rightRotate(x.parent);
                    w = x.parent.left;
                }
                
                if (!w.right.red && !w.left.red) {
                    w.red = true;
                    x = x.parent;
                } else {
                    if (!w.left.red) {
                        w.right.red = false;
                        w.red = true;
                        leftRotate(w);
                        w = x.parent.left;
                    }
                    w.red = x.parent.red;
                    x.parent.red = false;
                    w.left.red = false;
                    rightRotate(x.parent);
                    x = root;
                }
            }
        }
        x.red = false;
    }

    // Helper method to transplant two subtrees.
    /**
     * is used to replace the subtree rooted at node u by the subtree 
     * rooted at node v
     * @param u is a node
     * @param v is a node
     */
    private void transplant(Node u, Node v) {
        if (u.parent == NIL) {
            root = v;
        } else if (u == u.parent.left) {
            u.parent.left = v;
        } else {
            u.parent.right = v;
        }
        v.parent = u.parent;
    }

    /**
     * is used to find the minimum node
     * @param x node that we are finding the minimum for
     * @return the minimum node
     */
    private Node minimum(Node x) {
        while (x.left != NIL) {
            x = x.left;
        }
        return x;
    }

    /**
     * ToString method used to convert tree into string
     */
    @Override
    public String toString() {
        String result = "";
        if (root != NIL) {
            result = toStringHelper(root, "*", "");
        }
        return result;
    }


    /**
     * helper method for toString. Method performs a pre-order walk and creates a tree representation
     * @param node the node we want to convert to string
     * @param treeAddress address of the node
     * @param result the result of the node
     * @return return a string
     */
    private String toStringHelper(Node node, String treeAddress, String result) {
        if (node != NIL) {
            String color = "black";
            if (node.red) {
                color = "red";
            }
                
            if (treeAddress.isEmpty()) {
            	treeAddress = "*";
            }
                
            result += treeAddress + ":" + color + ":" + node.key + "\n";
            result = toStringHelper(node.left, treeAddress + "L", result);
            result = toStringHelper(node.right, treeAddress + "R", result);
        }
        return result;
    }
    
    public static void main(String[] args) {
		RedBlackTree rbt = new RedBlackTree();
		rbt.insert(2);
		rbt.insert(1);
		rbt.insert(3);
		System.out.println(rbt.toString());
		rbt.delete(2);
		System.out.println(rbt.toString());

		
	}

}