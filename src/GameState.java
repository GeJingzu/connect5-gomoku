//Main game controller
//Connects board,history,and merge system
public class GameState {

    private Gomokulogic logic = new Gomokulogic();

    public boolean placePiece(int r,int c){
        return logic.placePiece(r,c);
    }

    public boolean undo(){
        return logic.undo();
    }

    public boolean redo(){
        return logic.redo();
    }

    public int[][] getBoard(){
        return logic.getBoard();
    }

    public int getCurrentPlayer(){
        return logic.getCurrentPlayer();
    }
}