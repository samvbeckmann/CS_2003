import java.util.Random;

/**
 * Implementation class for testing sorts in Labs 11 and 12
 * @author Sam Beckmann
 * ID:
 * CS 2003-01
 */
public class SortDriver
{
    static final int[] sampleSizes = {100, 1000, 10000};
    static final int numTests = 10;

    public static void main(String[] args)
    {
        for (int size : sampleSizes)
        {
            for (int i = 0; i < numTests; i++)
            {
                int[] dataset = generateDataset(10);

            }
        }
    }

    /**
     * Generates a dataset of integers between 0 and 999, inclusive, of a given size.
     *
     * @param size Size of the array to generate
     * @return An integer array of randomly generated values.
     */
    private static int[] generateDataset(int size)
    {
        Random rnd = new Random();
        int [] dataset = new int[size];
        for (int i = 0; i < size; i++)
        {
            dataset[i] = rnd.nextInt(1000);
        }

        int[] thing = new int[10];

        return dataset;
    }
}
