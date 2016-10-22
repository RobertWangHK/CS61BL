
/** A class implementing a BST.
  * @author CS 61BL Staff.
  */
public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {
	
	/* Constructs an empty BST. */
	public BinarySearchTree() {
		//YOUR CODE HERE
	}

	/* Constructs a BST with root MYROOT. */
	public BinarySearchTree(TreeNode root) {
		//YOUR CODE HERE
	}
	
	/* Returns true if and only if KEY is in the BST. */
	public boolean contains(T key) {
		//YOUR CODE HERE
		if(root==null){
			return false;
		}
		else{
			return containsHelper(key, root);
		}
	}
	public boolean containsHelper(T key, TreeNode node){
		if(node==null){
			return false;
		}
		if(node.item.compareTo(key)==0){
			return true;
		}
		else if(node.item.compareTo(key)<0){
			return containsHelper(key, node.right);
		}
		else{
			return containsHelper(key, node.left);
		}
		//return false;
	}
	
	/* Adds a node for KEY iff it isn't in the BST already. */
	public void add(T key) {
		//YOUR CODE HERE
		if(contains(key)){
			//throw new RuntimeException("already exists");
		}
		else{
			if(root==null){
				root = new TreeNode(key);
			}
			else{
				root = addHelper(key, root);
			}
		}
	}
	public TreeNode addHelper(T key, TreeNode node){
		if(node == null){
			node = new TreeNode(key);
			return node;
		}
		else{
			if(node.item.compareTo(key)<0){
				node.right = addHelper(key, node.right);
			}
			else{
				node.left=addHelper(key, node.left);
			}
			return node;
		}
	}
	
	/* Deletes the node with KEY. */
	public T delete(T key) {
		TreeNode parent = null;
		TreeNode curr = root;
		TreeNode delNode = null;
		TreeNode replacement = null;
		boolean rightSide = false;
		
		while (curr != null && !curr.item.equals(key)) {
			if (((Comparable<T>) curr.item).compareTo(key) > 0) {
				parent = curr;
				curr = curr.left;
				rightSide = false;
			} else {
				parent = curr;
				curr = curr.right;
				rightSide = true;
			}
		}
		delNode = curr;
		if (curr == null) {
			return null;
		}
		
		if (delNode.right == null) {
			if (root == delNode) {
				root = root.left;
			} else {
				if (rightSide) {
					parent.right = delNode.left;
				} else {
					parent.left = delNode.left;
				}
			}
		} else {
			curr = delNode.right;
			replacement = curr.left;
			if (replacement == null) {
				replacement = curr;
			} else {
				while (replacement.left != null) {
					curr = replacement;
					replacement = replacement.left;
				}
				curr.left = replacement.right;
				replacement.right = delNode.right;
			}
			replacement.left = delNode.left;
			if (root == delNode) {
				root = replacement;
			} else {
				if (rightSide) {
					parent.right = replacement;
				} else {
					parent.left = replacement;
				}
			}
		}
		return delNode.item;
	}
	
}