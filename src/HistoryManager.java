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
            // deep copy
            this.boardSnapshot = new int[board.length][];
            for (int i = 0; i < board.length; i++) {
                this.boardSnapshot[i] = board[i].clone();
            }
        }
    }

    private List<Step> undoStack = new ArrayList<>();
    private List<Step> redoStack = new ArrayList<>();

    public void recordMove(int row, int col, int player, int[][] board) {
        undoStack.add(new Step(row, col, player, board));
        redoStack.clear();
    }

    public boolean canUndo() {
        return !undoStack.isEmpty();
    }

    public boolean canRedo() {
        return !redoStack.isEmpty();
    }

    public int[][] undo() {
        if (!canUndo()) return null;
        Step last = undoStack.remove(undoStack.size() - 1);
        redoStack.add(last);
        return last.boardSnapshot;
    }

    public Step redo() {
        if (!canRedo()) return null;
        Step next = redoStack.remove(redoStack.size() - 1);
        undoStack.add(next);
        return next;
    }

    public void clear() {
        undoStack.clear();
        redoStack.clear();
    }

    public int getStepCount() {
        return undoStack.size();
    }
}
