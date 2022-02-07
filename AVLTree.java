//package algoDat_avltree;

class AVLTree {
    private Node root;
    
    public AVLTree() {
        root = null;
    }
    
    static class Node {

    	Node left;
    	Node right;
        int key;
        int height;

        Node() {
            left = null;
            right = null;
            key = 0;
            height = 0;
        }

        Node(int n) {
            left = null;
            right = null;
            key = n;
            height = 0;
        }
    }
    
    private Node leftRotation(Node node) {
        Node oldRight = node.right;
        Node oldLeft = oldRight.left;
        oldRight.left = node;
        node.right = oldLeft;

        node.height = maxHeight(height(node.left), height(node.right)) + 1;
        oldRight.height = maxHeight(node.height, height(oldRight.right)) + 1;
        
        return oldRight;
    }

    private Node rightRotation(Node node) {
        Node oldLeft = node.left;
        Node oldRight = oldLeft.right;
        oldLeft.right = node;
        node.left = oldRight;

        node.height = maxHeight(height(node.left), height(node.right)) + 1;
        oldLeft.height = maxHeight(height(oldLeft.left), node.height) + 1;
        
        return oldLeft;
    } 
    
    private Node leftleftRotation(Node node) {
    	System.out.println(" LL-Imbalance");
    	node = rightRotation(node);
    	return node;
    }
    
    private Node rightrightRotation(Node node) {
    	System.out.println(" RR-Imbalance");
    	node = leftRotation(node);
    	return node;
    }
    
    private Node leftRightRotation(Node node) {
    	System.out.println(" LR-Imbalance");
        node.left = leftRotation(node.left);
        node = rightRotation(node);
        return node;
    }
    
    private Node rightLeftRotation(Node node) {
    	System.out.println(" RL-Imbalance");
    	node.right = rightRotation(node.right);
        node = leftRotation(node);
        return node;
    }

    public void insert(int key) {
        root = insert(key, root);
    }

    private Node insert(int key, Node root) {
        if (root == null) root = new Node(key);
        
        else if (key < root.key) {
        	
        	root.left = insert(key, root.left);
            if (blanceFactor(root) > 1 || blanceFactor(root) < -1) {
                if (key < root.left.key) {
                	//LL-Rotation
                	System.out.print(">> at " + root.key +"(BF:"+ blanceFactor(root) + ")");
                	root = leftleftRotation(root);
                } else {
                	//LR-Rotation
                	System.out.print(">> at " + root.key +"(BF:"+ blanceFactor(root) + ")");
                	root = leftRightRotation(root); 
                }
            }
        } else if (key > root.key) {
        	
        	root.right = insert(key, root.right);
            if (blanceFactor(root) > 1 || blanceFactor(root) < -1) {
                if (key > root.right.key) {
                	//RR-Rotation
                	System.out.print(">> at " + root.key +"(BF:"+ blanceFactor(root) + ")");
                	root = rightrightRotation(root);
                } else {
                    //RL-Rotation
                	System.out.print(">> at " + root.key +"(BF:"+ blanceFactor(root) + ")");
                	root = rightLeftRotation(root);
                }
            }
        }
        
        root.height = maxHeight(height(root.left), height(root.right)) + 1;
        return root;
    }
        
    private int maxHeight(int leftHeight, int rightHeight) {
    	return (leftHeight > rightHeight) ? leftHeight : rightHeight;
    }
    
    private int blanceFactor(Node node) {
    	if (node == null) return 0;
    	return (height(node.right)-height(node.left));
    }

	public void delete(int key) {
		delete(root, key);
	}

	private void delete(Node node, int key) {
		//Kein Kind
		//Ein Kind
		//Zwei Kind
    }

	public boolean search(int key) {
    	return search(root, key);
    }

    private boolean search(Node node, int key) {
    	boolean found = false;
    	while(node != null) {
    		if(node.key < key) {
    			node = node.right;
    		} else if(node.key > key) {
    			node = node.left;
    		} else {
    			found = true;
    			break;
    		}
    		search(node, key);
    	}
    	return found;
    }
    
    public void printMytree() {
    	printMytree(root);
    }
    
    private int height(Node node) {
    	return (node == null) ? 0 : node.height;
    }
    
    private String key(Node node) {
        return (node == null) ? " " : Integer.toString(node.key);
    }
 
    private void printMytree(Node Node) {
        if (Node != null) {
        	System.out.print("\n"+key(Node.left)+" <- "+key(Node)+" (BF:"+blanceFactor(Node)+")"+" -> "+key(Node.right));
        	printMytree(Node.left);
            printMytree(Node.right);
        }
    }
 
}