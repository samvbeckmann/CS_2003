import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import java.util.Vector;

/**
 * Finds the kth smallest value from a given array, recursively.
 *
 * @author Sam Beckmann
 * ID: 1443946
 * CS 2003-01
 */
public class Lab5
{
    /**
     * Partitions an array for quickSort.
     *
     * @param first    is the index of the first element to sort with
     *                 <code>first <= last</code>.
     * @param last     is the index of the last element to sort with
     *                 <code>first <= last</code>.
     * @param theArray is the array to be sorted: the element
     *                 between <code>first</code> and <code>last</code> (with
     *                 <code>first <= last</code>)will be sorted.
     * @return the index of the pivot element of
     * theArray[first..last]. Upon completion of the method, this will
     * be the index value lastS1 such that <code>S1 =
     * theArray[first..lastS1-1] < pivot theArray[lastS1] == pivot S2 =
     * theArray[lastS1+1..last] >= pivot </code>
     */
    private static <E extends Comparable<? super E>> int partition(E[] theArray,
                                                                   int first, int last)
    {
        // tempItem is used to swap elements in the array
        E tempItem;
        E pivot = theArray[first];   // reference pivot
        // initially, everything but pivot is in unknown
        int lastS1 = first;          // index of last item in S1
        // move one item at a time until unknown region is empty
        for (int firstUnknown = first + 1; firstUnknown <= last;
             ++firstUnknown)
        {
            // Invariant: theArray[first+1..lastS1] < pivot
            //            theArray[lastS1+1..firstUnknown-1] >= pivot
            // move item from unknown to proper region
            if (theArray[firstUnknown].compareTo(pivot) < 0)
            {
                // item from unknown belongs in S1
                ++lastS1;
                tempItem = theArray[firstUnknown];
                theArray[firstUnknown] = theArray[lastS1];
                theArray[lastS1] = tempItem;
            }  // end if
            // else item from unknown belongs in S2
        }  // end for
        // place pivot in proper position and mark its location
        tempItem = theArray[first];
        theArray[first] = theArray[lastS1];
        theArray[lastS1] = tempItem;
        return lastS1;
    }  // end partition

    /**
     * Returns the kth smallest value in the given array betwen the first and last indexes.
     *
     * @param k the element used as the pivot point
     * @param array array to find the element in
     * @param first first index to be considered
     * @param last last index to be considered
     * @return the kth smallest element in the array
     */
    public static <E extends Comparable<? super E>> E kSmall(int k, E[] array, int first, int last)
    {
        int pI = partition(array, first, last);
        if (pI - first + 1 == k)
        {
            return array[pI];
        } else if (pI - first + 1 > k)
        {
            return kSmall(k, array, first, pI-1);
        } else
        {
            return kSmall(k-(pI-first+1), array, pI+1, last);
        }
    }


    public static void main(String[] args)
    {
        try
        {
            Scanner console = new Scanner(System.in);
            System.out.println("Enter the name of the file containing the data");
            String filename = console.next();
            // read the data in the file
            Vector<Integer> vec = new Vector<Integer>();
            Scanner scanData = new Scanner(new File("CS_2003/Lab05/" + filename));
            while (scanData.hasNext())
                vec.add(scanData.nextInt());
            Integer[] myArray = new Integer[vec.size()];
            int count = 0;
            System.out.println("The integers in the file " + filename + " are: ");
            for (Integer val : vec)
            {
                myArray[count] = val;
                System.out.print(val + " ");
                count++;
            }
            while (true)
            {
                boolean flag;
                do
                {
                    flag = false;
                    try
                    {
                        System.out.print("\nEnter an index between 1 and " + vec.size() + ", or enter -1 to quit: ");
                        int k = console.nextInt();
                        if (k == 1)
                        {
                            System.out.println("The 1st smallest item in the file is: " + kSmall(k, myArray, 0, myArray.length-1));
                        } else if (k == 2)
                        {
                            System.out.println("The 2nd smallest item in the file is: " + kSmall(k, myArray, 0, myArray.length-1));
                        } else if (k == 3)
                        {
                            System.out.println("The 3rd smallest item in the file is: " + kSmall(k, myArray, 0, myArray.length-1));
                        } else if (k > 0 && k <= myArray.length)
                        {
                            System.out.println("The " + k + "th smallest item in the file is: " + kSmall(k, myArray, 0, myArray.length-1));
                        } else
                        {
                            return;
                        }
                    } catch (Exception e)
                    {
                        System.err.print(e);
                        System.out.println("Not a valid input. Try again.");
                        flag = true;
                    }
                } while (flag);
            }
        } catch (IOException e)
        {
            System.err.println("IOError!!!\n" + e);
            System.exit(9);
        }
    }
}//end class
