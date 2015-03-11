public class TestSortedList {

  public static void main(String[] args) {
    // Simple main function to test ListArrayBased Class  
    SortedList<Integer> L = new SortedList<Integer>(20);
    Integer[] values = {10, 40, 30, 50, 20, 30};
    System.out.print("L = "); L.display();
    for (Integer val : values)
    		L.sortedAdd(val);
    System.out.print("L = "); L.display();
    L.sortedRemove(20);
    System.out.print("L -20 = "); L.display();
    L.sortedRemove(30);
    System.out.print("L -30 = "); L.display();
    L.sortedRemove(40);
    System.out.print("L -40 = "); L.display();
    
    SortedList<Shape> M = new SortedList<Shape>();
    M.sortedAdd(new Disk(3));
    M.sortedAdd(new Disk(4));
    M.sortedAdd(new Square(7));
    M.sortedAdd(new Rectangle(10,5));
    M.sortedAdd(new Square(8));
    M.display(); 
   M.get(1).compareTo(new Square(6)); 
  } // end main
} // end class TestSortedList

