import ListReferencedBased.ListIndexOutOfBoundsException;

public interface BasicListInterface<E> {

    /** Tests if this list has no elements.
     * @return <code>true</code> if this list has no elements;
     * <code>false</code> otherwise.
     */
    public boolean isEmpty();
    
    /**
     * Returns the number of elements in this list.
     * @return the number of elements in this list.
    */
    public int size();
    
    /** Removes all of the elements from this list.
     */
    public void removeAll();

      /**
     * Returns the element at the specified position in this list.
     * @param index index of element to return.
     * @return the element at the specified position in this list.
     * @throws IndexOutOfBoundsException - if index is out of range
     * <code>(index < 0 || index > size())</code>.
     */
    public E get(int index) 
	throws ListIndexOutOfBoundsException;

} // end BasicListInterface
