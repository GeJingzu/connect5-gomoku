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

    // Test if Level 1 loads pieces at the correct coordinates.
    @Test
    public void testLevel1Coordinates() {
        // Load the first level
        manager.setupLevel(1);

        // Verify the black pieces (1) at (7,7) and (7,8)
        assertEquals("Check (7,7)", 1, board[7][7]);
        assertEquals("Check (7,8)", 1, board[7][8]);

        // Check a random empty spot
        assertEquals("Check (0,0) is empty", 0, board[0][0]);
    }

    //Test if an invalid level ID clears the board.
    @Test
    public void testInvalidLevel() {
        // Use an ID that does not exist
        manager.setupLevel(99);

        // The board should be empty
        assertEquals("Invalid level should be empty", 0, board[7][7]);
    }
}