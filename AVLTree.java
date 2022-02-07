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
        Node leftOfrightOfRoot = rightOfRoot.left;
        
        Node newRoot = rightOfRoot;
        newRoot.left = node;
        node.right = leftOfrightOfRoot;

        node.height = maxHeight(height(node.left), height(node.right)) + 1;
        newRoot.height = maxHeight(node.height, height(rightOfRoot.right)) + 1;
        
        return newRoot;
    }

    private Node rightRotation(Node node) {
        Node leftOfRoot = node.left;
        Node rightOfLeftOfRoot = leftOfRoot.right;
        
        Node newRoot = leftOfRoot;
        newRoot.right = node;
        node.left = rightOfLeftOfRoot;

        node.height = maxHeight(height(node.left), height(node.right)) + 1;
        newRoot.height = maxHeight(height(leftOfRoot.left), node.height) + 1;
        
        return newRoot;
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
            if (balanceFactor(root) > 1 || balanceFactor(root) < -1) {
                if (key < root.left.key) {
                	//LL-Rotation
                	System.out.print(">> at " + root.key +"(BF:"+ balanceFactor(root) + ")");
                	root = leftleftRotation(root);
                } else {
                	//LR-Rotation
                	System.out.print(">> at " + root.key +"(BF:"+ balanceFactor(root) + ")");
                	root = leftRightRotation(root); 
                }
            }
        } else if (key > root.key) {
        	
        	root.right = insert(key, root.right);
            if (balanceFactor(root) > 1 || balanceFactor(root) < -1) {
                if (key > root.right.key) {
                	//RR-Rotation
                	System.out.print(">> at " + root.key +"(BF:"+ balanceFactor(root) + ")");
                	root = rightrightRotation(root);
                } else {
                    //RL-Rotation
                	System.out.print(">> at " + root.key +"(BF:"+ balanceFactor(root) + ")");
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
    
    private int balanceFactor(Node node) {
    	if (node == null) return 0;
    	return (height(node.right)-height(node.left));
    }

	public void delete(int key) {
		if (this.root != null && search(root, key) == true) {
	    	this.root = delete(root, key);
	    	System.out.println("After Delete node " + key);
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
        else {
        	// ohne Kind oder nur ein Kind
        	if ((node.left == null) || (node.right == null)) {
        		// speichern den zu löschenden Knoten
                Node temp = null; 
                
                if (node.left != null) //mit linkes Kind
                	temp = node.left; 
                else if (node.right != null) //mit rechtes Kind
                	temp = node.right; 
                
                if (temp == null) { //ohne Kind
                    temp = node;
                    node = null;
                } else {
                	node = temp;
                }
                temp = null;
            } else { //mit zwei Kindern

                Node temp = minKeyNode(node.right);
                node.key = temp.key;
                node.right = delete(node.right, temp.key);
            }
        } 
        if (node == null) return node;
        //Todo
        node.height = maxHeight(height(node.left), height(node.right)) + 1;
        
        if (balanceFactor(node) > 1 && balanceFactor(node.left) >= 0) 
        	return rightRotation(node);
        
        if (balanceFactor(node) > 1 && balanceFactor(node.left) < 0) {
            node.left = leftRotation(node.left);
            return rightRotation(node);
        } 
        
        if (balanceFactor(node) < -1 && balanceFactor(node.right) <= 0)
            return leftRotation(node);
        
        if (balanceFactor(node) < -1 && balanceFactor(node.right) > 0) {
            node.right = rightRotation(node.right);
            return leftRotation(node);
        }
        
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
    	return (node == null) ? 0 : node.height;
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