//History manager for undo/redo functionality
// Uses two stacks to track game moves
public class HistoryManager {
    //Represent a single game step/move
    public static class Step{
        public final int row;

        //Column position
        public final int col;

        //Player who made this move (BLACK or WHITE)
        public final int player;

        // Board state BEFORE this move was made
        public final int[][] boardBefore;

        //Creates a new step record
         // @param row Row where piece was placed
         //@param col Column where piece was placed
         //@param player Player who made the move
         //@param board Current board state (will be deep copied)

        public Step(int row, int col, int player, int[][] board) {
            this.row = row;
            this.col = col;
            this.player = player;
            // Deep copy the board to preserve state
            this.boardBefore = new int[board.length][];
            for (int i = 0; i < board.length; i++) {
                this.boardBefore[i] = board[i].clone();
            }
        }
    }
    //Stack for undo operations (stores completed moves)
    private Stack<Step> undoStack;

    //Stack for redo operations (stores undone moves)
    private Stack<Step> redoStack;

    //Creates a new history manager
    public HistoryManager() {
        undoStack = new Stack<>();
        redoStack = new Stack<>();
    }
    //Records a new move
     //Clears redo stack when new move is made
     //@param row Row of move
     //@param col Column of move
     //@param player Player making move
     //@param currentBoard Board state before move
    public void recordMove(int row, int col, int player, int[][] currentBoard) {
        // New move clears redo history
        redoStack.clear();
        // Save current state before the move
        undoStack.push(new Step(row, col, player, currentBoard));
    }

    //Checks if undo is possible
    public boolean canUndo() {
        return !undoStack.isEmpty();
    }

    // Checks if redo is possible
    public boolean canRedo() {
        return !redoStack.isEmpty();
    }

    //Undoes the last move
    public int[][] undo() {
        if (!canUndo()) {
            return null;
        }

        Step step = undoStack.pop();
        redoStack.push(step);

        return step.boardBefore;
    }

    //Redoes a previously undone move
    public Step redo() {
        if (!canRedo()) {
            return null;
        }
        Step step = redoStack.pop();
        undoStack.push(step);

        return step;
    }
}
