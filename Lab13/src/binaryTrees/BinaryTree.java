package BinaryTrees;

import java.util.Iterator;

public class BinaryTree<E> extends BinaryTreeBasis<E> {
	public BinaryTree() {
	} // end default constructor

	public BinaryTree(E rootItem) {
		super(rootItem);
	} // end constructor

	public BinaryTree(E rootItem, BinaryTree<E> leftTree,
			BinaryTree<E> rightTree) {
		root = new TreeNode<E>(rootItem, null, null);
		attachLeftSubtree(leftTree);
		attachRightSubtree(rightTree);
	} // end constructor

	public void setRootItem(E newItem) {
		if (root != null) {
			root.setItem(newItem);
		} else {
			root = new TreeNode<E>(newItem, null, null);
		} // end if
	} // end setRootItem

	public void attachLeft(E newItem) {
		if (!isEmpty() && root.getLeft() == null) {
			// assertion: nonempty tree; no left child
			root.setLeft(new TreeNode<E>(newItem, null, null));
		} // end if
	} // end attachLeft

	public void attachRight(E newItem) {
		if (!isEmpty() && root.getRight() == null) {
			// assertion: nonempty tree; no right child
			root.setRight(new TreeNode<E>(newItem, null, null));
		} // end if
	} // end attachRight

	public void attachLeftSubtree(BinaryTree<E> leftTree) throws TreeException {
		if (isEmpty()) {
			throw new TreeException("TreeException:  Empty tree");
		} else if (root.getLeft() != null) {
			// a left subtree already exists; it should have been 
			// deleted first
			throw new TreeException("TreeException: "
					+ "Cannot overwrite left subtree");
		} else {
			// assertion: nonempty tree; no left child
			root.setLeft(leftTree.root);
			// don't want to leave multiple entry points into 
			// our tree
			leftTree.makeEmpty();
		} // end if
	} // end attachLeftSubtree

	public void attachRightSubtree(BinaryTree<E> rightTree)
			throws TreeException {
		if (isEmpty()) {
			throw new TreeException("TreeException:  Empty tree");
		} else if (root.getRight() != null) {
			// a right subtree already exists; it should have been 
			// deleted first
			throw new TreeException("TreeException: "
					+ "Cannot overwrite right subtree");
		} else {
			// assertion: nonempty tree; no right child
			root.setRight(rightTree.root);
			// don't want to leave multiple entry points into 
			// our tree
			rightTree.makeEmpty();
		} // end if
	} // end attachRightSubtree

	protected BinaryTree(TreeNode<E> rootNode) {
		root = rootNode;
	} // end protected constructor

	public BinaryTree<E> detachLeftSubtree() throws TreeException {
		if (isEmpty()) {
			throw new TreeException("TreeException:  Empty tree");
		} else {
			// create a new binary tree that has root's left 
			// node as its root
			BinaryTree<E> leftTree;
			leftTree = new BinaryTree<E>(root.getLeft());
			root.setLeft(null);
			return leftTree;
		} // end if
	} // end detachLeftSubtree

	public BinaryTree<E> detachRightSubtree() throws TreeException {
		if (isEmpty()) {
			throw new TreeException("TreeException:  Empty tree");
		} else {
			BinaryTree<E> rightTree;
			rightTree = new BinaryTree<E>(root.getRight());
			root.setRight(null);
			return rightTree;
		} // end if
	} // end detachRightSubtree

	public Iterator<E> iterator() {
		return new TreeIterator<E>(this);
	}

} // end BinaryTree
