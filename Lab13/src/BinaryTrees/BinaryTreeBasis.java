package BinaryTrees;

public abstract class BinaryTreeBasis<E> implements Iterable<E> {
	protected TreeNode<E> root;

	/** 
	 * default constructor, the root of the tree is set to null.
	 */
	public BinaryTreeBasis() {
		root = null;
	} // end default constructor

	/** constructor, the root of the tree will contain the specified item, and there
	 * is no children
	 * @param rootItem item at the top of the tree.
	 */
	public BinaryTreeBasis(E rootItem) {
		root = new TreeNode<E>(rootItem, null, null);
	} // end constructor

	/**
	 * Determines whether a tree is empty.
	 * @return true if the tree is empty, else returns false.
	 */
	public boolean isEmpty() {
		return root == null;
	} // end isEmpty

	/**
	 * Removes all nodes from the tree.
	 */
	public void makeEmpty() {
		root = null;
	} // end makeEmpty

	/**
	 * accesses the top of the tree.
	 * @return the item in the tree's root.
	 * @throws TreeException when the tree is empty.
	 */
	public E getRootItem() throws TreeException {
		if (root == null) {
			throw new TreeException("TreeException: Empty tree");
		} else {
			return root.getItem();
		} // end if
	} // end getRootItem

} // end BinaryTreeBasis
