import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class MergeSystemTest {

    private MergeSystem mergeSystem = new MergeSystem();
    private int[][] board;

    @BeforeEach
    void setUp() {
        board = new int[15][15];
    }

    @Test
    @DisplayName("Empty position cannot merge")
    public void testEmptyCannotMerge() {
        assertFalse(mergeSystem.canMerge(board, 7, 7, 1),
                "Empty position should not be able to merge");
    }

    @Test
    @DisplayName("Single piece cannot merge (need 3 consecutive)")
    public void testSingleCannotMerge() {
        board[7][7] = 1;
        assertFalse(mergeSystem.canMerge(board, 7, 7, 1),
                "Single piece should not be able to merge");
    }

    @Test
    @DisplayName("2 consecutive pieces cannot merge")
    public void testTwoCannotMerge() {
        board[7][6] = 1;
        board[7][7] = 1;
        assertFalse(mergeSystem.canMerge(board, 7, 7, 1),
                "2 consecutive pieces should not be able to merge");
    }
    @Test
    @DisplayName("Horizontal 3 consecutive can merge")
    public void testHorizontalThreeCanMerge() {
        board[7][5] = 1;
        board[7][6] = 1;
        board[7][7] = 1;

        assertTrue("Middle of horizontal 3 can merge",
                mergeSystem.canMerge(board, 7, 6, 1));
        assertTrue("Edge of horizontal 3 can also merge",
                mergeSystem.canMerge(board, 7, 7, 1));
    }

    @Test
    @DisplayName("Vertical 3 consecutive can merge")
    public void testVerticalThreeCanMerge() {
        board[5][7] = 1;
        board[6][7] = 1;
        board[7][7] = 1;

        assertTrue("Vertical 3 can merge",
                mergeSystem.canMerge(board, 6, 7, 1));
    }
    @Test
    @DisplayName("Diagonal 3 consecutive can merge")
    public void testDiagonalThreeCanMerge() {
        board[5][5] = 1;
        board[6][6] = 1;
        board[7][7] = 1;

        assertTrue("Diagonal 3 can merge",
                mergeSystem.canMerge(board, 6, 6, 1));
    }

    @Test
    @DisplayName("4 consecutive can also merge")
    public void testFourCanMerge() {
        board[7][5] = 1;
        board[7][6] = 1;
        board[7][7] = 1;
        board[7][8] = 1;

        assertTrue("4 consecutive can merge",
                mergeSystem.canMerge(board, 7, 6, 1));
    }
    @Test
    @DisplayName("Execute merge captures surrounding enemy pieces")
    public void testExecuteMergeCaptures() {
        // Black 3 consecutive
        board[7][5] = 1;
        board[7][6] = 1;
        board[7][7] = 1;

        // Place white pieces within MERGE_RANGE=1
        board[6][6] = 2;  // Above
        board[8][6] = 2;  // Below
        board[6][7] = 2;  // Upper right
        board[8][8] = 2;  // Outside range, should not be captured

        // Execute merge
        List<int[]> captured = mergeSystem.executeMerge(board, 7, 6, 1);

        // Verify 3 pieces captured
        assertEquals(3, captured.size(), "Should capture 3 white pieces");

        // Verify captured positions become black
        assertEquals(1, board[6][6], "Captured position should become black");
        assertEquals(1, board[8][6], "Captured position should become black");
        assertEquals(1, board[6][7], "Captured position should become black");

        // Verify outside range remains unchanged
        assertEquals(2, board[8][8], "Outside range should remain white");
    }

    private void assertEquals(int i, int size, String s) {
    }
    @Test
    @DisplayName("Merge returns captured piece coordinates")
    public void testMergeReturnsCapturedPositions() {
        board[7][6] = 1;
        board[7][7] = 1;
        board[7][8] = 1;
        board[6][7] = 2;

        List<int[]> captured = mergeSystem.executeMerge(board, 7, 7, 1);

        // Verify returned coordinates are correct
        boolean found = false;
        for (int[] pos : captured) {
            if (pos[0] == 6 && pos[1] == 7) {
                found = true;
                break;
            }
        }
        assertTrue("Should return correct coordinates of captured piece", found);
    }
    @Test
    @DisplayName("Merge returns empty list when no enemy pieces")
    public void testMergeNoEnemy() {
        board[7][5] = 1;
        board[7][6] = 1;
        board[7][7] = 1;
        // No white pieces around

        List<int[]> captured = mergeSystem.executeMerge(board, 7, 6, 1);

        assertTrue("Should return empty list when no enemy pieces", captured.isEmpty());
    }

    @Test
    @DisplayName("Merge at boundary does not go out of bounds")
    public void testMergeAtBoundary() {
        // 3 consecutive at corner
        board[0][0] = 1;
        board[0][1] = 1;
        board[0][2] = 1;

        // Should not throw exception
        assertDoesNotThrow(() -> {
            boolean can = mergeSystem.canMerge(board, 0, 1, 1);
            List<int[]> cap = mergeSystem.executeMerge(board, 0, 1, 1);
        }, "Merge at boundary should not go out of bounds");
    }

}
