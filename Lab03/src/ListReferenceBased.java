/**
 * Reference-based implementation of ADT list.
 * Modify the code to implement a doubly linked list.
 *
 * @author Sam Beckmann
 * CS 2000-01
 * ID: 1443946
 */

import java.util.Iterator;

public class ListReferenceBased<E> implements ListInterface<E>, Iterable<E>
{

    /**
     * Reference to the first element of the list
     */
    private Node<E> head;

    /**
     * Number of items in list
     */
    private int numItems;

    /**
     * Default constructor
     */
    public ListReferenceBased()
    {
        numItems = 0;
        head = null;
    }  // end default constructor

    /**
     * Constructor with the first item in the list.
     *
     * @param item the first item in the list
     */
    public ListReferenceBased(E item)
    {
        numItems = 1;
        head = new Node<E>(item);
    } // end first item constructor

    /**
     * Copy constructor
     *
     * @param listReferenceBased list to be copied
     */
    public ListReferenceBased(ListReferenceBased listReferenceBased)
    {

        if (!listReferenceBased.isEmpty())
        {
            head = new Node<E>((E) listReferenceBased.get(1));
            numItems = 1;

            for (int i = 2; i <= listReferenceBased.numItems; i++)
            {
                append((E) listReferenceBased.get(i));
            }
        } else head = null;
    } // end copy constructor

    /**
     * Tests if this list has no elements.
     *
     * @return <code>true</code> if this list has no elements;
     * <code>false</code> otherwise.
     */
    public boolean isEmpty()
    {
        return numItems == 0;
    }  // end isEmpty

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list.
     */
    public int size()
    {
        return numItems;
    }  // end size


    /**
     * Locates a specified node in a linked list.
     * <li> Postcondition: Curr will be the node at specified index
     *
     * @param index position of the desired node. Assumes that <code>
     *              1 <= index <= numItems</code>
     * @return a reference to the desired Node.
     */
    private Node<E> find(int index)
    {
        Node<E> curr = head;
        for (int skip = 1; skip < index; skip++) // iterate through to the correct index
        {
            curr = curr.getNext();
        } // end for
        return curr;
    } // end find

    /**
     * Get the item location at the specified position in the list
     *
     * @param index position of the node containing the item
     * @return the Object located at the specified index
     * @throws ListIndexOutOfBoundsException if the index is out of
     *                                       range, i.e. when <code> index <=0 || index > size() </code>
     */
    public E get(int index)
            throws ListIndexOutOfBoundsException
    {
        if (index >= 1 && index <= numItems)
        {
            // get reference to node, then return data in node
            Node<E> curr = find(index);
            return curr.getItem();
        } else
        {
            throw new ListIndexOutOfBoundsException(
                    "List index out of bounds exception on get");
        } // end if
    } // end get


    /**
     * Inserts the specified element at the specified position in this
     * list. Shifts the element currently at that position (if any)
     * and any subsequent elements to the right (adds one to their
     * indices).
     *
     * @param index index at which the specified element is to be
     *              inserted.
     * @param item  element to be inserted.
     * @throws IndexOutOfBoundsException - if index is out of range
     *                                   (index < 0 || index > size() + 1).
     */
    public void add(int index, E item)
            throws ListIndexOutOfBoundsException
    {
        if (index >= 1 && index <= numItems + 1)
        {
            if (isEmpty()) // Case 1: List is empty
            {
                head = new Node<E>(item);
            }
            else if (index == 1) // Case 2: Add at head
            {
                Node<E> newNode = new Node<E>(item, null, head);
                head.setPrev(newNode);
                head = newNode;
            }
            else if (index == size() + 1) // Case 3: Add to end
            {
                Node<E> prev = find(index - 1);
                Node<E> newNode = new Node<E>(item, prev, null);
                prev.setNext(newNode);

            } else // Case 4: Add in middle
            {
                Node<E> prev = find(index - 1);
                Node<E> newNode = new Node<E>(item, prev, prev.getNext());
                prev.setNext(newNode);
                newNode.getNext().setPrev(newNode);

            } // end if else structure
            numItems++;
        } else
        {
            throw new ListIndexOutOfBoundsException(
                    "List index out of bounds exception on add");
        } // end if
    }  // end add


