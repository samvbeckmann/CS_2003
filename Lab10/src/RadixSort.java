import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Implementation of Radix Sort with multiple queues
 *
 * @author Sam Beckmann
 * ID: 1443946
 * CS 2003
 */
public class RadixSort
{

    public static void main(String[] argv)
    {
        QueueReferenceBased<Integer> Q = null;
        try
        {
            Q = new QueueReferenceBased<Integer>();
            //Read file radix.dat into a queue Q
            Scanner scan = new Scanner(new File("radix.dat"));
            //Calling RadSort with queue Q and the pass number
            while (scan.hasNextInt())
                Q.enqueue(scan.nextInt());
            scan.close();
        } catch (IOException e)
        {
            System.err.println("error obtaining the data");
            System.exit(9);
        }
        radixSort(Q);
    }


    public static void radixSort(QueueReferenceBased<Integer> Q)
    {
        radix(Q, 1);
    }

    public static void radix(QueueReferenceBased<Integer> Q, int k)
    {
        System.out.println("~~  sorting column " + k + " ~~");
        final int NUMDIGITS = 5; // maximum number of digits in data
        final int NUMBASE = 10; // decimal numbers, base 10

        // creation of the array
        ArrayList<QueueReferenceBased<Integer>> pockets =
                new ArrayList<QueueReferenceBased<Integer>>(NUMBASE);

        //instantiation of the array
        for (int i = 0; i < NUMBASE; i++)
            pockets.add(i, new QueueReferenceBased<Integer>());

        //enqueue the appropriate pockets
        while (!Q.isEmpty())
        {
            int value = Q.dequeue();
            pockets.get(getKthNumber(value, k, NUMBASE)).enqueue(value);
        }

        // dequeue from pockets, enqueue to main Queue
        for (QueueReferenceBased<Integer> pocket : pockets)
        {
            while (!pocket.isEmpty())
            {
                int toQueue = pocket.dequeue();
                Q.enqueue(toQueue);
                System.out.printf("%6d\n", toQueue);
            }
        }

        // recursive call if necessary.
        if (k < NUMDIGITS)
            radix(Q, k + 1);
    } // end RadSort


    /**
     * find the kth digit of a number num writen in base numbase
     *
     * @param num     is the number considered
     * @param k       is the position of the digit we want to know (from the right)
     * @param numbase is the base used to write <code>num</code> (ex base 10).
     */
    public static int getKthNumber(Integer num, int k, int numbase)
    {
        return (num / (int) Math.pow(numbase, k - 1)) % numbase;
    }

}// end RadixSort

