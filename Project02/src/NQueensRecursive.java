/**
 * Recursive solution to the N-Queens problem
 *
 * @author Sam Beckmann
 * ID: 1443946
 * CS 2003-01
 */
class NQueensRecursive extends AbstractNQueens
{
    /**
     * current number of backtracks
     */
    private int backTracks;

    /**
     * creates an empty square board
     */
    public NQueensRecursive(int size)
    {
        super(size);
        backTracks = 0;
    }  // end constructor         


    /**
     * Places queens by calling PlaceQueens with the first column.
     *
     * @return If a solution is found, each column of the board
     * contains one queen and method returns true; otherwise,
     * returns false (no solution exists for a queen anywhere
     * in column specified).
     */
    public boolean placeQueens()
    {
        return placeQueens(0);
    }


    /**
     * Places queens in the specified column of the board and
     * recursively place the queens on the successive columns when
     * possible
     *
     * @param column where the new queen is added. It is assumed that
     *               queens are correctly placed in columns <code>1</code> through
     *               <code>column-1</code>
     * @return If a solution is found, each column of the board
     * contains one queen and method returns true; otherwise, returns
     * false (no solution exists for a queen anywhere in column
     * specified).
     */
    private boolean placeQueens(int column)
    {
        if (column == BOARD_SIZE)
        {
            return true;  // base case
        } else
        {
            boolean queenPlaced = false;
            int row = 0;  // number of square in column
            while (!queenPlaced && (row < BOARD_SIZE))
            {
                // if square can be attacked
                if (isUnderAttack(row, column))
                {
                    ++row;  // consider next row in column
                } // end if
                else
                { // place queen and consider next column
                    setQueenAndMarks(row, column);
                    queenPlaced = placeQueens(column + 1);
                    // if no queen can be placed in the next column,
                    if (!queenPlaced)
                    {
                        // backtrack: remove queen placed earlier
                        // and try next row in column
                        removeQueenAndMarks(row, column);
                        ++row;
                        ++backTracks;
                    } // end if
                } // end else
            } // end while
            return queenPlaced;
        } // end else
    } // end placeQueens


    /**
     * Sets a queen at the location indicated by the specified row and
     * column and marks the columns attacked by this queen and no
     * other queen placed in prior columns.
     *
     * @param row    where the new queen is located
     * @param column where the new queen is located
     */
    private void setQueenAndMarks(int row, int column)
    {
        board[row][column] = QUEEN;
        markBoard(row, column, 1);
        numPlacedQueens++;
    }  // end setQueen


    /**
     * Removes a queen at the location indicated by row and column,
     * and marks in the following columns to denote attack by this
     * queen.
     * <li> Precondition: A queen was placed in this position
     * and it is being removed as part of the backtracking process.
     * <li> Postcondition: Sets the square on the board in a given row and
     * column to EMPTY.  Also unmark all board positions attacked by
     * this queen and not by any previous queens.
     *
     * @param row    where the queen to be removed is located
     * @param column where the queen to be removed is located
     */
    private void removeQueenAndMarks(int row, int column)
    {
        markBoard(row, column, -1);
        board[row][column] = EMPTY;
    }  // end removeQueen


    /**
     * Updates the marking of the board, called when a queen is placed or removed.
     *
     * @param row    the row of the changed queen
     * @param column the column of the changed queen
     * @param marker identifies whether a queen is being placed or removed
     */
    private void markBoard(int row, int column, int marker)
    {
        markForward(row, column, 1, marker);
        markBackward(row, column, 1, marker);
        markColumn(column, marker);
    } // end markBoard


    /**
     * Marks the column number <code>col + step</code> to denote the
     * locations that can be attacked by a queen on the square in question.  <li>
     * Precondition: A queen was placed or removed form the position (row, col) and
     * the (col+step)th column is being marked as part of the
     * lookahead process.  <li> Postcondition: Marks up to three
     * squares on the board in column (col+step).  These squares are
     * the one on the same row and the ones on the diagonals emanating
     * from the queen placed at (row, col).
     *
     * @param row    the row of the changed queen
     * @param col    the column of the changed queen
     * @param step   identifies the state of the markup process: the
     *               <code>col+step</code> column will be marked up
     * @param marker identifies whether a queen is being placed or removed
     */
    private void markForward(int row, int col, int step,
                             int marker)
    {
        if (col + step < BOARD_SIZE)
        {
            board[row][col + step] += marker;
            if (row + step < BOARD_SIZE)
            {
                board[row + step][col + step] += marker;
            }
            if (row - step >= 0)
            {
                board[row - step][col + step] += marker;
            }
            markForward(row, col, step + 1, marker);
        }
    } // ned markForward


    /**
     * Marks the column number <code>col - step</code> to denote the
     * locations that can be attacked by a queen on the square in question.  <li>
     * Precondition: A queen was placed or removed form the position (row, col) and
     * the (col-step)th column is being marked as part of the
     * lookahead process.  <li> Postcondition: Marks up to three
     * squares on the board in column (col-step).  These squares are
     * the one on the same row and the ones on the diagonals emanating
     * from the queen placed at (row, col).
     *
     * @param row    the row of the changed queen
     * @param col    the column of the changed queen
     * @param step   identifies the state of the markup process: the
     *               <code>col+step</code> column will be marked up
     * @param marker identifies whether a queen is being placed or removed
     */
    private void markBackward(int row, int col, int step, int marker)
    {
        if (col - step >= 0)
        {
            board[row][col - step] += marker;
            if (row + step < BOARD_SIZE)
            {
                board[row + step][col - step] += marker;
            }
            if (row - step >= 0)
            {
                board[row - step][col - step] += marker;
            }
            markBackward(row, col, step + 1, marker);
        }
    } // end markBackward


    /**
     * Marks the given column to the locations that can be attacked by a queen
     * in the position (row, column).
     *
     * @param col    the column being marked
     * @param marker identifies whether a queen is being placed or removed
     */
    private void markColumn(int col, int marker)
    {
        for (int row = 0; row < BOARD_SIZE; row++)
        {
            if (board[row][col] != QUEEN)
            {
                board[row][col] += marker;
            }
        }
    } // end markColumn


    /**
     * Determines whether the square on the board at a given row and
     * column is under attack by any queens in the columns 1 through
     * column-1.  <li> Precondition: Each column between 1 and
     * column-1 has a queen placed in a square at a specific row.
     * None of these queens can be attacked by any other queen.
     *
     * @param row    of the considered square of the board
     * @param column of the considered square of the board
     * @return If the designated square is under attack, returns true;
     * otherwise, returns false.
     */
    private boolean isUnderAttack(int row, int column)
    {
        isUnderAttackCalls++;
        return board[row][column] != EMPTY;
    }  // end isUnderAttack


    /**
     * Gets the stats for the solution to this problem, formatted in HTML
     *
     * @return HTML formatted stats for found solution.
     */
    public String getStatsInHTML()
    {
        return super.getStatsInHTML() +
                        "Number of Back Tracks: " + backTracks + "<br>";
    } // end getStatsinHTML
} // end NQueensRecursive
