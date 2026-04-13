import java.util.ArrayList;
import java.util.List;


 //Merge pieces system
 //Allows capturing enemy pieces when 3+ consecutive pieces exist

public class MergeSystem {

    //Checks if a position can trigger merge (has 3+ consecutive pieces)
     // @param board Current game board
     //@param row Row to check
     //@param col Column to check
     //@param player Player to check for (BLACK or WHITE)
     //@return true if merge can be triggered
    public boolean canMerge(int[][] board, int row, int col, int player) {
        if (board[row][col] != player) {
            return false;
        }

        // Four directions to check
        int[][] directions = {{0, 1}, {1, 0}, {1, 1}, {1, -1}};

        for (int[] dir : directions) {
            int count = 1;
            int dr = dir[0];
            int dc = dir[1];

            // Check positive direction
            int r = row + dr;
            int c = col + dc;
            while (inBounds(r, c, board.length) && board[r][c] == player) {
                count++;
                r += dr;
                c += dc;
            }

            // Check negative direction
            r = row - dr;
            c = col - dc;
            while (inBounds(r, c, board.length) && board[r][c] == player) {
                count++;
                r -= dr;
                c -= dc;
            }

            // Found 3+ consecutive
            if (count >= Constants.MERGE_THRESHOLD) {
                return true;
            }
        }

        return false;
    }

    // Executes merge operation, capturing enemy pieces
     // @param board Game board (will be modified)
     //@param row Row of merge center
     //@param col Column of merge center
     //@param player Player executing merge
     //@return List of captured piece positions [row, col]
    public List<int[]> executeMerge(int[][] board, int row, int col, int player) {
        List<int[]> captured = new ArrayList<>();
        int enemy = (player == Constants.BLACK) ? Constants.WHITE : Constants.BLACK;

        // Check all positions within MERGE_RANGE
        for (int dr = -Constants.MERGE_RANGE; dr <= Constants.MERGE_RANGE; dr++) {
            for (int dc = -Constants.MERGE_RANGE; dc <= Constants.MERGE_RANGE; dc++) {
                int newRow = row + dr;
                int newCol = col + dc;

                // Capture enemy pieces in range
                if (inBounds(newRow, newCol, board.length) && board[newRow][newCol] == enemy) {
                    board[newRow][newCol] = player;
                    captured.add(new int[]{newRow, newCol});
                }
            }
        }

        return captured;
    }

    //Checks if position is within board bounds
    //@param r Row
    //@param c Column
     // @param size Board size
     //@return true if in bounds
    private boolean inBounds(int r, int c, int size) {
        return r >= 0 && r < size && c >= 0 && c < size;
    }
}
