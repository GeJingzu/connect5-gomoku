import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import java.util.List;

import static org.junit.Assert.*;

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

        assertTrue(mergeSystem.canMerge(board, 7, 6, 1),
                "Middle of horizontal 3 can merge");
        assertTrue(mergeSystem.canMerge(board, 7, 7, 1),
                "Edge of horizontal 3 can also merge");
    }

    @Test
    @DisplayName("Vertical 3 consecutive can merge")
    public void testVerticalThreeCanMerge() {
        board[5][7] = 1;
        board[6][7] = 1;
        board[7][7] = 1;

        assertTrue(mergeSystem.canMerge(board, 6, 7, 1),
                "Vertical 3 can merge");
    }
    @Test
    @DisplayName("Diagonal 3 consecutive can merge")
    public void testDiagonalThreeCanMerge() {
        board[5][5] = 1;
        board[6][6] = 1;
        board[7][7] = 1;

        assertTrue(mergeSystem.canMerge(board, 6, 6, 1),
                "Diagonal 3 can merge");
    }

    @Test
    @DisplayName("4 consecutive can also merge")
    public void testFourCanMerge() {
        board[7][5] = 1;
        board[7][6] = 1;
        board[7][7] = 1;
        board[7][8] = 1;

        assertTrue(mergeSystem.canMerge(board, 7, 6, 1),
                "4 consecutive can merge");
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

}
