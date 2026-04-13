//Main game controller
//Connects board,history,and merge system
public class GameState {

    private Gomokulogic logic;

    public GameState() {
        logic = new Gomokulogic();
    }

    // 下棋
    public boolean placePiece(int row, int col) {
        // 直接用你们的算法类
        int player = logic.getCurrentPlayer();
        return logic.placePiece(row, col, player);
    }

    // 获取棋盘（给UI用）
    public int[][] getBoard() {
        int size = 15;
        int[][] board = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = logic.getPiece(i, j);
            }
        }
        return board;
    }

    // 当前玩家（UI显示用）
    public int getCurrentPlayer() {
        return logic.getCurrentPlayer();
    }

    // ===== 下面这几个是为了不报错（兼容原代码）=====

    public boolean undo() {
        return false; // 你们没做就先这样
    }

    public boolean redo() {
        return false;
    }

    public boolean canUndo() {
        return false;
    }

    public boolean canRedo() {
        return false;
    }

    public int getStepCount() {
        return 0;
    }
}