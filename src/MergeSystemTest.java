import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.Assert.assertFalse;

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
}
