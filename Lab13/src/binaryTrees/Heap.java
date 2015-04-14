package BinaryTrees;

import java.util.Vector;

//package Heaps;

public class Heap<E extends Comparable<? super E>> {
	/** /maximum size of heap */
	private int MAX_HEAP = 100;

	/**  array of heap items */
	private Vector<E> items;

	/**
	 * default constructor
	 */
	public Heap() {
		items = new Vector<E>();
	} // end default constructor

	// heap operations:

	/**
	 * Determines whether a heap is empty.
	 * @returns Returns true if the heap is empty; otherwise returns false.
	 */
	public boolean heapIsEmpty() {
		return items.size() == 0;
	} // end heapIsEmpty

	/**
	 * Inserts an item into a heap. If the heap was not full, newItem is in its
	 * proper position; otherwise HeapException is thrown.
	 * @param newItem
	 *            is the item to be inserted.
	 * @throws HeapException
	 *             when the heap is full.
	 */
	public void heapInsert(E newItem) throws HeapException {

		if (items.size() < MAX_HEAP) {
			// place the new item at the end of the heap
			items.add(newItem);

			// trickle new item up to its proper position
			int place = items.size()-1;
			int parent = (place - 1) / 2;
			while ((parent >= 0)
					&& (items.get(place).compareTo(items.get(parent)) < 0)) {
				// swap items[place] and items[parent]
				E temp = items.get(parent);
				items.set(parent, items.get(place));
				items.set(place, temp);

				place = parent;
				parent = (place - 1) / 2;
			} // end while
		} // end if
		else {
			throw new HeapException("HeapException: Heap full");
		} // end if
	} // end heapInsert

	/**
	 * Retrieves and deletes the item in the root of a heap. This item has the
	 * largest search key in the heap. If the heap is not empty, returns the
	 * item in the root of the heap and then deletes it. However, if the heap is
	 * empty, removal is impossible and the method returns null.
	 * 
	 * @return the largest item
	 */
	public E heapDelete() {
		E rootItem = null;
		if (!heapIsEmpty()) {
			rootItem = items.remove(0);
			heapRebuild(0);
		} // end if
		return rootItem;
	} // end heapDelete

	/**
	 * if the root is not a leaf and the root's search key is larger than the
	 * smaller of the search keys in the root's children
	 * 
	 * @param root
	 */
	protected void heapRebuild(int root) {
		int child = 2 * root + 1; // index of root's left 
		// child, if any
		if (child < items.size()) {
			// root is not a leaf, so it has a left child at child
			int rightChild = child + 1; // index of right child, 
			// if any

			// if root has a right child, find smaller child ; SS: corrected comment 11/29/04
			if ((rightChild < items.size())
					&& (items.get(rightChild).compareTo(items.get(child)) < 0)) {
				child = rightChild; // index of larger child
			} // end if

			// if the root's value is smaller than the
			// value in the larger child, swap values
			if (items.get(root).compareTo(items.get(child)) > 0) {
				E temp = items.get(root);
				items.set(root, items.get(child));
				items.set(child, temp);
				// transform the new subtree into a heap
				heapRebuild(child);
			} // end if
		} // end if
		// if root is a leaf, do nothing
	} // end heapRebuild

} // end Heap
