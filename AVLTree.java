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
        Node rightOfRoot = node.right;
        
        node.right = rightOfRoot.left;
        rightOfRoot.left = node;
        
        node.height = maxHeight(height(node.left),  height(node.right)) + 1;
        rightOfRoot.height = maxHeight(height(rightOfRoot.left),  height(rightOfRoot.right)) + 1;
        
        return rightOfRoot;
    }
    
    private Node rightRotation(Node node) {
        Node leftOfRoot = node.left;
    
        node.left  = leftOfRoot.right;
        leftOfRoot.right = node;

        node.height = maxHeight(height(node.left),  height(node.right)) + 1;
        leftOfRoot.height = maxHeight(height(leftOfRoot.left),  height(leftOfRoot.right)) + 1;
        
        return leftOfRoot;
    } 
    
    private Node leftleftRotation(Node node) {
    	System.out.println(">> LL-Imbalance >> at " + node.key +"(BF:"+ balanceFactor(node) + ")");
    	node = rightRotation(node);
    	return node;
    }
    
    private Node rightrightRotation(Node node) {
    	System.out.println(">> RR-Imbalance >> at " + node.key +"(BF:"+ balanceFactor(node) + ")");
    	node = leftRotation(node);
    	return node;
    }
    
    private Node leftRightRotation(Node node) {
    	System.out.println(">> LR-Imbalance >> at " + node.key +"(BF:"+ balanceFactor(node) + ")");
        node.left = leftRotation(node.left);
        node = rightRotation(node);
        return node;
    }
    
    private Node rightLeftRotation(Node node) {
    	System.out.println(">> RL-Imbalance >> at " + node.key +"(BF:"+ balanceFactor(node) + ")");
    	node.right = rightRotation(node.right);
        node = leftRotation(node);
        return node;
    }
    
    private Node rebalance(Node node) {
    	  // Left-heavy
    	  if (balanceFactor(node) < -1)
    	    if (balanceFactor(node.left) <= 0)
    	    	//LL-Rotation
    	    	node = leftleftRotation(node);
    	    else                   
    	    	//LR-Rotation
    	    	node = leftRightRotation(node);

    	  // Right-heavy
    	  if (balanceFactor(node) > 1)
    	    if (balanceFactor(node.right) >= 0)
    	    	//RR-Rotation
    	    	node = rightrightRotation(node);
    	    else                          
    	    	//RL-Rotation
    	    	node = rightLeftRotation(node);
    	  return node;
    }

    public void insert(int key) {
        root = insert(key, root);
    }
    
    private Node insert(int key, Node node) {
    	if (node == null) node = new Node(key);
        
        else if (key < node.key) {
        	node.left = insert(key, node.left);
        } else if (key > node.key) {
        	node.right = insert(key, node.right);
        }
    	node.height = maxHeight(height(node.left),  height(node.right)) + 1;
    	node = rebalance(node);
    	
    	return node;
    }
        
    private int maxHeight(int leftHeight, int rightHeight) {
    	return (leftHeight > rightHeight) ? leftHeight : rightHeight;
    }
    
    private int balanceFactor(Node node) {
    	if (node == null) return 0;
    	return (height(node.right)-height(node.left));
    }

	public void delete(int key) {
		if (this.root != null && search(root, key) == true) {
	    	this.root = delete(root, key);
	    	printMytree();
	    } else {
	    	System.out.println("Node " + key + " is not found");
	    }
	}
	
	private Node delete(Node node, int key) {
	    if (node == null) return node;

	    if (key < node.key) 
	    	node.left = delete(node.left, key);
	    else if (key > node.key)
	    	node.right = delete(node.right, key);

	    // no children
	    else if (node.left == null && node.right == null)
	    	node = null;

	    // one child
	    else if (node.left == null)
	    	node = node.right;
	    else if (node.right == null)
	    	node = node.left;

	    // two children
	    else {    	
            Node temp = minKeyNode(node.right);
            node.key = temp.key;
            node.right = delete(node.right, temp.key);
	    }

		if (node == null) return node;
		
		node.height = maxHeight(height(node.left),  height(node.right)) + 1;
		node = rebalance(node);
		
		return node;
	}

    public Node minKeyNode(Node node){
    	while (node.left != null) {
        	node = node.left;
        }
        return node;
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
    	return (node == null) ? -1 : node.height;
    }
     
    private String key(Node node) {
        return (node == null) ? " " : Integer.toString(node.key);
    }
 
    private void printMytree(Node Node) {
        if (Node != null) {
        	System.out.print("\n"+key(Node.left)+" <- "+key(Node)+" (BF:"+balanceFactor(Node)+")"+" -> "+key(Node.right));
        	printMytree(Node.left);
            printMytree(Node.right);
        }
    }
 
}