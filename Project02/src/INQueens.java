/**
 * Interface for solutions to the n-queens problem.
 *
 * @author Sam Beckmann
 * ID: 1443946
 * CS 2003-01
 */
public interface INQueens
{
    /**
     * Solves the n queens problem
     *
     * @return true if a solution was found, otherwise false.
     */
    public boolean placeQueens();

    /**
     * Gets the stats for the solution to this problem, formatted in HTML
     *
     * @return HTML formatted stats for found solution.
     */
    public String getStatsInHTML();

    /**
     * Returns the value from the board in the given position.
     *
     * @param row row of board
     * @param col column of board
     * @return The value of the board space at [row, column]
     */
    public int getValueFromBoard(int row, int col);
}
