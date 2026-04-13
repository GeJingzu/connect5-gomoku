import java.util.ArrayList;
import java.util.List;

public class MergeSystem {

    public boolean canMerge(int[][] board, int row, int col, int player) {
        if (board[row][col] != player) {
            return false;
        }

        int[][] directions = {{0, 1}, {1, 0}, {1, 1}, {1, -1}};

        for (int[] dir : directions) {
            int count = 1;
            int dr = dir[0];
            int dc = dir[1];

            int r = row + dr;
            int c = col + dc;
            while (inBounds(r, c, board.length) && board[r][c] == player) {
                count++;
                r += dr;
                c += dc;
            }

            r = row - dr;
            c = col - dc;
            while (inBounds(r, c, board.length) && board[r][c] == player) {
                count++;
                r -= dr;
                c -= dc;
            }

            if (count >= Constants.MERGE_THRESHOLD) {
                return true;
            }
        }
        return false;
    }

    public List<int[]> executeMerge(int[][] board, int row, int col, int player) {
        List<int[]> captured = new ArrayList<>();
        int enemy = (player == Constants.BLACK) ? Constants.WHITE : Constants.BLACK;

        for (int dr = -Constants.MERGE_RANGE; dr <= Constants.MERGE_RANGE; dr++) {
            for (int dc = -Constants.MERGE_RANGE; dc <= Constants.MERGE_RANGE; dc++) {
                int newRow = row + dr;
                int newCol = col + dc;
                if (inBounds(newRow, newCol, board.length) && board[newRow][newCol] == enemy) {
                    board[newRow][newCol] = player;
                    captured.add(new int[]{newRow, newCol});
                }
            }
        }
        return captured;
    }

    private boolean inBounds(int r, int c, int size) {
        return r >= 0 && r < size && c >= 0 && c < size;
    }
}