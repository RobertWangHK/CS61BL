import jh61b.junit.In;

/** A data structure to represent a Linked List of Integers.
  * Each IntList represents one node in the overall Linked List.
  *
  * @author Maurice Lee and Wan Fung Chui
  */

public class IntList {

    /**
     * The integer stored by this node.
     */
    private int item;
    /**
     * The next node in this IntList.
     */
    private IntList next;

    /**
     * Constructs an IntList storing ITEM and next node NEXT.
     */
    public IntList(int item, IntList next) {
        this.item = item;
        this.next = next;
    }

    /**
     * Constructs an IntList storing ITEM and no next node.
     */
    public IntList(int item) {
        this(item, null);
    }

    /**
     * Returns an IntList consisting of the elements in ITEMS.
     * IntList L = IntList.list(1, 2, 3);
     * System.out.println(L.toString()) // Prints (1 2 3)
     */
    public static IntList list(int... items) {
        /** Check for cases when we have no element given. */
        if (items.length == 0) {
            return null;
        }
        /** Create the first element. */
        IntList head = new IntList(items[0]);
        IntList last = head;
        /** Create rest of the list. */
        for (int i = 1; i < items.length; i++) {
            last.next = new IntList(items[i]);
            last = last.next;
        }
        return head;
    }

    /**
     * Returns the integer stored by this IntList.
     */
    public int item() {
        return item;
    }

    /**
     * Returns the next node stored by this IntList.
     */
    public IntList next() {
        return next;
    }

    /**
     * Returns [position]th item in this list. Throws IllegalArgumentException
     * if index out of bounds.
     *
     * @param position, the position of element.
     * @return The element at [position]
     */
    public int get(int position) {
        // YOUR CODE HERE
        if (position < 0 || position >= this.size()) {
            throw new IllegalArgumentException("Out of Range");
        }
        IntList r = this;
        for (int i = 0; i < position; i++) {
            r = r.next;
        }
        return r.item;
    }

    /**
     * Returns the size of the list.
     *
     * @return The size of the list.
     */
    public int size() {
        // YOUR CODE HERE
        int n = 1;
        IntList r = this;
        while (r.next != null) {
            r = r.next;
            n++;
        }
        return n;
    }

    /**
     * Returns the string representation of the list. For the list (1, 2, 3),
     * returns "( 1 2 3 )".
     *
     * @return The String representation of the list.
     */
    public String toString() {
        // YOUR CODE HERE
        int[] arr = new int[this.size()];
        String r = "( ";
        IntList p = this;
        int i = 1;
        arr[0] = this.item;
        while (p.next != null) {
            p = p.next;
            arr[i] = p.item;
            i++;
        }
        for (i = 0; i < arr.length; i++) {
            r += arr[i];
            r += " ";
        }
        r += ")";
        return r;
    }

    /**
     * Returns whether this and the given list or object are equal.
     *
     * @param obj, another list (object)
     * @return Whether the two lists are equal.
     */
    public boolean equals(Object obj) {
        // YOUR CODE HERE
        if (!(obj instanceof IntList)) {
            return false;
        }
        if (!(this.size() == ((IntList) obj).size())) {
            return false;
        }
        IntList r = this;
        while (r.next != null && ((IntList) obj).next != null) {
            if (r.item != ((IntList) obj).item) {
                return false;
            }
            r = r.next;
            obj = ((IntList) obj).next;
        }
        return true;
    }

    /**
     * Adds the given item at the end of the list.
     *
     * @param item, the int to be added.
     */
    public void add(int item) {
        // YOUR CODE HERE
        IntList r = new IntList(item);
        IntList p = this;
        while (p.next != null) {
            p = p.next;
        }
        p.next = r;
    }

    /**
     * Returns the smallest element in the list.
     *
     * @return smallest element in the list
     */
    public int smallest() {
        // YOUR CODE HERE
        int temp = this.item;
        IntList r = this;
        while (r.next != null) {
            temp = Math.min(temp, r.item);
            r = r.next;
        }
        temp = Math.min(temp, r.item);
        return temp;
    }

    /**
     * Returns the sum of squares of all elements in the list.
     *
     * @return The sum of squares of all elements.
     */
    public int squaredSum() {
        // YOUR CODE HERE
        int result = 0;
        IntList r = this;
        while (r.next != null) {
            result += r.item * r.item;
            r = r.next;
        }
        result += r.item * r.item;
        return result;
    }

    /**
     * Returns a new IntList consisting of L1 followed by L2,
     * non-destructively.
     *
     * @param l1 list to be on the front of the new list.
     * @param l2 list to be on the back of the new list.
     * @return new list with L1 followed by L2.
     */
    public static IntList append(IntList l1, IntList l2) {
        // YOUR CODE HERE
        if (l1 == null && l2 == null) {
            return null;
        } else if (l1 != null && l2 == null) {
            IntList r = new IntList(l1.item);
            IntList last = r;
            while (l1.next != null) {
                l1 = l1.next;
                IntList temp = new IntList(l1.item);
                last.next = temp;
                last = last.next;
            }
            return r;
        } else if (l2 != null && l1 == null) {
            IntList r = new IntList(l2.item);
            IntList last = r;
            while (l2.next != null) {
                l2 = l2.next;
                IntList temp = new IntList(l2.item);
                last.next = temp;
                last = last.next;
            }
            return r;
        } else {
            IntList r = new IntList(l1.item);
            IntList last = r;
            while (l1.next != null) {
                l1 = l1.next;
                IntList temp = new IntList(l1.item);
                last.next = temp;
                last = last.next;
            }
            while (l2.next != null) {
                IntList temp = new IntList(l2.item);
                l2 = l2.next;
                //IntList temp = new IntList(l1.item);
                last.next = temp;
                last = last.next;
            }
            IntList a = new IntList(l2.item);
            last.next = a;
            last = last.next;
            return r;
        }
    }
}