import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class NQueensGUI implements ActionListener
{
    /**
     * Class that solves the NQueens problem recursively
     */
    NQueensRecursive nQueensRecursive;

    /**
     * Class that solves the NQueens problem iteratively
     */
    NQueensIterative nQueensIterative;

    /**
     * Main frame that contains everything
     */
    JFrame boardFrame;

    /**
     * Main panek that contains all the widgets
     */
    JPanel boardPanel;

    /**
     * TextField to enter the size <code>N</code> of the chess board
     */
    JTextField sizeField;

    /**
     * Label for the text field
     */
    JLabel sizeLabel;

    /**
     * Button to launch the search of an assignment of the N queens
     * on the chess board (recursive method)
     */
    JButton recursiveButton;

    /**
     * Button to launch the search of an assignment of the N queens
     * on the chess board (iterative method)
     */
    JButton iterativeButton;

    /**
     * model for the board represented by a Table
     */
    BoardModel bModel;

    /**
     * Jtable representing the chess board
     */
    JTable board;

    /**
     * Label to indicates the status of the search
     */
    JLabel statusLabel;

    /**
     * renderer to color the board
     */
    TableCellRenderer renderer;

    /**
     * label to indicates the statistics of the resolution process
     */
    JLabel statsLabel;

    /**
     * Solution used to solve the problem
     * <code>1 = recursive implementation</code><p>
     * <code>2 = iterative implementation</code>
     */
    int solution;

    /**
     * Constructor
     */
    public NQueensGUI()
    {
        JFrame.setDefaultLookAndFeelDecorated(true);
        // create and set up the window
        boardFrame = new JFrame("NQueensRecursive");
        boardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // create and set up the pannel
        boardPanel = new JPanel();
        boardPanel.setOpaque(true);
        // Add the widgets
        addWidgets();
        // Add the panel to the window.
        boardFrame.getContentPane().add(boardPanel, BorderLayout.CENTER);
        // Set the button to be default button in the frame.
        boardFrame.getRootPane().setDefaultButton(recursiveButton);
        // Display the window.
        boardFrame.setSize(new Dimension(600, 750));
        boardFrame.setVisible(true);
    }

    /**
     * creates and adds all the widgets to the Panel
     */
    public void addWidgets()
    {
        // textfield to input N
        sizeField = new JTextField(2);
        sizeField.setHorizontalAlignment(JTextField.TRAILING);
        sizeField.setSize(30, 10);
        sizeLabel = new JLabel("number of queens");
        // button to launch the recursive resolution
        recursiveButton = new JButton("Recursive");
        recursiveButton.setSize(30, 20);
        // button to launch the iterative resolution
        iterativeButton = new JButton("Iterative");
        iterativeButton.setSize(30, 20);
        // status label
        statusLabel = new JLabel();
        statusLabel.setSize(40, 10);
        // board
        bModel = new BoardModel();
        board = new JTable(bModel);
        board.setGridColor(Color.CYAN);
        board.setShowGrid(true);
        board.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane scrollPane = new JScrollPane(board);
        renderer = new CustomTableCellRenderer();
        // label for the statistics of the resolution
        statsLabel = new JLabel();
        statsLabel.setHorizontalTextPosition(4);
        board.setDefaultRenderer(Object.class, renderer);
        // Listen to the events from the place queens button
        recursiveButton.addActionListener(this);
        iterativeButton.addActionListener(this);
        // add the widget to the container
        boardPanel.add(sizeLabel);
        boardPanel.add(sizeField);
        boardPanel.add(recursiveButton);
        boardPanel.add(iterativeButton);
        boardPanel.add(statusLabel);
        boardPanel.add(scrollPane, BorderLayout.CENTER);
        boardPanel.add(statsLabel);
    }

    /**
     * action when the user click on the button to launch the
     * resolution
     */
    public void actionPerformed(ActionEvent event)
    {
        // object for the model
        Object[][] data = null;
        statusLabel.setText("Computing ...");
        bModel.update(data);
        statsLabel.setText("<html><font face='Verdana' color = 'gray'>"
                + "<hr> Computing <hr><br>"
                + "</font></html>");
        statusLabel.repaint();
        statsLabel.repaint();
        board.repaint();
        int size = Integer.parseInt(sizeField.getText());
        // create the Queens
        if (event.getSource() == recursiveButton)
            nQueensRecursive = new NQueensRecursive(size);
        else
            nQueensIterative = new NQueensIterative(size);

        // get the current time

        sizeField.setText("");
        long cputime = 0, finish = 0, start = System.currentTimeMillis();
        if (event.getSource() == recursiveButton)
        {
            solution = 1;
            if (nQueensRecursive.placeQueens())
            {// success!
                finish = System.currentTimeMillis(); // get the current time
                statusLabel.setText("<html><font face='Verdana' color = 'gray'>"
                        + "<br><br><br>A solution to " + size + " Queens"
                        + "</font></html>");
                //	    statusLabel.setText("Solution Found");
                data = new Object[size][size];
                Object[] columnName = new String[size];
                cputime = finish - start;
                // prepare the table: leave blank where there is no queen
                // write Q where there is a queen
                for (int i = 0; i < size; i++)
                {
                    columnName[i] = Integer.toString(i);
                    for (int j = 0; j < size; j++)
                        if (nQueensRecursive.board[i][j] == NQueensRecursive.QUEEN)
                            data[i][j] = "Q";
                        else
                            data[i][j] = "";
                }
            } else
            { // failure no solution for NQueensRecursive was found!
                data = null;
                statusLabel.setText("No solution found!");
            }

            // update the GUI
            bModel.update(data);
            statsLabel.setText("<html><font face='Verdana' color = 'gray'>"
                    + "CPU time =" + ((float) cputime / 1000) + "sec<br>"
                    + nQueensRecursive.getStatsInHTML()
                    + "</font></html>");
        } else
        {
            solution = 2;
            if (nQueensIterative.placeQueens())
            {// success!
                finish = System.currentTimeMillis(); // get the current time
                statusLabel.setText("<html><font face='Verdana' color = 'gray'>"
                        + "<br><br><br>A solution to " + size + " Queens"
                        + "</font></html>");
                //	    statusLabel.setText("Solution Found");
                data = new Object[size][size];
                Object[] columnName = new String[size];
                cputime = finish - start;
                // prepare the table: leave blank where there is no queen
                // write Q where there is a queen
                for (int i = 0; i < size; i++)
                {
                    columnName[i] = Integer.toString(i);
                    for (int j = 0; j < size; j++)
                        if (nQueensIterative.board[i][j] == NQueensRecursive.QUEEN)
                            data[i][j] = "Q";
                        else
                            data[i][j] = "";
                }
            } else
            { // failure no solution for NQueensRecursive was found!
                data = null;
                statusLabel.setText("No solution found!");
            }
            // update the GUI
            bModel.update(data);
            statsLabel.setText("<html><font face='Verdana' color = 'gray'>"
                    + "CPU time =" + ((float) cputime / 1000) + "sec<br>"
                    + nQueensIterative.getStatsInHTML()
                    + "</font></html>");
            this.board.repaint();
        }
    }

    /**
     * main
     */
    public static void main(String[] args)
    {
        JFrame.setDefaultLookAndFeelDecorated(true);
        new NQueensGUI();
    }

    /**
     * Model for the board
     */
    public class BoardModel extends AbstractTableModel
    {
        public Object[][] data;

        public int getColumnCount()
        {
            if (data != null)
                return data.length;
            else
                return 0;
        }

        public int getRowCount()
        {
            if (data != null)
                return data.length;
            else
                return 0;
        }

        public Object getValueAt(int row, int col)
        {
            return data[row][col];
        }

        public void update(Object[][] objArray)
        {
            int oldSize = 0;
            int newSize = 0;
            if (data != null)
                oldSize = data.length;
            if (objArray != null)
                newSize = objArray.length;
            data = objArray;
            if (newSize < oldSize)
                fireTableRowsDeleted(newSize, oldSize - 1);
            if (newSize > oldSize)
                fireTableRowsInserted(oldSize, newSize - 1);
            fireTableStructureChanged();
            for (int i = 0; i < newSize; i++)
                for (int j = 0; j < newSize; j++)
                    fireTableCellUpdated(i, j);
            TableColumn column = null;
            for (int i = 0; i < newSize; i++)
            {
                column = board.getColumnModel().getColumn(i);
                column.setPreferredWidth(10);
            }
        }
    }// end Table Model

    /**
     * Renderer to color the table: cells containing a queen have a
     * red background, the level of gray of the other cells depends on
     * the number of queens that can attack the position: it is darker
     * when more queens can attack the position.
     * <p/>
     * Warning: use the board of the NQueensRecursive class to know how many
     * queens can attack a given location
     */
    public class CustomTableCellRenderer extends DefaultTableCellRenderer
    {
        public Component getTableCellRendererComponent(JTable table,
                                                       Object value,
                                                       boolean isSelected,
                                                       boolean hasFocus,
                                                       int row, int column)
        {
            // obtain a cell
            Component cell = super.getTableCellRendererComponent(table, value,
                    isSelected,
                    hasFocus,
                    row, column);
            // color the cell
            if (value instanceof String)
            {
                if (((String) value).equals("Q")) // it is a queen
                    cell.setBackground(Color.RED);
                else
                {
                    int amount;
                    if (solution == 1)
                        amount = nQueensRecursive.board[row][column];
                    else
                        amount = nQueensIterative.board[row][column];
                    Color c;
                    if (amount * 15 < 255)
                        c = new Color(0, 0, 0, amount * 15);
                    else
                        c = Color.black;
                    cell.setBackground(c);
                }
            }
            return cell;
        }
    }// end renderer class


}// end PlaceQueens
