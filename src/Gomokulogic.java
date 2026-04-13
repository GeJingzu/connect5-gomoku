//Jingzu Ge

import java.util.Stack;
import java.util.Random;

public class Gomokulogic {
    private int size = Constants.BOARD_SIZE;
    private int[][] board;
    private int currentPlayer = Constants.BLACK;
    private MergeSystem mergeSystem = new MergeSystem();
    private HistoryManager history = new HistoryManager();

    public Gomokulogic() {
        board = new int[size][size];
    }

    public int[][] getBoard() {
        return board;
    }
    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean placePiece(int r, int c) {

        if (r<0 || r>=size || c<0 || c>=size || board[r][c]!=0) {
            return false;
        }

        history.recordMove(r,c,currentPlayer,board);

        board[r][c] = currentPlayer;

        // merge
        if (mergeSystem.canMerge(board,r,c,currentPlayer)) {
            mergeSystem.executeMerge(board,r,c,currentPlayer);
        }

        // win
        if (checkWin(r,c,currentPlayer)) {
            System.out.println("Player "+currentPlayer+" wins!");
        } else {
            currentPlayer = (currentPlayer==1)?2:1;
        }

        return true;
    }

    public boolean undo(){
        int[][] prev = history.undo();
        if(prev==null) return false;

        for(int i=0;i<size;i++) board[i]=prev[i].clone();

        currentPlayer = (currentPlayer==1)?2:1;
        return true;
    }

    public boolean redo(){
        HistoryManager.Step s = history.redo();
        if(s==null) return false;

        board[s.row][s.col]=s.player;
        currentPlayer = (currentPlayer==1)?2:1;
        return true;
    }

    public boolean checkWin(int r,int c,int color){
        int count;
        // transverse
        count=1;
        for(int i=c+1;i<size && board[r][i]==color;i++) count++;
        for(int i=c-1;i>=0 && board[r][i]==color;i--) count++;
        if(count>=5) return true;

        // vertical
        count=1;
        for(int i=r+1;i<size && board[i][c]==color;i++) count++;
        for(int i=r-1;i>=0 && board[i][c]==color;i--) count++;
        if(count>=5) return true;

        // Oblique\
        count=1;
        for(int i=1;r+i<size&&c+i<size&&board[r+i][c+i]==color;i++) count++;
        for(int i=1;r-i>=0&&c-i>=0&&board[r-i][c-i]==color;i++) count++;
        if(count>=5) return true;

        // Oblique/
        count=1;
        for(int i=1;r+i<size&&c-i>=0&&board[r+i][c-i]==color;i++) count++;
        for(int i=1;r-i>=0&&c+i<size&&board[r-i][c+i]==color;i++) count++;
        return count>=5;
    }

    }






