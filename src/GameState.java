//Main game controller
//Connects board,history,and merge system
public class GameState {
    private Gomokulogic logic;
    private int currentPlayer;
    private boolean gameOver;
    private int winner;
    private HistoryManager history;
    private MergeSystem mergeSystem;

    public int gameMode = 0;  // 0: Classic, 1: Merging Mode

    public GameState(Gomokulogic logic) {
        this.logic = logic;
        history = new HistoryManager();
        mergeSystem = new MergeSystem();
    }
    int[][] board = logic.getBoard();

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
            switchPlayer();
        }
        return true;
    }

    public boolean placePiece(int row, int col) {
        int[][] board = logic.getBoard();

        if (!logic.placePiece(row, col)) return false;

        // merge（额外逻辑）
        if (mergeSystem.canMerge(board, row, col, logic.getCurrentPlayer())) {
            mergeSystem.executeMerge(board, row, col, logic.getCurrentPlayer());
        }

        return true;
    }

    public boolean tryMerge(int row, int col) {
        if (gameOver || board[row][col] != currentPlayer) return false;
        if (!mergeSystem.canMerge(board, row, col, currentPlayer)) return false;

        history.recordMove(row, col, currentPlayer, board);
        mergeSystem.executeMerge(board, row, col, currentPlayer);

        if (checkWin(row, col)) {
            gameOver = true;
            winner = currentPlayer;
        } else {
            switchPlayer();
        }
        return true;
    }


    public boolean placePieceWithMode(int row, int col, int color) {
        if (gameOver) return false;
        if (color != currentPlayer) return false;

        if (gameMode == 0) {
            return placePiece(row, col, color);
        }

        else {

            if (board[row][col] == currentPlayer) {
                return tryMerge(row, col);
            }

            else if (board[row][col] == Constants.EMPTY) {
                return placePiece(row, col, color);
            }
            return false;
        }
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

        for (int i = 0; i < Constants.BOARD_SIZE; i++) {
            board[i] = step.boardSnapshot[i].clone();
        }

        switchPlayer();
        return true;
    }

    public void loadEndgame(EndgameLoader.Level level) {
        reset();
        for (int i = 0; i < Constants.BOARD_SIZE; i++) {
            board[i] = level.board[i].clone();
        }
        currentPlayer = level.firstPlayer;
    }

    public boolean canMergeAt(int row, int col) {
        return board[row][col] == currentPlayer &&
                mergeSystem.canMerge(board, row, col, currentPlayer);
    }

    private boolean isValid(int row, int col) {
        return row >= 0 && row < Constants.BOARD_SIZE &&
                col >= 0 && col < Constants.BOARD_SIZE &&
                board[row][col] == Constants.EMPTY;
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == Constants.BLACK) ?
                Constants.WHITE : Constants.BLACK;
    }

    private boolean checkWin(int row, int col) {
        int p = board[row][col];
        if (p == Constants.EMPTY) return false;

        int[][] dirs = {{0,1}, {1,0}, {1,1}, {1,-1}};
        for (int[] d : dirs) {
            int count = 1;
            int dr = d[0], dc = d[1];

            int r = row + dr, c = col + dc;
            while (inBounds(r,c) && board[r][c] == p) { count++; r+=dr; c+=dc; }

            r = row - dr; c = col - dc;
            while (inBounds(r,c) && board[r][c] == p) { count++; r-=dr; c-=dc; }

            if (count >= 5) return true;
        }
        return false;
    }

    private boolean inBounds(int r, int c) {
        return r >= 0 && r < Constants.BOARD_SIZE && c >= 0 && c < Constants.BOARD_SIZE;
    }

    public int[][] getBoard() {
        int[][] copy = new int[board.length][];
        for (int i = 0; i < board.length; i++) copy[i] = board[i].clone();
        return copy;
    }

    public int getCurrentPlayer() { return currentPlayer; }
    public boolean isGameOver() { return gameOver; }
    public int getWinner() { return winner; }
    public boolean canUndo() { return history.canUndo(); }
    public boolean canRedo() { return history.canRedo(); }
    public int getStepCount() { return history.getStepCount(); }

    public int getPiece(int row, int col) {
        if (row >= 0 && row < Constants.BOARD_SIZE && col >= 0 && col < Constants.BOARD_SIZE) {
            return board[row][col];
        }
        return -1;
    }

    public boolean isFull() {
        for (int i = 0; i < Constants.BOARD_SIZE; i++) {
            for (int j = 0; j < Constants.BOARD_SIZE; j++) {
                if (board[i][j] == Constants.EMPTY) return false;
            }
        }
        return true;
    }

    public int[] easyAIMove(int aiColor) {
        if (aiColor != currentPlayer) return null;
        java.util.Random rand = new java.util.Random();
        int r, c;
        if (isFull()) return null;
        while (true) {
            r = rand.nextInt(Constants.BOARD_SIZE);
            c = rand.nextInt(Constants.BOARD_SIZE);
            if (board[r][c] == Constants.EMPTY) {
                placePiece(r, c, aiColor);
                return new int[]{r, c};
            }
        }
    }
}