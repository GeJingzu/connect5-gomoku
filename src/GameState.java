import static org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.helpers.AnnotationRegistry.reset;

public class GameState {
    private int[][] board;
    private int currentPlayer;
    private boolean gameOver;
    private int winner;
    private HistoryManager history;
    private MergeSystem mergeSystem;

    public GameState() {
        board = new int[Constants.BOARD_SIZE][Constants.BOARD_SIZE];
        history = new HistoryManager();
        mergeSystem = new MergeSystem();
        reset();
    }
}