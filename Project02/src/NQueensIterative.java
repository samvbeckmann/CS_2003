import java.util.Random;

/**
 * Iterative solution to the N-Queens problem
 *
 * @author Sam Beckmann
 * ID: 1443946
 * CS 2003-01
 */
public class NQueensIterative
{

    /**
     * indicate an empty square
     */
    public static final int EMPTY = 0;

    /**
     * indicate square contains a queen
     */
    public static final int QUEEN = -1;

    /**
     * Random generator for NQueensIterative
     */
    private Random rnd;

    /**
     * squares per row or column
     */
    private int BOARD_SIZE;

    /**
     * chess board: each entry <code>board[i][j]</code> of the board
     * can take the following values:
     * <li> QUEEN = -1 : indicating the presence of a queen
     * <li> EMPTY = 0  : indicating an empty cell
     * <li> <code>i>0</code> : where <code>i</code> number of queens that can
     * attack the cell <code>(i,j)</code>
     */
    public int [][] board;

    /**
     * Vector storing the rows of the current queen in each column.
     */
    private int [] queenPositions;

    /**
     * number of locations that have been checked to be under attack
     * at some point in the search process
     */
    private int isUnderAttackCalls;

    /**
     * current number of placed queens
     */
    private int numPlacedQueens;

    public NQueensIterative(int size)
    {
        BOARD_SIZE = size;
        board = new int[size][size];
        queenPositions = new int [size];
        isUnderAttackCalls = 0;
        numPlacedQueens = 0;
        rnd = new Random();
    } // end constructor

    /**
     * Solves the n queens problem
     *
     * @return true if a solution was found, otherwise false.
     */
    public boolean placeQueens()
    {
        // see if the operation is possible
        if (BOARD_SIZE < 4)
            return false;

        // randomly initialize queens, one per column
        for (int i = 0; i < BOARD_SIZE; i++)
        {
            placeQueenRandomly(i);
        }

        while(true)
        {
            int columnToMove = underMostAttack();

            // return true if the operation is complete.
            if (columnToMove == -1)
            {
                markBoard();
                return true;
            }
            else
            {
                int newRow = rowUnderLeastAttack(columnToMove);
                moveQueen(queenPositions[columnToMove], newRow, columnToMove);
            }
        }
    } // end placeQueens

    /**
     * Finds the number of queens attacking the square at <code>row, column</code>.
     *
     * @param row Row of square being checked.
     * @param col Column of square being checked.
     * @return Number of queens attacking the square in question. If <code>0</code>,
     *         square is not being attacked by any queen.
     */
    private int isUnderAttack(int row, int col)
    {
        isUnderAttackCalls++;
        int counter = 0;

        // check the row that the queen is in.
        for (int i = 0; i < BOARD_SIZE; i++)
        {
            if(board[row][i] != EMPTY && i != col)
                counter++;
        }

        // check the columns in front of the queen
        int step = 1;
        while (col + step < BOARD_SIZE)
        {
            if (row + step < BOARD_SIZE)
                if (board[row + step][col + step] != EMPTY)
                    counter++;
            if (row - step >= 0)
                if (board[row - step][col + step] != EMPTY)
                    counter++;
            step++;
        }

        // check the columns behind the queen
        step = 1;
        while (col - step >= 0)
        {
            if (row + step < BOARD_SIZE)
                if (board[row + step][col - step] != EMPTY)
                    counter++;
            if (row - step >= 0)
                if (board[row - step][col - step] != EMPTY)
                    counter++;
            step++;
        }
        return counter;
    } // end isUnderAttack

    /**
     * Checks each queen for the number of other queens that are attacking it,
     * and gives the user the column of the queen under the most attacks.
     *
     * @return the column which contains the queen under the most attacks.
     */
    private int underMostAttack()
    {
        int start = rnd.nextInt(BOARD_SIZE);
        int currMaxAttacks = 0;
        int currMaxCol = -1;
        for (int i = 0; i < BOARD_SIZE; i++)
        {
            int column = (i + start) % BOARD_SIZE;
            if (isUnderAttack(queenPositions[column], column) > currMaxAttacks)
            {
                currMaxCol = column;
                currMaxAttacks = isUnderAttack(queenPositions[column], column);
            }
        }
        return currMaxCol;
    } // end underMostAttack

