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

    public boolean placePiece(int row, int col) {
        if (gameOver || !isValid(row, col)) return false;

        history.recordMove(row, col, currentPlayer, board);
        board[row][col] = currentPlayer;

        if (checkWin(row, col)) {
            gameOver = true;
            winner = currentPlayer;
        } else {
            currentPlayer = (currentPlayer == Constants.BLACK) ?
                    Constants.WHITE : Constants.BLACK;
        }
        return true;
    }

    private boolean checkWin(int row, int col) {
        return false;
    }

    private boolean isValid(int row, int col) {
        return false;
    }
}
