import org.junit.Before;
import org.junit.Test;

import javax.swing.JLabel;

import static org.junit.Assert.assertEquals;

public class EndgameManagerTest {
    private EndgameManager manager;
    private int[][] board;
    private JLabel testLabel;

    @Before
    public void setUp() {
        // Create a new 15x15 board for testing
        board = new int[15][15];
        testLabel = new JLabel();
        // Create the manager object
        manager = new EndgameManager(board, testLabel);
    }

    // Test if the board can be correctly cleared.
    @Test
    public void testClearBoard() {
        // Put a piece on the board manually
        board[0][0] = 1;
        manager.clearBoard();

        // Check if the cell is 0 now
        assertEquals("Board should be empty", 0, board[0][0]);
    }
}