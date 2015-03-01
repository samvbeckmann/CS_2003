public class TestLR
{  public static void main(String[] args)
   {// Simple main function to test ListReferenceBased Class  
       ListReferenceBased<Integer> L = new ListReferenceBased<Integer>();
       ListReferenceBased<Integer> M = new ListReferenceBased<Integer>(20);

       L.add(1, 10);
       L.append(40);
       L.add(2, 30);
       L.add(2, 20);
       L.display();
       System.out.println("The size of List L is "+ L.size());
       System.out.println("The size of List M is "+ M.size());
       M = new ListReferenceBased<Integer>(L);
       System.out.println("List L is copied to list M and M now contains :");
       M.display();
       L.add(5, 50);
       if(L.contains(40)>0)
	   System.out.println("The list contains 40");
       else 
	   System.out.println("The list does not contain 40");
       if(L.contains(90)>0) 
	   System.out.println("The list contains 90");
       else 
	   System.out.println("The list does not contain 90 ");
       L.add(6, 60);
       System.out.println("Value in position 3 is "+ L.get(3));
       L.display();
       L.remove(6);
       L.remove(1);
       L.display();
       L.remove(2);
       System.out.println("The list L is now:");
       L.display();
       System.out.println("the list M printed in reverse is:");
       M.displayReverse();
       L.delete(80);
       L.delete(40);
       L.display();
       int sum=0;
       for (Integer val : L){
	   System.out.println("value is " + val);
	   sum+= val;
       }
       System.out.println("the sum of the element in L is : " + sum);
   } // end main
} // end class TestLR
//***********************************
