import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class MergeManagerTest {

    @Test
    @DisplayName("Merge Mechanic Test")
    public void testPieceMerge() {
        int[][] board = new int[15][15];
        // Assume MergeManager is the class you created
        MergeManager mergeMgr = new MergeManager(board);

        // Set up: 2 black pieces
        board[7][5] = 1;
        board[7][6] = 1;

        // Action: Place the 3rd piece
        board[7][7] = 1;
        mergeMgr.checkAndExecuteMerge(7, 7, 1);

        // Assert: Check if the piece at (7,7) evolved to ID 3
        assertEquals(3, board[7][7], "The piece should merge into a Super Piece (ID 3)");
    }
}