    /**
     * Searches a given column to find the row under the least amount of attack.
     * Chooses randomly between rows equally under attack.
     *
     * @param col Column being searched.
     * @return The row under the least amount of attack.
     */
    private int rowUnderLeastAttack(int col)
    {
        int start = rnd.nextInt(BOARD_SIZE);
        int currMinAttacks = Integer.MAX_VALUE;
        int currMinRow = -1;
        for (int i = 0; i < BOARD_SIZE; i++)
        {
            int row = (i + start) % BOARD_SIZE;
            if (isUnderAttack(row, col) < currMinAttacks)
            {
                currMinRow = row;
                currMinAttacks = isUnderAttack(row, col);
            }
        }
        return currMinRow;
    } // end rowUnderLeastAttack

    /**
     * Randomly places a queen in a row in the given column.
     *
     * @param col column to place the queen in.
     */
    private void placeQueenRandomly(int col)
    {
        int row = rnd.nextInt(BOARD_SIZE);
        board[row][col] = QUEEN;
        queenPositions[col] = row;
        numPlacedQueens++;
    } // end placeQueenRandomly

    /**
     * Moves a queen from one row to another in a column.
     *
     * @param prevRow Row the queen was in previously.
     * @param newRow  Row the queen is being moved to.
     * @param col     Column queen is being moved in.
     */
    private void moveQueen(int prevRow, int newRow, int col)
    {
        board[prevRow][col] = EMPTY;
        board[newRow][col] = QUEEN;
        queenPositions[col] = newRow;
        numPlacedQueens++;
    } // end moveQueen

    /**
     * When the queens have been properly placed, marks the squares
     * attacked by each queen to create a heatmap of attacks on squares.
     */
    private void markBoard()
    {
        for (int col = 0; col < BOARD_SIZE; col++)
        {
            int row = queenPositions[col];
            markForward(row, col);
            markBackward(row, col);
            markColumn(col);
        }
    } // end markBoard

    /**
     * Marks up to three squares in each column after the given column,
     * indicating squares attacked by a queen placed at <code>row, column</code>.
     *
     * @param row Row queen is placed in.
     * @param col Column queen is placed in.
     */
    private void markForward(int row, int col)
    {
        int step = 1;
        while(col + step < BOARD_SIZE)
        {
            // mark same row in marking column
            if (board[row][col + step] == QUEEN)
                System.err.print("Queens attacking each other when should be solved!");
            else
                board[row][col + step]++;

            // mark the downwards diagonal
            if (row + step < BOARD_SIZE)
                if (board[row + step][col + step] == QUEEN)
                    System.err.print("Queens attacking each other when should be solved!");
                else
                    board[row + step][col + step]++;

            // mark the upwards diagonal
            if (row - step >= 0)
                if (board[row - step][col + step] == QUEEN)
                    System.err.print("Queens attacking each other when should be solved!");
                else
                    board[row - step][col + step]++;

            step++;
        }
    } // end markForwards

    /**
     * Marks up to three squares in each column before the given column,
     * indicating squares attacked by a queen placed at <code>row, column</code>.
     *
     * @param row Row queen is placed in.
     * @param col Column queen is placed in.
     */
    private void markBackward(int row, int col)
    {
        int step = 1;
        while(col - step >= 0)
        {
            // mark same row in marking column
            if (board[row][col - step] == QUEEN)
                System.err.print("Queens attacking each other when should be solved!");
            else
                board[row][col - step]++;

            // mark the downwards diagonal
            if (row + step < BOARD_SIZE)
                if (board[row + step][col - step] == QUEEN)
                    System.err.print("Queens attacking each other when should be solved!");
                else
                    board[row + step][col - step]++;

            // mark the upwards diagonal
            if (row - step >= 0)
                if (board[row - step][col - step] == QUEEN)
                    System.err.print("Queens attacking each other when should be solved!");
                else
                    board[row - step][col - step]++;

            step++;
        }
    } // end markBackwards

    /**
     * Marks all the squares in a column not occupied by a queen.
     *
     * @param col Column to be marked.
     */
    private void markColumn(int col)
    {
        for (int i = 0; i < BOARD_SIZE; i++)
        {
            if (board[i][col] != QUEEN)
                board[i][col]++;
        }
    } // end markColumn

    /**
     * Gets the stats for the solution to this problem, formatted in HTML
     *
     * @return HTML formatted stats for found solution.
     */
    public String getStatsInHTML()
    {
        return
                "Statistics for NQueensRecursive on a " + BOARD_SIZE + " x " + BOARD_SIZE
                        + " chess board <br>"
                        + "Number of isUnderAttack() calls : " + isUnderAttackCalls + "<br>"
                        + "Number of times Queens were placed: " + numPlacedQueens + "<br>";
    }

} // end NQueensIterative
