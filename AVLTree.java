package algoDat_avltree;

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
        oldRight.height = maxHeight(height(oldLeft.left), node.height) + 1;
        
        return oldLeft;
    } 
    
    private Node leftRightRotation(Node node) {
        node.left = leftRotation(node.left);
        node = rightRotation(node);
        return node;
    }
    
    private Node rightLeftRotation(Node node) {
    	node.right = rightRotation(node.right);
        node = leftRotation(node);
        return node;
    }

    public void insert(int key) {
        root = insertHelper(key, root);
    }

    private Node insertHelper(int key, Node root) {
        if (root == null) root = new Node(key);
        
        else if (key < root.key) {
        	
        	root.left = insertHelper(key, root.left);
            if (height(root.left) - height(root.right) > 1) {
                if (key < root.left.key)
                	//LL-Rotation
                	root = rightRotation(root);
                else
                	//LR-Rotation
                	root = leftRightRotation(root); 
            }
        } else if (key > root.key) {
        	
        	root.right = insertHelper(key, root.right);
            if (height(root.right) - height(root.left) > 1) {
                if (key > root.right.key)
                	//RR-Rotation
                	root = leftRotation(root);
                else
                	//RL-Rotation
                	root = rightLeftRotation(root);
            }
        }
        
        root.height = maxHeight(height(root.left), height(root.right)) + 1;
        return root;
    }
        
    private int maxHeight(int leftHeight, int rightHeight) {
    	if(leftHeight > rightHeight) return leftHeight;
    	else return rightHeight;
    }

	public void delete(int key) {
		return delete(root, key);
	}

	private void delete(Node node, int key) {
		//Todo
    }

	public boolean search(int key) {
    	return search(root, key);
    }

    private boolean search(Node node, int key) {
    	//Todo
    }
    
    public void printMytree() {
    	printMytree(root);
    }
    
    private int height(Node node) {
    	if(node == null) return -1;
    	else return node.height;
    }
    
    private String key(Node node) {
        return node == null ? " " : Integer.toString(node.key);
    }
 
    private void printMytree(Node Node) {
        if (Node != null) {
        	System.out.print("\n"+key(Node.left)+" <- "+key(Node)+" -> "+key(Node.right));
        	printMytree(Node.left);
            printMytree(Node.right);
        }
    }
 
}