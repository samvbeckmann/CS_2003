import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

/**
 * Recursively finds the minimum and maximum of a file of doubles
 *
 * @author Sam Beckmann
 * CS 2003-01
 * ID: 1443946
 */
public class VectorMinMax
{

    /**
     * Main function, asks for a file name, then parses that file
     * to produce a vector of the doubles in it, the prints the
     * Min and Max of the Vector, along with their respective indexes.
     *
     * @param args Arguments of the program.
     */
    public static void main(String [] args)
    {
        Scanner scanMan = new Scanner (System.in);
        MinMaxObject minMax = new MinMaxObject();

        System.out.print("Name of file: ");
        boolean flag;
        do
        {
            flag = true;
            try // Competent exception handler for IO errors.
            {
                String filename = scanMan.next();
                System.out.println();
                Scanner tokens = new Scanner(new File(filename));
                Vector<Double> vector = new Vector<Double>();
                while (tokens.hasNextDouble())
                {
                    vector.add(tokens.nextDouble());
                }
                minMax = findMinMax(vector);
            }
            catch (IOException e)
            {
                flag = false;
                System.out.print("File not found. Try again: ");
            }
        } while (!flag);

        System.out.println(minMax.toString());

    } // end main

    /**
     * Takes a vector of doubles, and finds the minimum and maximum via recursive methods.
     *
     * @param doubleVector A vector of doubles, to be searched for minimum and maximum
     * @return A MinMaxObject that carries the min, max, and indexes of each in a given vector.
     */
    public static MinMaxObject findMinMax(Vector<Double> doubleVector)
    {
        int minIndex = findMin(doubleVector, 1, doubleVector.size() - 1, 0);
        int maxIndex = findMax(doubleVector, 1, doubleVector.size() - 1, 0);

        return new MinMaxObject(doubleVector.get(minIndex), minIndex, doubleVector.get(maxIndex), maxIndex);
    } // end findMinMax

    /**
     * Recursive algorithm that finds the minimum in a vector of doubles between the indexes first and last.
     *
     * @param doubleVector Vector of Doubles to find the minimum in.
     * @param first What index of the given vector to begin the search for minimum on.
     * @param last What index of the given vector to end the search for minimum on.
     * @param currMin The index of the current minimum.
     * @return The index of the minimum found within given parameters.
     */
    public static int findMin(Vector<Double> doubleVector, int first, int last, int currMin)
    {
        if (last < first) // Base Case
        {
            return currMin;
        }
        else
        {
            if (doubleVector.get(first) < doubleVector.get(currMin))
            {
                currMin = first;
            }
            return findMin(doubleVector, first + 1, last, currMin); // Recursive Call
        }
    } // end findMin

    /**
     * Recursive algorithm that finds the maximum in a vector of doubles between the indexes first and last.
     *
     * @param doubleVector Vector of Doubles to find the minimum in.
     * @param first What index of the given vector to begin the search for maximum on.
     * @param last What index of the given vector to end the search for maximum on.
     * @param currMax The index of the current maximum.
     * @return The index of the maximum found within given parameters.
     */
    public static int findMax(Vector<Double> doubleVector, int first, int last, int currMax)
    {
        if (last < first) // Base Case
        {
            return currMax;
        }
        else
        {
            if (doubleVector.get(first) > doubleVector.get(currMax))
            {
                currMax = first;
            }
            return findMax(doubleVector, first + 1, last, currMax); // Recursive Call
        }
    } // end findMax


    /**
     * Data type containing a minimum, maximum, and respective indexes for a list of doubles.
     */
    public static class MinMaxObject
    {

        /**
         * Minimum of the given vector.
         */
        private double min;

        /**
         * Maximum of the given vector.
         */
        private double max;

        /**
         * Index of the minimum.
         */
        private int minIndex;

        /**
         * Index of the maximum.
         */
        private int maxIndex;

        /**
         * Default Constructor
         */
        public MinMaxObject()
        {
            // NOOP
        } // end constructor

        /**
         * Constructor with all variables initialized.
         *
         * @param min_ Given minimum.
         * @param minIndex_ Given minimum index.
         * @param max_ Given Maximum.
         * @param maxIndex_ Given Maximum index.
         */
        public MinMaxObject(double min_, int minIndex_, double max_, int maxIndex_)
        {
            min = min_;
            minIndex = minIndex_;
            max = max_;
            maxIndex = maxIndex_;
        } // end constructor


        /**
         * Prints the values of the minMaxObject to a string
         *
         * @return String containing all of the variable fields, formatted.
         */
        @Override
        public String toString()
        {
            return "Minimum:\t" + min
                    + "\nMin Index:\t" + minIndex
                    + "\nMaximum:\t" + max
                    + "\nMax Index:\t" + maxIndex + "\n";
        } // end toString
    } // end MinMaxObject
} // end VectorMinMax
