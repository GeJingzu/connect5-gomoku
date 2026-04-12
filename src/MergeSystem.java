public class MergeSystem {
    //Checks if a position can trigger merge (has 3+ consecutive pieces)
    //@param board Current game board
    //@param row Row to check
    //@param col Column to check
    //@param player Player to check for (BLACK or WHITE)
    //@return true if merge can be triggered
    public boolean canMerge(int[][] board, int row, int col, int player) {
        if (board[row][col] != player) {
            return false;
        }
        return false;
    }
}


