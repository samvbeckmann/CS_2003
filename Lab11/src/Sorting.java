public class Sorting
{


    public static <E extends Comparable<? super E>> E kSmall(int k,
                                                             E[] array, int first, int last)
    {
        int pivot = partition(array, first, last);
        if (k < pivot - first + 1)
            return kSmall(k, array, first, pivot - 1);
        else if (k == pivot - first + 1)
            return array[pivot];
        else
            return kSmall(k - (pivot - first + 1), array, pivot + 1, last);
    }

    /**
     * Sorts the items in an array into ascending order using the
     * Bubble Sort algorithm: <code>theArray</code> is sorted into
     * ascending order.
     *
     * @param n        is the number of items of the array to sort
     * @param theArray is an array of n items.
     */
    public static <E extends Comparable<? super E>> void bubbleSort(E[] theArray, int n)
    {

        boolean sorted = false;  // false when swaps occur
        for (int pass = 1; (pass < n) && !sorted; ++pass)
        {
            // Invariant: theArray[n+1-pass..n-1] is sorted
            //            and > theArray[0..n-pass]
            sorted = true;  // assume sorted
            for (int index = 0; index < n - pass; ++index)
            {
                // Invariant: theArray[0..index-1] <= theArray[index]
                int nextIndex = index + 1;
                if (theArray[index].compareTo(theArray[nextIndex]) > 0)
                {
                    // exchange items
                    E temp = theArray[index];
                    theArray[index] = theArray[nextIndex];
                    theArray[nextIndex] = temp;
                    sorted = false;  // signal exchange
                }  // end if
            }  // end for

            // Assertion: theArray[0..n-pass-1] < theArray[n-pass]
        }  // end for
    }  // end bubbleSort


    /**
     * Finds the largest item in an array.
     *
     * @param size     is the size of the array to sort
     * @param theArray is the array of size <code>size</code> to sort;
     *                 <code>size >= 1</code>.
     * @return the index of the largest item in the array.
     */
    private static <E extends Comparable<? super E>> int indexOfLargest(E[] theArray, int size)
    {

        int indexSoFar = 0; // index of largest item found so far
        // Invariant: theArray[indexSoFar]>=theArray[0..currIndex-1]
        for (int currIndex = 1; currIndex < size; ++currIndex)
        {
            if (theArray[currIndex].compareTo(theArray[indexSoFar]) > 0)
            {
                indexSoFar = currIndex;
            }  // end if
        } // end for

        return indexSoFar;  // index of largest item
    }  // end indexOfLargest

    /**
     * Sorts the items in an array into ascending order using the
     * Insertion Sort Algorithm.
     *
     * @param n        is the size of the array to sort
     * @param theArray is an array of n items.
     */
    public static <E extends Comparable<? super E>> void insertionSort(E[] theArray, int n)
    {
        // unsorted = first index of the unsorted region,
        // loc = index of insertion in the sorted region,
        // nextItem = next item in the unsorted region

        // initially, sorted region is theArray[0],
        //          unsorted region is theArray[1..n-1];

        for (int unsorted = 1; unsorted < n; ++unsorted)
        {
            // Invariant: theArray[0..unsorted-1] is sorted
            // find the right position (loc) in
            // theArray[0..unsorted] for theArray[unsorted],
            // which is the first item in the unsorted
            // region; shift, if necessary, to make room
            E nextItem = theArray[unsorted];
            int loc = unsorted;

            while ((loc > 0) &&
                    (theArray[loc - 1].compareTo(nextItem) > 0))
            {
                // shift theArray[loc-1] to the right
                // BUG FIX: replaced the following line with the two that follows it
                // theArray[loc--] = theArray[loc-1];
                theArray[loc] = theArray[loc - 1];
                loc--;
            }  // end while
            // insert nextItem into sorted region
            theArray[loc] = nextItem;
        }  // end for
    }  // end insertionSort


    /**
     * Merges two sorted array segments theArray[first..mid] and
     * theArray[mid+1..last] into one sorted array.
     *
     * @param first <code>first <= mid <= last</code>. The subarrays
     *              theArray[first..mid] and theArray[mid+1..last] are
     *              each sorted in increasing order.
     * @param mid   with <code>first <= mid <= last </code>. The subarrays
     *              theArray[first..mid] and theArray[mid+1..last] are
     *              each sorted in increasing order.
     * @param last  with <code> first <= mid <= last</code>.  The
     *              subarrays theArray[first..mid] and theArray[mid+1..last] are
     *              each sorted in increasing order.  Implementation note: This
     *              method merges the two subarrays into a temporary array and
     *              copies the result into the original array anArray.
     */
    private static <E extends Comparable<? super E>> void merge(E[] theArray,
                                                                int first, int mid, int last)
    {

        int maxSize = theArray.length;

        E[] tempArray;
        tempArray = (E[]) java.lang.reflect.Array.newInstance(theArray.getClass().getComponentType(), maxSize);
        // temporary array
        //Object[] tmp = new Object[maxSize];
        //E[] tempArray =  tmp;
        //	E[] tempArray = new E[maxSize];

        // initialize the local indexes to indicate the subarrays
        int first1 = first;    // beginning of first subarray
        int last1 = mid;      // end of first subarray
        int first2 = mid + 1;  // beginning of second subarray
        int last2 = last;     // end of second subarray
        // while both subarrays are not empty, copy the
        // smaller item into the temporary array
        int index = first1;    // next available location in
        // tempArray
        while ((first1 <= last1) && (first2 <= last2))
        {
            // Invariant: tempArray[first1..index-1] is in order
            if (theArray[first1].compareTo(theArray[first2]) < 0)
            {
                tempArray[index] = theArray[first1];
                first1++;
            } else
            {
                tempArray[index] = theArray[first2];
                first2++;
            }  // end if
            index++;
        }  // end while

        // finish off the nonempty subarray

        // finish off the first subarray, if necessary
        while (first1 <= last1)
        {
            // Invariant: tempArray[first1..index-1] is in order
            tempArray[index] = theArray[first1];
            first1++;
            index++;
        }  // end while

        // finish off the second subarray, if necessary
        while (first2 <= last2)
        {
            // Invariant: tempArray[first1..index-1] is in order
            tempArray[index] = theArray[first2];
            first2++;
            index++;
        }  // end while

        // copy the result back into the original array
        for (index = first; index <= last; ++index)
        {
            theArray[index] = tempArray[index];
        }  // end for
    }  // end merge


    /**
     * Sorts the items in an array into ascending order using the Merge
     * Sort algorithm. The algorithm will sort the array between the
     * indices <code>first</code> and <code>last</code>.
     *
     * @param first    is the index of the first element to sort
     * @param last     is the index of the last element to sort
     * @param theArray is the array to sort
     *                 Calls: merge.
     */
    public static <E extends Comparable<? super E>> void mergeSort(E[] theArray, int first, int last)
    {
        if (first < last)
        {
            // sort each half
            int mid = (first + last) / 2;   // index of midpoint
            // sort left half theArray[first..mid]
            mergeSort(theArray, first, mid);
            // sort right half theArray[mid+1..last]
            mergeSort(theArray, mid + 1, last);
            // merge the two halves
            merge(theArray, first, mid, last);
        }  // end if
    }  // end mergesort

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
     * Calls: choosePivot.
     */

    private static <E extends Comparable<? super E>> int partition(E[] theArray,
                                                                   int first, int last)
    {
        // tempItem is used to swap elements in the array
        E tempItem;
        // place pivot in theArray[first]
        choosePivot(theArray, first, last);
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
     * Chooses a pivot for quickSort's partition algorithm and swaps it
     * with the first item in an array; i.e.  theArray[first] is the
     * pivot.
     *
     * @param theArray is the array to be sorted: the element between
     *                 <code>first</code> and <code>last</code> (with <code>first <=
     *                 last</code>)will be sorted.
     * @param first    is the index of the first element to consider
     * @param last     is the index of the last element to consider
     */
    private static <E extends Comparable<? super E>> void choosePivot(E[] theArray,
                                                                      int first, int last)
    {
        // Implementation left as an exercise.
    }  // end choosePivot

    /**
     * Sorts the items in an array into ascending order using the Quick
     * Sort algorithm
     *
     * @param first    is the index of the first element to sort with
     *                 <code>first <= last</code>.
     * @param last     is the index of the last element to sort with
     *                 <code>first <= last</code>.
     * @param theArray is the array to be sorted: the element
     *                 between <code>first</code> and <code>last</code> (with
     *                 <code>first <= last</code>)will be sorted.
     *                 Calls: partition.
     */

    public static <E extends Comparable<? super E>> void quickSort(E[] theArray,
                                                                   int first, int last)
    {
        int pivotIndex;

        if (first < last)
        {
            // create the partition: S1, Pivot, S2
            pivotIndex = partition(theArray, first, last);

            // sort regions S1 and S2
            quickSort(theArray, first, pivotIndex - 1);
            quickSort(theArray, pivotIndex + 1, last);
        }  // end if
    }  // end quickSort


    /**
     * Sorts the items in an array into ascending order using the
     * Selection Sort algorithm.
     *
     * @param n        is the size of the array to sort
     * @param theArray is an array of n items.
     *                 Calls: indexOfLargest.
     */

    public static <E extends Comparable<? super E>> void selectionSort(E[] theArray,
                                                                       int n)
    {
        // last = index of the last item in the subarray of
        //        items yet to be sorted
        // largest = index of the largest item found
        for (int last = n - 1; last >= 1; last--)
        {
            // Invariant: theArray[last+1..n-1] is sorted
            // and > theArray[0..last]

            // select largest item in theArray[0..last]
            int largest = indexOfLargest(theArray, last + 1);

            // swap largest item theArray[largest] with
            // theArray[last]
            E temp = theArray[largest];
            theArray[largest] = theArray[last];
            theArray[last] = temp;
        }  // end for
    }  // end selectionSort

    /**
     * Sorts the items in an array into ascending order using the
     * Shell Sort algorithm.
     *
     * @param n        is the size of the array to sort
     * @param theArray is an array of n items.
     */
    public static <E extends Comparable<? super E>> void shellSort(E[] theArray, int n)
    {
        int loc;
        E nextItem;
        for (int h = n / 2; h > 0; h = h / 2)
        {
            for (int unsorted = h; unsorted < n; ++unsorted)
            {
                nextItem = theArray[unsorted];
                loc = unsorted;
                while ((loc >= h) &&
                        (theArray[loc - h].compareTo(nextItem) > 0))
                {
                    theArray[loc] = theArray[loc - h];
                    loc = loc - h;
                }  // end while
                theArray[loc] = nextItem;
            }  // end for unsorted
        }  // end for h
    }  // end shellsort
}
