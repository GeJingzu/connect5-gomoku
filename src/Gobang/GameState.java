package Gobang;
//Yutian Wang

public class GameState {
    private Gomokulogic logic;  // Holding a reference to Gomokulogic
    //Constructor: Pass in Gomokulogic instance
    public GameState(Gomokulogic logic) {
        this.logic = logic;
    }
    private boolean canPlay = false;
public void startGame(){
    canPlay = true;
}
public void stopGame(){
    canPlay = false;
}
public boolean isPlaying(){
    return canPlay;
}

    public int[][] getBoard() {
        return logic.getBoard();
    }

    public int getCurrentTurn() {
        return logic.getCurrentTurn();
    }

    public boolean placePiece(int row, int col, int color) {
        return logic.placePiece(row, col, color);
    }

    public boolean undo() {
        return logic.undo();
    }

    public boolean checkWin(int row, int col, int color) {
        return logic.checkWin(row, col, color);
    }

    public boolean isFull() {
        return logic.isFull();
    }

    public int getPiece(int row, int col) {
        return logic.getPiece(row, col);
    }

    public void reset() {
        logic.reset();
    }

    public int[] easyAIMove(int aiColor) {
        return logic.easyAIMove(aiColor);
    }

    public int getGameMode() {
        return logic.gameMode;
    }

    public void setGameMode(int mode) {
        logic.gameMode = mode;
    }

}
