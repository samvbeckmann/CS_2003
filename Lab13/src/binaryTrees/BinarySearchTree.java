package BinaryTrees;

import java.util.Iterator;

// ADT binary search tree.
//  Assumption: A tree contains at most one item

public class BinarySearchTree<E extends Comparable<? super E>> extends
		BinaryTreeBasis<E> {
	// inherits isEmpty(), makeEmpty(), getRootItem(), and
	// the use of the constructors from BinaryTreeBasis

	public BinarySearchTree() {
	} // end default constructor

	public BinarySearchTree(E rootItem) {
		super(rootItem);
	} // end constructor

	public void insert(E newItem) {
		root = insertItem(root, newItem);
	} // end insert

	public void delete(E searchKey) throws TreeException {
		root = deleteItem(root, searchKey);
	} // end delete

	protected TreeNode<E> insertItem(TreeNode<E> tNode, E newItem) {
		TreeNode<E> newSubtree;
		if (tNode == null) {
			// position of insertion found; insert after leaf
			// create a new node
			tNode = new TreeNode<E>(newItem, null, null);
			return tNode;
		} // end if
		E nodeItem = tNode.getItem();

		// search for the insertion position
		if (newItem.compareTo(nodeItem) < 0) {
			// search the left subtree
			newSubtree = insertItem(tNode.getLeft(), newItem);
			tNode.setLeft(newSubtree);
			return tNode;
		} else { // search the right subtree
			newSubtree = insertItem(tNode.getRight(), newItem);
			tNode.setRight(newSubtree);
			return tNode;
		} // end if
	} // end insertItem

	protected E retrieveItem(TreeNode<E> tNode, E searchKey) {
		E treeItem;
		if (tNode == null) {
			treeItem = null;
		} else {
			E nodeItem = tNode.getItem();
			if (searchKey.compareTo(nodeItem) == 0) {
				// item is in the root of some subtree
				treeItem = tNode.getItem();
			} else if (searchKey.compareTo(nodeItem) < 0) {
				// search the left subtree
				treeItem = retrieveItem(tNode.getLeft(), searchKey);
			} else { // search the right subtree
				treeItem = retrieveItem(tNode.getRight(), searchKey);
			} // end if
		} // end if
		return treeItem;
	} // end retrieveItem

	protected TreeNode<E> deleteItem(TreeNode<E> tNode, E searchKey) {
		// Calls: deleteNode.
		TreeNode<E> newSubtree;
		if (tNode == null) {
			throw new TreeException("TreeException: Item not found");
		} else {
			E nodeItem = tNode.getItem();
			if (searchKey.compareTo(nodeItem) == 0) {
				// item is in the root of some subtree
				tNode = deleteNode(tNode); // delete the item
			}
			// else search for the item
			else if (searchKey.compareTo(nodeItem) < 0) {
				// search the left subtree
				newSubtree = deleteItem(tNode.getLeft(), searchKey);
				tNode.setLeft(newSubtree);
			} else { // search the right subtree
				newSubtree = deleteItem(tNode.getRight(), searchKey);
				tNode.setRight(newSubtree);
			} // end if
		} // end if
		return tNode;
	} // end deleteItem

	protected TreeNode<E> deleteNode(TreeNode<E> tNode) {
		// Algorithm note: There are four cases to consider:
		//   1. The tNode is a leaf.
		//   2. The tNode has no left child.
		//   3. The tNode has no right child.
		//   4. The tNode has two children.
		// Calls: findLeftmost and deleteLeftmost
		E replacementItem;

		// test for a leaf
		if ((tNode.getLeft() == null) && (tNode.getRight() == null)) {
			return null;
		} // end if leaf

		// test for no left child
		else if (tNode.getLeft() == null) {
			return tNode.getRight();
		} // end if no left child

		// test for no right child
		else if (tNode.getRight() == null) {
			return tNode.getLeft();
		} // end if no right child

		// there are two children:
		// retrieve and delete the inorder successor
		else {
			replacementItem = findLeftmost(tNode.getRight());
			tNode.setItem(replacementItem);
			tNode.setRight(deleteLeftmost(tNode.getRight()));
			return tNode;
		} // end if
	} // end deleteNode

	protected E findLeftmost(TreeNode<E> tNode) {
		if (tNode.getLeft() == null) {
			return tNode.getItem();
		} else {
			return findLeftmost(tNode.getLeft());
		} // end if
	} // end findLeftmost

	protected TreeNode<E> deleteLeftmost(TreeNode<E> tNode) {
		if (tNode.getLeft() == null) {
			return tNode.getRight();
		} else {
			tNode.setLeft(deleteLeftmost(tNode.getLeft()));
			return tNode;
		} // end if
	} // end deleteLeftmost

	public Iterator<E> iterator() {
		return new TreeIterator<E>(this);
	}

	public Iterator<E> iterator(int traverseType) {
		return new TreeIterator<E>(this, traverseType);
	}

} // end BinarySearchTree
