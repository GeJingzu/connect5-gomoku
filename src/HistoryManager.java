import java.util.Stack;

public class HistoryManager {

    public static class Step {
        public final int row,col,player;
        public final int[][] boardBefore;

        public Step(int r,int c,int p,int[][] board){
            row=r; col=c; player=p;
            boardBefore = new int[board.length][];
            for(int i=0;i<board.length;i++){
                boardBefore[i]=board[i].clone();
            }
        }
    }

    private Stack<Step> undo = new Stack<>();
    private Stack<Step> redo = new Stack<>();

    public void recordMove(int r,int c,int p,int[][] board){
        redo.clear();
        undo.push(new Step(r,c,p,board));
    }

    public int[][] undo(){
        if(undo.isEmpty()) return null;
        Step s=undo.pop();
        redo.push(s);
        return s.boardBefore;
    }

    public Step redo(){
        if(redo.isEmpty()) return null;
        Step s=redo.pop();
        undo.push(s);
        return s;
    }
}
