public interface SortedListInterface<E extends Comparable<? super E>>
    extends BasicListInterface<E> {

    /** adds a new item in the sorted list.
     * @param newItem new item to be inserted in the list.
     * @throws ListException
     */
    public void sortedAdd(E newItem)
	throws ListException;
    
    /** remove the first occurrence of the specified item from the list
     * @param anItem is the item to be remove
     */
    public void sortedRemove(E anItem);
    
    /** locate the index of the specified element in the list. NB the
     * index of a list starts at index 1.
     * @param anItem is the item which index is wanted.
     */
    public int locateIndex(E anItem);

}  // end SortedListInterface
