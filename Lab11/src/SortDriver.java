import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

/**
 * Implementation class for testing sorts in Labs 11 and 12
 *
 * @author Sam Beckmann
 *         ID: 1443946
 *         CS 2003-01
 */
public class SortDriver
{
    private static final int[] SAMPLE_SIZES = {100, 1000, 10000};
    private static final int NUM_TESTS = 25;
    private static final int NUM_ALGORITHMS = 6;

    public static void main(String[] args)
    {
        try // Delete output file if it exists
        {
            Files.delete(Paths.get("timings.dat"));
        } catch (IOException x)
        {
            // NOOP
        }

        for (int size : SAMPLE_SIZES)
        {
            long[][] times = new long[NUM_ALGORITHMS][NUM_TESTS];
            double[] means = new double[NUM_ALGORITHMS];

            for (int i = 0; i < NUM_TESTS; i++)
            {
                Integer[] dataset = generateDataset(size);

                for (int j = 0; j < NUM_ALGORITHMS; j++)
                {
                    Integer[] localCopy = new Integer[size];
                    System.arraycopy(dataset, 0, localCopy, 0, dataset.length);
                    times[j][i] = runDataset(localCopy, j);
                }
            }

            for (int algorithm = 0; algorithm < NUM_ALGORITHMS; algorithm++)
            {
                for (int test = 0; test < NUM_TESTS; test++)
                {
                    means[algorithm] += times[algorithm][test];
                }
                means[algorithm] /= NUM_TESTS;
            }

            double[] stdDevs = generateStandardDeviations(means, times);

            printTValues(times, means);

            outputFile(means, stdDevs, size);
        }
    } // end main

    /**
     * Calculates the standard deviations at a certain dataset size.
     *
     * @param means an array of the means of each algorithm at this dataset size
     * @param times a 2D array of times it took each algorithm in each test to run.
     * @return An array of standard deviations for this size.
     */
    private static double[] generateStandardDeviations(double[] means, long[][] times)
    {
        double[] deviations = new double[NUM_ALGORITHMS];

        for (int i = 0; i < NUM_ALGORITHMS; i++)
        {
            double total = 0;
            for (int j = 0; j < NUM_TESTS; j++)
            {
                total += Math.pow(times[i][j] - means[i], 2);
            }
            deviations[i] = Math.sqrt(total / (NUM_TESTS - 1));
        }
        return deviations;
    } // end generateStandardDeviations

    /**
     * Generates a dataset of integers between 0 and 999, inclusive, of a given size.
     *
     * @param size Size of the array to generate
     * @return An integer array of randomly generated values.
     */
    private static Integer[] generateDataset(int size)
    {
        Random rnd = new Random();
        Integer[] dataset = new Integer[size];
        for (int i = 0; i < size; i++)
        {
            dataset[i] = rnd.nextInt(1000);
        }

        return dataset;
    } // end generateDataset

    /**
     * Runs a given dataset to find the time it took for that set to be sorted by a given algorithm.
     *
     * @param dataset     dataset to be sorted
     * @param algorithmID sorting algorithm to be used
     * @return number of milliseconds it took the algorithm to run
     */
    private static long runDataset(Integer[] dataset, int algorithmID)
    {
        long initTime = System.currentTimeMillis();
        switch (algorithmID)
        {
            case 0:
                Sorting.selectionSort(dataset, dataset.length);
                break;
            case 1:
                Sorting.bubbleSort(dataset, dataset.length);
                break;
            case 2:
                Sorting.insertionSort(dataset, dataset.length);
                break;
            case 3:
                Sorting.mergeSort(dataset, 0, dataset.length - 1);
                break;
            case 4:
                Sorting.quickSort(dataset, 0, dataset.length - 1);
                break;
            case 5:
                Sorting.radixSort(dataset, 3, 10);
                break;
        }
        return System.currentTimeMillis() - initTime;
    } // end runDataset

    /**
     * Writes a single line of the output file.
     *
     * @param means   an array of the means for each algorithm at this size
     * @param stdDevs an array of the standard deviations of the algorithm at this size
     * @param size    the size of items in the datasets
     */
    private static void outputFile(double[] means, double[] stdDevs, int size)
    {
        try
        {
            File output = new File("timings.dat");
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(output, true)));

            writer.write(((String.valueOf(size))));
            for (int i = 0; i < NUM_ALGORITHMS; i++)
            {
                writer.write(" " + String.valueOf(means[i]) + " " + String.valueOf(stdDevs[i]));
            }
            writer.write("\n");

            writer.close();

        } catch (IOException e)
        {
            System.err.print("IO Exception!" + e + "\n");
        }
    } // end outputFile

    /**
     * Calculates the t-values for all the algorithms at the current size,
     * and prints the results to console
     *
     * @param times A 2D array of all the times for the algorithms
     * @param means An array of the mean time for each algorithm
     */
    private static void printTValues(long[][] times, double[] means)
    {
        System.out.println("\nNEW DATASET\n");

        for (int algorithmA = 0; algorithmA < NUM_ALGORITHMS - 1; algorithmA++)
        {
            for (int algorithmB = algorithmA + 1; algorithmB < NUM_ALGORITHMS; algorithmB++)
            {
                double total = 0;
                for (int numTrial = 0; numTrial < NUM_TESTS; numTrial++)
                {
                    total += Math.pow(times[algorithmA][numTrial] - means[algorithmA] - (times[algorithmB][numTrial] - means[algorithmB]), 2);
                }
                double tValue = Math.abs(means[algorithmA] - means[algorithmB]) * Math.sqrt(NUM_TESTS * (NUM_TESTS - 1) / total);
                System.out.printf("%s vs. %s: %s (t = %.3f)\n",
                        getAlgorithmFromID(algorithmA), getAlgorithmFromID(algorithmB), tValue > 1.812461 ? "SIGNIFICANT" : "INSIGNIFICANT", tValue);
            }
        }
    } // end printTValues

    private static String getAlgorithmFromID(int ID)
    {
        switch (ID){
            case 0:
                return "Selection";
            case 1:
                return "Bubble";
            case 2:
                return "Insertion";
            case 3:
                return "Merge";
            case 4:
                return "Quick";
            case 5:
                return "Radix";
            default:
                return "Algorithm not recognized";
        }
    }
}
