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
    void testInitialState() {
        assertFalse(history.canUndo(), "Should not be able to undo at start");
        assertFalse(history.canRedo(), "Should not be able to redo at start");
        assertEquals(0, history.getStepCount(), "Step count should be 0");
    }

}
