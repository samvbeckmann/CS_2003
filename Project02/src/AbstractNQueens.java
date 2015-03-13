/**
 * Abstract solution to n-queens, extended by implementations.
 *
 * @author Sam Beckmann
 * ID: 1443946
 * CS 2003-01
 */
public abstract class AbstractNQueens implements INQueens
{
    /**
     * squares per row or column
     */
    protected int BOARD_SIZE;

    /**
     * indicate an empty square
     */
    protected static final int EMPTY = 0;

    /**
     * indicate square contains a queen
     */
    protected static final int QUEEN = -1;

    /**
     * chess board: each entry <code>board[i][j]</code> of the board
     * can take the following values:
     * <li> QUEEN = -1 : indicating the presence of a queen
     * <li> EMPTY = 0  : indicating an empty cell
     * <li> <code>i>0</code> : where <code>i</code> number of queens that can
     * attack the cell <code>(i,j)</code>
     */
    protected int board[][];

    /**
     * number of locations that have been checked to be under attack
     * at some point in the search process
     */
    protected int isUnderAttackCalls;

    /**
     * current number of placed queens
     */
    protected int numPlacedQueens;

    /**
     * Constructor.
     *
     * @param size The size of one side of the square board.
     */
    public AbstractNQueens(int size)
    {
        BOARD_SIZE = size;
        board = new int[size][size];
        isUnderAttackCalls = 0;
        numPlacedQueens = 0;

    } // end constructor

    /**
     * Returns the value from the board in the given position.
     *
     * @param row row of board
     * @param col column of board
     * @return The value of the board space at [row, column]
     */
    public int getValueFromBoard(int row, int col)
    {
        return board[row][col];
    } // end getValueFromBoard

    /**
     * Solves the n queens problem
     *
     * @return true if a solution was found, otherwise false.
     */
    public abstract boolean placeQueens();

    /**
     * Gets the stats for the solution to this problem, formatted in HTML
     *
     * @return HTML formatted stats for found solution.
     */
    public String getStatsInHTML()
    {
        return
                "Statistics for N-Queens on a " + BOARD_SIZE + " x " + BOARD_SIZE
                        + " chess board <br>"
                        + "Number of isUnderAttack() calls : " + isUnderAttackCalls + "<br>"
                        + "Number of times Queens were placed: " + numPlacedQueens + "<br>";
    } // end getStatsInHTML
}
