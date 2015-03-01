/**
 * class Node for the implementation of simple linked list. The node
 * contains only an item and a reference to the following Node
 *
 * @author Sam Beckmann
 */

public class Node<E>
{

    /**
     * Object contained in the Node
     */
    private E item;
    /**
     * Reference to the following node
     */
    private Node<E> next;
    /**
     * Reference to the previous node
     */
    private Node<E> prev;

    /**
     * Constructor: create a new Node containing the specified item
     *
     * @param newItem the item encapsulated in the node
     */
    public Node(E newItem)
    {
        item = newItem;
        next = null;
        prev = null;
    } // end constructor

    /**
     * Constructor: creates a new Node containing the specified item
     * and links this node to the specified nodse:
     * prevNode <- this -> nextNode
     * @param newItem  the item encapsulated in the node
     * @param prevNode the previous node of this node.
     * @param nextNode the successor node of this node.
     */
    public Node(E newItem, Node<E> prevNode, Node<E> nextNode)
    {
        item = newItem;
        next = nextNode;
        prev = prevNode;
    } // end constructor

    /**
     * set the item in the node
     *
     * @param newItem the new item to be encapsulated in the node.
     */
    public void setItem(E newItem)
    {
        item = newItem;
    } // end setItem

    /**
     * get the item encapsulated in the node
     *
     * @return the object encapsulated in the node
     */
    public E getItem()
    {
        return item;
    } // end getItem

    /**
     * set the reference to the following node
     *
     * @param nextNode following node
     */
    public void setNext(Node<E> nextNode)
    {
        next = nextNode;
    } // end setNext

    /**
     * get the following node
     *
     * @return the following node
     */
    public Node<E> getNext()
    {
        return next;
    } // end getNext

    /**
     * set the reference to the previous node
     *
     * @param prevNode following node
     */
    public void setPrev(Node<E> prevNode)
    {
        prev = prevNode;
    }

    /**
     * get the previous node
     *
     * @return the previous node
     */
    public Node<E> getPrev()
    {
        return prev;
    }

} // end class Node
