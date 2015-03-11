package ListReferencedBased;

public class SetTest
{ // Simple main function to test ADT Set
    public static void main(String[] args)
    {
      Integer[] M ={2,3,5,7,11,13,17,19,23};
      Integer[] N = {1,3,6,8,9,11,12,17,19,23,24,25,3};
	
      Set<Integer> A = new Set<Integer>(); 
      A.insert(10);
      A.arrayInsert(M);
      System.out.println("\nSet A Follows:");
      A.print();

      Set<Integer> B = new Set<Integer>(); 
      B.arrayInsert(N);
      B.insert(11);
      System.out.println("\nSet B Follows:");
      B.print();

      Set<Integer> C = A.union(B);
      Set<Integer> D = A.intersection(B);
      System.out.println("\nSet C which is (A Union B) Follows:");
      C.print();
      System.out.println("\nSet D which is (A intersect B) Follows:");
      D.print();

      Set<Integer> E = A.difference(B);
      Set<Integer> F = B.difference(A);
      System.out.println("\nSet E which is (A - B) Follows:");
      E.print();
      System.out.println("\nSet F which is (B - A) Follows:");
      F.print();
      E.in(2);
      F.in(2);
   } // end main
} 

