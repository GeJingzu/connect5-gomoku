import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.Assert.*;

public class MergeSystemTest {

    private MergeSystem mergeSystem;
    private int[][] board;

    @BeforeEach
    void setUp() {
        mergeSystem = new MergeSystem();
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
}
