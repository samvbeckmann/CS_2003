package BinaryTrees;

public class TreeNode<E> {
	/** item encapsulated by the TreeNode */
	private E item;

	/** left child (left subtree in fact) of the current node */
	private TreeNode<E> leftChild;

	/** right child (right subtree in fact) of the current node */
	private TreeNode<E> rightChild;

	/**
	 * Constructor: encapsulate the specified item in a node with no children.
	 * 
	 * @param newItem
	 *            item contained in the node
	 */
	public TreeNode(E newItem) {
		item = newItem;
		leftChild = null;
		rightChild = null;
	} // end constructor

	/**
	 * Constructor: encapsulate the specified item in a node with a left and
	 * right children
	 * 
	 * @param newItem
	 *            item contained in the node
	 * @param left
	 *            reference to the left subtree
	 * @param right
	 *            reference to the right subtree
	 */
	public TreeNode(E newItem, TreeNode<E> left, TreeNode<E> right) {
		item = newItem;
		leftChild = left;
		rightChild = right;
	} // end constructor

	/**
	 * accesses the item contained in the current node.
	 * 
	 * @return the item included in the node
	 */
	public E getItem() {
		return item;
	} // end getItem

	/**
	 * Sets the item field to the new value newItem.
	 * 
	 * @param newItem
	 *            new value of the item
	 */
	public void setItem(E newItem) {
		item = newItem;
	} // end setItem

	/**
	 * accesses the left subtree.
	 * 
	 * @return the reference to the left child.
	 */
	public TreeNode<E> getLeft() {
		return leftChild;
	} // end getLeft

	/**
	 * Sets the left child reference to left.
	 * 
	 * @param left
	 *            new left subtree of the current node.
	 */
	public void setLeft(TreeNode<E> left) {
		leftChild = left;
	} // end setLeft

	/**
	 * accesses the right subtree.
	 * 
	 * @return the reference to the right child.
	 */
	public TreeNode<E> getRight() {
		return rightChild;
	} // end getRight

	/**
	 * Sets the right child reference to left.
	 * 
	 * @param right
	 *            new right subtree of the current node.
	 */
	public void setRight(TreeNode<E> right) {
		rightChild = right;
	} // end setRight
} // end TreeNode
