import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

public class HistoryManagerTest {
    private HistoryManager history;
    private int[][] testBoard;

    @BeforeEach
    void setUp() {
        history = new HistoryManager();
        testBoard = new int[15][15];
        // Initialize some pieces
        testBoard[7][7] = 1;
        testBoard[7][8] = 2;
    }

    @Test
    @DisplayName("Initial state: cannot undo or redo")
    public void testInitialState() {
        assertFalse(history.canUndo(), "Should not be able to undo at start");
        assertFalse(history.canRedo(), "Should not be able to redo at start");
        assertEquals(0, history.getStepCount(), "Step count should be 0");
    }
    @Test
    @DisplayName("Can undo after recording a move")
    public void testRecordAndUndo() {
        // Record a move
        history.recordMove(7, 9, 1, testBoard);

        // Verify state
        assertTrue(history.canUndo(), "Should be able to undo after recording");
        assertFalse(history.canRedo(), "Should not be able to redo after recording");
        assertEquals(1, history.getStepCount(), "Step count should be 1");

        // Execute undo
        int[][] restored = history.undo();

        // Verify
        assertNotNull(restored, "Undo should return board state");
        assertFalse(history.canUndo(), "Cannot undo after undoing all");
        assertTrue(history.canRedo(), "Should be able to redo after undo");
        assertEquals(0, history.getStepCount(), "Step count should be 0 after undo");

        // Verify restored board is correct
        assertEquals(1, restored[7][7], "Board should be restored to original state");
        assertEquals(2, restored[7][8], "Board should be restored to original state");
    }
}
