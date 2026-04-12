//Main game controller
//Connects board,history,and merge system
public class GameState {
    private int[][] board;
    private int currentPlayer;
    private boolean gameOver;
    private int winner;

    private HistoryManager history;
    private MergeSystem merge;  // Field - created in constructor

    public GameState() {
        board = new int[Constants.BOARD_SIZE][Constants.BOARD_SIZE];
        history = new HistoryManager();
        merge = new MergeSystem();  // Initialize here
        reset();
    }

    // Start fresh game
    public void reset() {
        for (int i = 0; i < Constants.BOARD_SIZE; i++) {
            for (int j = 0; j < Constants.BOARD_SIZE; j++) {
                board[i][j] = Constants.EMPTY;
            }
        }
        currentPlayer = Constants.BLACK;
        gameOver = false;
        winner = Constants.EMPTY;
        history.clear();
    }

    // Place a piece normally
    public boolean placePiece(int row, int col) {
        if (gameOver || !isValid(row, col)) return false;

        history.recordMove(row, col, currentPlayer, board);
        board[row][col] = currentPlayer;

        if (checkWin(row, col)) {
            gameOver = true;
            winner = currentPlayer;
        } else {
            switchPlayer();
        }
        return true;
    }

    private void switchPlayer() {
    }

    private boolean checkWin(int row, int col) {
        return false;
    }

    // Try to merge at position
    public boolean tryMerge(int row, int col) {
        if (gameOver || board[row][col] != currentPlayer) return false;
        if (!merge.canMerge(board, row, col, currentPlayer)) return false;

        history.recordMove(row, col, currentPlayer, board);
        merge.executeMerge(board, row, col, currentPlayer);

        if (checkWin(row, col)) {
            gameOver = true;
            winner = currentPlayer;
        } else {
            switchPlayer();
        }
        return true;
    }

    public boolean undo() {
        if (!history.canUndo()) return false;
        int[][] prev = history.undo();
        if (prev == null) return false;

        for (int i = 0; i < Constants.BOARD_SIZE; i++) {
            board[i] = prev[i].clone();
        }
        gameOver = false;
        winner = Constants.EMPTY;
        switchPlayer();
        return true;
    }

    public boolean redo() {
        if (!history.canRedo()) return false;
        HistoryManager.Step step = history.redo();
        if (step == null) return false;

        board[step.row][step.col] = step.player;
        switchPlayer();
        return true;
    }

    // Load a puzzle level
    public void loadEndgame(EndgameLoader.Level level) {
        reset();
        for (int i = 0; i < Constants.BOARD_SIZE; i++) {
            board[i] = level.board[i].clone();
        }
        currentPlayer = level.firstPlayer;
    }

    // Check if merge is possible at this position
    public boolean canMergeAt(int row, int col) {
        // Use 'merge' field, don't create new variable
        return board[row][col] == currentPlayer &&
                merge.canMerge(board, row, col, currentPlayer);
    }

    private boolean isValid(int row, int col) {
        return row >= 0 && row < Constants.BOARD_SIZE &&
                col >= 0 && col < Constants.BOARD_SIZE &&
                board[row][col] == Constants.EMPTY;
    }
}