    /**
     * Appends the specified element to the end of this list.
     *
     * @param elt element to be added at the end of the list
     */
    public void append(E elt)
    {
        add(size()+1, elt);
    }//end append


    /**
     * Removes the element at the specified position in this
     * list. Reduces the number of elements by one.
     *
     * @param index the index of the element to remove
     * @throws IndexOutOfBoundsException - if index is out of range
     *                                   (index < 0 || index > size()).
     */
    public void remove(int index)
            throws ListIndexOutOfBoundsException
    {
        if (index >= 1 && index <= size())
        {
            if (index == 1)
            {
                // delete the first node from the list
                head = head.getNext();
                head.setPrev(null);
            }
            else if (index == size())
            {
                Node<E> prev = find(index - 1);
                prev.getNext().setPrev(null);
                prev.setNext(null);
            }
            else
            {
                Node<E> prev = find(index - 1);
                // delete the node after the node that prev
                // references, save reference to node
                prev.setNext(prev.getNext().getNext());
                prev.getNext().setPrev(prev);
            } // end if
            numItems--;
        } // end if
        else
        {
            throw new ListIndexOutOfBoundsException(
                    "List index out of bounds exception on remove");
        } // end if
    }   // end remove


    /**
     * Remove all the elements in this list.
     */
    public void removeAll()
    {
        // setting head to null causes list to be
        // unreachable and thus marked for garbage
        // collection
        head = null;
        numItems = 0;
    } // end removeAll


    /**
     * Delete the the specified element in this list if exists,
     * otherwise state that the item is not in the current
     * list.
     *
     * @param elt the element, if it exists, to delete
     */
    public void delete(E elt)
    {
        int index = contains(elt);
        if (index > 0)
        {
            remove(index);
        } else
        {
            System.out.println("Value not in list, cannot be removed.");
        }
    }

    /**
     * Looks for the index of the specified element in this list. If
     * the element is not present, the method returns <code>-1</code>
     *
     * @param elt the element which index is looked for.
     * @return either the index of the location in the list where the
     * argument is present or <code>-1</code> if the argument is not
     * contained in the list.
     */
    public int contains(E elt)
    {
        Node<E> checkedNode = head;
        int checkedIndex = 1;
        while (checkedIndex <= numItems)
        {
            if (checkedNode.getItem().equals(elt))
            {
                return checkedIndex;
            }
            checkedNode = checkedNode.getNext();
            checkedIndex++;
        }
        return -1;
    }

    /**
     * Prints all the elements in this list on the console in sequence
     */
    public void display()
    {
        Node<E> printing = head;
        while(printing != null)
        {
            System.out.print(printing.getItem() + " ");
            printing = printing.getNext();
        }
        System.out.println();
    }

    /**
     * Prints all the elements in this list on the console in reverse
     * order
     */
    public void displayReverse()
    {
        Node<E> printing = find(size());
        while(printing != null)
        {
            System.out.print(printing.getItem() + " ");
            printing = printing.getPrev();
        }
        System.out.println();
    }

    public ListReferenceBasedIterator<E> iterator()
    {
        return new ListReferenceBasedIterator<E>(head);
    }

    public class ListReferenceBasedIterator<E> implements Iterator<E>
    {
        Node<E> current;

        public ListReferenceBasedIterator(Node<E> node)
        {
            current = node;
        }

        public boolean hasNext()
        {
            return (current != null);
        }

        public E next()
        {
            E elt = current.getItem();
            current = current.getNext();
            return elt;
        }

        public void remove()
        {
        }
    }
} // end ListReferenceBased
