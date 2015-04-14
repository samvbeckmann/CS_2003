package BinaryTrees;

import java.util.LinkedList;
import java.util.Iterator;

public class TreeIterator<E> implements Iterator<E> {
	/** Binary Tree associated with the iterator. */
	private BinaryTreeBasis<E> binTree;

	/** list to store the elements to go through */
	private LinkedList<E> list;

	/** offset for printing the elements in the console */
	private static final String OFFSET = "       ";

	/** code to use in order traversal of the tree */
	public final static int IN_ORDER = 0;

	/** code to use pre order traversal of the tree */
	public final static int PRE_ORDER = 1;

	/** code to use post order traversal of the tree */
	public final static int POST_ORDER = 2;

	/**
	 * Constructor: the defaut order traversal is in order.
	 * 
	 * @param bTree
	 *            is the binary tree that will be traversed
	 */
	public TreeIterator(BinaryTreeBasis<E> bTree) {
		binTree = bTree;
		// empty queue indicates no traversal type currently
		// selected or end of current traversal has been reached
		list = new LinkedList<E>();
		// default inorder traversal
		setInorder();
	} // end constructor

	/**
	 * Constructor: the iterator will traverse the tree with the specified
	 * traverse order
	 * 
	 * @param bTree
	 *            is the binary tree that will be traversed
	 * @param traversalType
	 *            is the code corresponding the type of order traversal:
	 *            <ul>
	 *            <li> IN_ORDER: in order traversal
	 *            <li> PRE_ORDER: pre order traversal
	 *            <li> POS_ORDER: post order traversal
	 *            </ul>
	 */
	public TreeIterator(BinaryTreeBasis<E> bTree, int traversalType) {
		binTree = bTree;
		// empty queue indicates no traversal type currently
		// selected or end of current traversal has been reached
		list = new LinkedList<E>();
		switch (traversalType) {
		case IN_ORDER:
			setInorder();
			break;
		case PRE_ORDER:
			setPreorder();
			break;
		case POST_ORDER:
			setPostorder();
			break;
		}
	} // end constructor

	/** set the order of traversal to pre order */
	public void setPreorder() {
		list.clear();
		preorder(binTree.root);
	} // setPreOrder

	/** set the order of traversal to in order */
	public void setInorder() {
		list.clear();
		inorder(binTree.root);
	} // end setInorder

	/** set the order of traversal to post order */
	public void setPostorder() {
		list.clear();
		postorder(binTree.root);
	} // end setPostorder

	/**
	 * visit the tree and store its items in pre order in the list
	 * <code>list</code>
	 * 
	 * @param treeNode
	 *            is the current node of the tree.
	 */
	private void preorder(TreeNode<E> treeNode) {
		if (treeNode != null) {
			list.add(treeNode.getItem());
			preorder(treeNode.getLeft());
			preorder(treeNode.getRight());
		} // end if
	} // end preorder

	/**
	 * visit the tree and store its items in order in the list <code>list</code>
	 * 
	 * @param treeNode
	 *            is the current node of the tree.
	 */
	private void inorder(TreeNode<E> treeNode) {
		if (treeNode != null) {
			inorder(treeNode.getLeft());
			list.add(treeNode.getItem());
			inorder(treeNode.getRight());
		} // end if
	} // end inorder

	/**
	 * visit the tree and store its items in post order in the list
	 * <code>list</code>
	 * 
	 * @param treeNode
	 *            is the current node of the tree.
	 */
	private void postorder(TreeNode<E> treeNode) {
		if (treeNode != null) {
			postorder(treeNode.getLeft());
			postorder(treeNode.getRight());
			list.add(treeNode.getItem());
		} // end if
	} // end postorder

	/**
	 * print the items of the tree using pre order (note that it only prints,
	 * the list of item remains unchanged)
	 */
	public void printPreorder() {
		System.out.print("Root");
		preorderPrint(binTree.root, "", ' ');
	} // printPreOrder

	/**
	 * print the items of the tree using in order (note that it only prints, the
	 * list of item remains unchanged)
	 */
	public void printInorder() {
		System.out.print("Root");
		inorderPrint(binTree.root, "", ' ');
	} // end printInorder

	/**
	 * print the items of the tree using post order (note that it only prints,
	 * the list of item remains unchanged)
	 */
	public void printPostorder() {
		System.out.print("Root");
		postorderPrint(binTree.root, "", ' ');
	} // end printPostorder

	private void preorderPrint(TreeNode<E> treeNode, String space,
			char whichChild) {
		if (treeNode != null) {
			System.out.println(space + whichChild + ":" + treeNode.getItem());
			preorderPrint(treeNode.getLeft(), space + OFFSET, 'L');
			preorderPrint(treeNode.getRight(), space + OFFSET, 'R');
		} // end if
	} // end preorder

	private void inorderPrint(TreeNode<E> treeNode, String space,
			char whichChild) {
		if (treeNode != null) {
			inorderPrint(treeNode.getLeft(), space + OFFSET, 'L');
			System.out.println(space + whichChild + ":" + treeNode.getItem());
			inorderPrint(treeNode.getRight(), space + OFFSET, 'R');
		} // end if
	} // end inorder

	private void postorderPrint(TreeNode<E> treeNode, String space,
			char whichChild) {
		if (treeNode != null) {
			postorderPrint(treeNode.getLeft(), space + OFFSET, 'L');
			postorderPrint(treeNode.getRight(), space + OFFSET, 'R');
			System.out.println(space + whichChild + ":" + treeNode.getItem());
		} // end if
	} // end postorder

	/**
	 * Returns true if the iteration has more elements. (In other words, returns
	 * true if next would return an element rather than throwing an exception.)
	 * 
	 * @return true if the iterator has more elements.
	 */
	public boolean hasNext() {
		return !list.isEmpty();
	}

	/**
	 * Returns the next element in the iteration. Calling this method repeatedly
	 * until the hasNext() method returns false will return each element in the
	 * underlying collection exactly once.
	 * 
	 * @return the next element in the iteration.
	 * @throws NoSuchElementException -
	 *             iteration has no more elements.
	 */
	public E next() throws java.util.NoSuchElementException {
		return list.remove();
	}

	/**
	 * Removes from the underlying collection the last element returned by the
	 * iterator (optional operation). This method can be called only once per
	 * call to next. The behavior of an iterator is unspecified if the
	 * underlying collection is modified while the iteration is in progress in
	 * any way other than by calling this method.
	 * 
	 * @throws UnsupportedOperationException -
	 *             if the remove operation is not supported by this Iterator.
	 */

	public void remove() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

} // end TreeIterator
