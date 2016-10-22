import java.util.*;

public class BST {
    BSTNode root;

    public BST(LinkedList list) {
        root = linkedListToTree(list.iterator(), list.size());
    }

    // Your comment here
    private BSTNode linkedListToTree(Iterator iter, int n) {
        // YOUR CODE HERE
        if (n <= 0) {
            return null;
        }else if (n == 1) {
            BSTNode node = new BSTNode();
            node.item = iter.next();
            return node;
        }
        BSTNode left = linkedListToTree(iter, n / 2);
        BSTNode node = new BSTNode();
        node.item = iter.next();
        node.left = left;
        node.right = linkedListToTree(iter, n - 1 - n / 2);
        return node;
    }

    /**
     * Prints the tree to the console.
     */
    private void print() {
        print(root, 0);
    }

    private void print(BSTNode node, int d) {
        if (node == null) {
            return;
        }
        for (int i = 0; i < d; i++) {
            System.out.print("  ");
        }
        System.out.println(node.item);
        print(node.left, d + 1);
        print(node.right, d + 1);
    }

    /**
     * Node for BST.
     */
    static class BSTNode {

        /**
         * Item.
         */
        Object item;

        /**
         * Left child.
         */
        BSTNode left;

        /**
         * Right child.
         */
        BSTNode right;
    }
}
