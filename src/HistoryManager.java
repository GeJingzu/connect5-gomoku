import java.util.ArrayList;
import java.util.List;

public class HistoryManager {

    public static class Step {
        public final int row, col, player;
        public final int[][] boardSnapshot;

        public Step(int row, int col, int player, int[][] board) {
            this.row = row;
            this.col = col;
            this.player = player;
            // deep copy - 深拷贝棋盘
            this.boardSnapshot = new int[board.length][];
            for (int i = 0; i < board.length; i++) {
                this.boardSnapshot[i] = board[i].clone();
            }
        }
    }

    private List<Step> undoStack = new ArrayList<>();
    private List<Step> redoStack = new ArrayList<>();

    // 记录一步棋
    public void recordMove(int row, int col, int player, int[][] board) {
        undoStack.add(new Step(row, col, player, board));
        redoStack.clear();  // 新的一步会清空 redo 栈
    }

    // 是否可以悔棋
    public boolean canUndo() {
        return !undoStack.isEmpty();
    }

    // 是否可以重做
    public boolean canRedo() {
        return !redoStack.isEmpty();
    }

    // 悔棋：返回上一步的棋盘状态
    public int[][] undo() {
        if (!canUndo()) return null;
        Step last = undoStack.remove(undoStack.size() - 1);
        redoStack.add(last);
        return last.boardSnapshot;
    }

    // 重做：返回 Step 对象（包含位置和玩家信息）
    public Step redo() {
        if (!canRedo()) return null;
        Step next = redoStack.remove(redoStack.size() - 1);
        undoStack.add(next);
        return next;
    }

    // 清空历史
    public void clear() {
        undoStack.clear();
        redoStack.clear();
    }

    // 获取步数
    public int getStepCount() {
        return undoStack.size();
    }
}
