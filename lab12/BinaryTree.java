/**
 * A BinaryTree is a tree with nodes that have up to two children.
 */
public class BinaryTree {

    /**
     * root is the root of this BinaryTree
     */
    private TreeNode root;

    /**
     * The BinaryTree constructor
     */
    public BinaryTree() {
        root = null;
    }

    public BinaryTree(TreeNode t) {
        root = t;
    }

    /**
     * Print the values in the tree in preorder: root value first, then values
     * in the left subtree (in preorder), then values in the right subtree
     * (in preorder).
     */
    public void printPreorder() {
        if (root == null) {
            System.out.println("(empty tree)");
        } else {
            root.printPreorder();
            System.out.println();
        }
    }

    /**
     * Print the values in the tree in inorder: values in the left subtree
     * first (in inorder), then the root value, then values in the first
     * subtree (in inorder).
     */
    public void printInorder() {
        if (root == null) {
            System.out.println("(empty tree)");
        } else {
            root.printInorder();
            System.out.println();
        }
    }

    /**
     * Fills this BinaryTree with values a, b, and c
     */
    public void fillSampleTree1() {
        root = new TreeNode("a", new TreeNode("b"), new TreeNode("c"));
    }

    /**
     * Fills this BinaryTree with values a, b, and c, d, e, f
     */
    public void fillSampleTree2() {
        root = new TreeNode("a", new TreeNode("b", new TreeNode("d",
            new TreeNode("e"), new TreeNode("f")), null), new TreeNode("c"));
    }
    public void fillSampleTree3() {
        root = new TreeNode("a", new TreeNode("b",null,null),
                new TreeNode("c", new TreeNode("d", new TreeNode("e"), new TreeNode("f")), null));
    }
    public int height(){
        if(root==null){
            return 0;
        }
        else{
            return root.height()+1;
        }
    }
    public boolean isCompletelyBalanced(){
        if(root==null){
            return true;
        }
        else{
            return root.balance();
        }
    }
    /**
     * Creates two BinaryTrees and prints them out in inorder
     */
    public static void main(String[] args) {
        BinaryTree t;
        t = new BinaryTree();
        t.fillSampleTree3();
        print(t, "sample tree 3");
        System.out.print(t.height());
        System.out.print(t.isCompletelyBalanced());
    }

    /**
     * Prints out the contents of a BinaryTree with a description in both
     * preorder and inorder
     * @param t           the BinaryTree to print out
     * @param description a String describing the BinaryTree t
     */
    private static void print(BinaryTree t, String description) {
        System.out.println(description + " in preorder");
        t.printPreorder();
        System.out.println(description + " in inorder");
        t.printInorder();
        System.out.println();
    }

    /**
     * A TreeNode is a Node this BinaryTree
     */
    private static class TreeNode {

        /**
         * item is the item that is contained in this TreeNode
         * left is the left child of this TreeNode
         * right is the right child of this TreeNode
         */
        public Object item;
        public TreeNode left;
        public TreeNode right;

        /**
         * A TreeNode constructor that creates a node with obj as its item
         * @param  obj the item to be contained in this TreeNode
         */
        TreeNode(Object obj) {
            item = obj;
            left = null;
            right = null;
        }

        /**
         * A Treenode constructor that creates a node with obj as its item and
         * left and right as its children
         * @param  obj   the item to be contained in this TreeNode
         * @param  left  the left child of this TreeNode
         * @param  right the right child of this TreeNode
         */
        TreeNode(Object obj, TreeNode left, TreeNode right) {
            item = obj;
            this.left = left;
            this.right = right;
        }

        /**
         * Prints this TreeNode and the subtree rooted at it in preorder
         */
        private void printPreorder() {
            System.out.print(item + " ");
            if (left != null) {
                left.printPreorder();
            }
            if (right != null) {
                right.printPreorder();
            }
        }

        /**
         * Prints this TreeNode and the subtree rooted at it in inorder
         */
        private void printInorder() {
            if (left != null) {
                left.printInorder();
            }
            System.out.print(item + " ");
            if (right != null) {
                right.printInorder();
            }
        }
        public int height(){
            if(left==null&&right==null){
                return 0;
            }
            else{
                if(left==null){
                    return 1+right.height();
                }
                else if(right == null){
                    return 1+left.height();
                }
                else{
                    return 1 + left.height()+right.height();
                }
            }
            //return 1 + left.height()+right.height();
        }
        public boolean balance(){
            if(left==null&&right==null){
                return true;
            }
            else if(left!=null&&right==null){
                return false;
            }
            else if(left==null&&right!=null){
                return false;
            }
            else{
                return left.balance()&&right.balance()&&left.height()==right.height();
            }
            //return 1 + left.height()+right.height();
        }
    }
}
