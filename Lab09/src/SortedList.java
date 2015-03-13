import ListReferencedBased.ListException;
import ListReferencedBased.ListIndexOutOfBoundsException;
import ListReferencedBased.ListReferenceBased;

public class SortedList<E extends Comparable<? super E>> implements SortedListInterface<E>
{
    /**
     * Sorted List.
     */
    ListReferenceBased<E> list;

    /**
     * Default constructor.
     */
    public SortedList()
    {
        list = new ListReferenceBased<E>();
    } // end default constructor

    /**
     * Constructor.
     *
     * @param item The first item in the list.
     */
    public SortedList(E item)
    {
        list = new ListReferenceBased<E>(item);
    } // end constructor

    /**
     * Copy constructor.
     *
     * @param sortedList Sorted List to copy.
     */
    public SortedList(SortedList sortedList)
    {
        list = new ListReferenceBased<E>(sortedList.list);
    } // end copy constructor

    /**
     * Adds a given item to the sorteedList in the right spot.
     *
     * @param newItem new item to be inserted in the list.
     * @throws ListException
     */
    public void sortedAdd(E newItem) throws ListException
    {
        list.add(locateIndex(newItem) + 1, newItem);
    } // end sortedAdd

    /**
     * Removes the first instance of the given item in a list,
     * if the list contains an instance of that item.
     *
     * @param anItem is the item to be remove
     */
    public void sortedRemove(E anItem)
    {
        int index = locateIndex(anItem);
        if (list.get(index) == anItem)
            list.remove(index);
    } // end sortedRemove

    /**
     * Finds the place where the given item should be in the list.
     *
     * @param anItem is the item which index is wanted.
     * @return index where the item is/should be in the list.
     */
    public int locateIndex(E anItem)
    {
        return locateIndex(anItem, 1, size());
    } // end locateIndex

    /**
     * Recursive method to find the place where the given item should be in the list
     *
     * @param item  is the item which index is wanted.
     * @param first the starting index for the binary search.
     * @param last  the ending index for the binary search.
     * @return index where the given item is/should be in the list/
     */
    private int locateIndex(E item, int first, int last)
    {
        int middle = (first + last) / 2;

        if (first > last) // base case
            return middle;

        int compare = item.compareTo(list.get(middle));

        if (compare == 0)
            return middle;
        else if (compare < 0)
            return locateIndex(item, first, middle - 1);
        else
            return locateIndex(item, middle + 1, last);
    } // end locateIndex

    /**
     * Tests if this list has no elements.
     *
     * @return <code>true</code> if this list has no elements;
     * <code>false</code> otherwise.
     */
    public boolean isEmpty()
    {
        return list.isEmpty();
    } // end isEmpty

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list.
     */
    public int size()
    {
        return list.size();
    } // end size

    /**
     * Removes all of the elements from this list.
     */
    public void removeAll()
    {
        list.removeAll();
    } // end removeAll

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of element to return.
     * @return the element at the specified position in this list.
     * @throws IndexOutOfBoundsException - if index is out of range
     *                                   <code>(index < 0 || index > size())</code>.
     */
    public E get(int index) throws ListIndexOutOfBoundsException
    {
        return list.get(index);
    } // end get

    /**
     * Prints all the elements in this list on the console in sequence
     */
    public void display()
    {
        list.display();
    } // end display
}  // end class
