import java.util.ArrayList;

/** A Generic heap class. Unlike Java's priority queue, this heap doesn't just
  * store Comparable objects. Instead, it can store any type of object
  * (represented by type T) and an associated priority value.
  * @author CS 61BL Staff */
public class ArrayHeap<T> {

	/* DO NOT CHANGE THESE METHODS. */

	/* An ArrayList that stores the nodes in this binary heap. */
	private ArrayList<Node> contents;

	/* A constructor that initializes an empty ArrayHeap. */
	public ArrayHeap() {
		contents = new ArrayList<>();
		contents.add(null);
	}

	/* Returns the node at index INDEX. */
	private Node getNode(int index) {
		if (index >= contents.size()) {
			return null;
		} else {
			return contents.get(index);
		}
	}

	private void setNode(int index, Node n) {
		// In the case that the ArrayList is not big enough
		// add null elements until it is the right size
		while (index + 1 >= contents.size()) {
			contents.add(null);
		}
		contents.set(index, n);
	}

	/* Swap the nodes at the two indices. */
	private void swap(int index1, int index2) {
		Node node1 = getNode(index1);
		Node node2 = getNode(index2);
		this.contents.set(index1, node2);
		this.contents.set(index2, node1);
	}

	/* Prints out the heap sideways. Use for debugging. */
	@Override
	public String toString() {
		return toStringHelper(1, "");
	}

	/* Recursive helper method for toString. */
	private String toStringHelper(int index, String soFar) {
		if (getNode(index) == null) {
			return "";
		} else {
			String toReturn = "";
			int rightChild = getRightOf(index);
			toReturn += toStringHelper(rightChild, "        " + soFar);
			if (getNode(rightChild) != null) {
				toReturn += soFar + "    /";
			}
			toReturn += "\n" + soFar + getNode(index) + "\n";
			int leftChild = getLeftOf(index);
			if (getNode(leftChild) != null) {
				toReturn += soFar + "    \\";
			}
			toReturn += toStringHelper(leftChild, "        " + soFar);
			return toReturn;
		}
	}

	/* A Node class that stores items and their associated priorities. */
	public class Node {
		private T item;
		private double priority;

		private Node(T item, double priority) {
			this.item = item;
			this.priority = priority;
		}

		public T item(){
			return this.item;
		}

		public double priority() {
			return this.priority;
		}

		@Override
		public String toString() {
			return this.item.toString() + ", " + this.priority;
		}
	}

	/*
	* heapify, when goes for index i, maybe still need to swap in lower parts.
	* we can use a min or max heap to realize a priority queue.
	* */

	/* FILL IN THE METHODS BELOW. */

	/* Returns the index of the node to the left of the node at i. */
	private int getLeftOf(int i) {
		if(i*2<contents.size()){
			return i*2;
		}
		return 0;
	}

	/* Returns the index of the node to the right of the node at i. */
	private int getRightOf(int i) {
		//YOUR CODE HERE
		if(i*2+1<contents.size()){
			return i*2+1;
		}
		return 0;
	}

	/* Returns the index of the node that is the parent of the node at i. */
	private int getParentOf(int i) {
		if(i!=1){
			return i/2;
		}
		return 0;
	}

	/* Adds the given node as a left child of the node at the given index. */
	private void setLeft(int index, Node n) {
		//YOUR CODE HERE
		int left = getLeftOf(index);
		if(left!=0){
			setNode(left,n);
		}
	}

	/* Adds the given node as the right child of the node at the given index. */
	private void setRight(int index, Node n) {
		//YOUR CODE HERE
		int right = getRightOf(index);
		if(right!=0){
			setNode(right,n);
		}
	}

	/** Returns the index of the node with smaller priority. Precondition: not
	  * both nodes are null. */
	private int min(int index1, int index2) {
		//YOUR CODE HERE
		if(getNode(index1)==null){
			return index2;
		}
		if(getNode(index2)==null){
			return index1;
		}
		Node a = getNode(index1);
		Node b = getNode(index2);
		if(a.priority()<b.priority()){
			return index1;
		}
		return index2;
	}

	/* Returns the Node with the smallest priority value, but does not remove it
	 * from the heap. */
	public Node peek() {
		//YOUR CODE HERE
		Node smallest = new Node(getNode(1).item(), getNode(1).priority());
		return smallest;
	}

	/* Bubbles up the node currently at the given index. */
	private void bubbleUp(int index) {
		//YOUR CODE HERE
		while(index>1&&getParentOf(index)!=0){
			if(min(index, getParentOf(index))==index){
				swap(index, getParentOf(index));
				bubbleDown(index);
				index = getParentOf(index);
				continue;
			}
			break;
		}
	}

	/* Bubbles down the node currently at the given index. */
	private void bubbleDown(int index) {
		//YOUR CODE HERE
		while(index<contents.size()&&(getLeftOf(index)!=0||getRightOf(index)!=0)){
			int child = min(getLeftOf(index), getRightOf(index));
			if(min(child, index)==child){
				swap(index, child);
				bubbleUp(index);
				index = child;
				continue;
			}
			break;
		}
	}

	/* Inserts an item with the given priority value. Same as enqueue, or offer. */
	public void insert(T item, double priority) {
		//YOUR CODE HERE
		Node input = new Node(item, priority);
		//setNode(contents.size(),input);
		contents.add(input);
		bubbleUp(contents.size()-1);
	}

	/* Returns the Node with the smallest priority value, and removes it from
	 * the heap. Same as dequeue, or poll. */
	public Node removeMin() {
		//YOUR CODE HERE
		Node first = new Node(getNode(1).item(),getNode(1).priority());
		swap(1,contents.size()-1);
		contents.remove(getNode(contents.size()-1));
		bubbleDown(1);
		return first;
	}

	/* Changes the node in this heap with the given item to have the given
	 * priority. You can assume the heap will not have two nodes with the same
	 * item. Check for item equality with .equals(), not == */
	public void changePriority(T item, double priority) {
		//YOUR CODE HERE
		Node newNode = new Node(item,priority);
		int index =0;
		for(int i=1; i<contents.size(); i++){
			Node a = contents.get(i);
			if(a.item().equals(item)){
				index = contents.indexOf(a);
				//setNode(index, newNode);
				contents.set(index,newNode);
				break;
			}
		}
		if(index==0){
			return;
		}
		else if(getParentOf(index)!=0&&min(index,getParentOf(index))==index){
			bubbleUp(index);
		}
		else if(index*2<contents.size()&&(getRightOf(index)!=0||getLeftOf(index)!=0)){
			int mini = min(getLeftOf(index), getRightOf(index));
			if(min(mini,index)==mini){
				bubbleDown(index);
			}
		}
	}

	public void Print(){
		System.out.println(contents);
	}

	public static void main(String[] args) {
		ArrayHeap<Double> arr = new ArrayHeap<>();
		arr.insert(3.0, 5);
		arr.insert(4.0, 2);
		arr.insert(2.0, 3);
		//arr.changePriority(2.0, 5);
		System.out.println(arr.peek());
		//arr.removeMin();
		arr.Print();
	}
}
