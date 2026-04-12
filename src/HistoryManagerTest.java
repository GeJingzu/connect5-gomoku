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

    @Test
    @DisplayName("Redo after undo")
    public void testRedo() {
        // Record and undo
        history.recordMove(7, 9, 1, testBoard);
        history.undo();

        // Redo
        HistoryManager.Step step = history.redo();

        // Verify
        assertNotNull(step, "Redo should return the step");
        assertEquals(7, step.row, "Redo position should be correct");
        assertEquals(9, step.col, "Redo position should be correct");
        assertEquals(1, step.player, "Redo player should be correct");
        assertTrue(history.canUndo(), "Should be able to undo after redo");
        assertFalse(history.canRedo(), "Should not be able to redo after redo");
    }
    @Test
    @DisplayName("New move clears redo stack")
    public void testNewMoveClearsRedo() {
        // First move -> undo -> new move (should clear redo stack)
        history.recordMove(7, 9, 1, testBoard);
        history.undo();

        // Modify board
        testBoard[8][8] = 1;
        history.recordMove(8, 8, 1, testBoard);

        // Redo stack should be cleared
        assertFalse(history.canRedo(), "Should not be able to redo old steps after new move");
    }

    @Test
    @DisplayName("Undo multiple steps sequentially")
    public void testMultipleUndos() {
        // Record 3 steps
        for (int i = 0; i < 3; i++) {
            history.recordMove(7, 7+i, (i % 2 == 0) ? 1 : 2, testBoard);
        }
        assertEquals(3, history.getStepCount());

        // Undo 3 times sequentially
        assertTrue(history.undo() != null);
        assertTrue(history.undo() != null);
        assertTrue(history.undo() != null);

        // Cannot undo further
        assertNull(history.undo(), "Cannot undo infinitely");
    }
}
