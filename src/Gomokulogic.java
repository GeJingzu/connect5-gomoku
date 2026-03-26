public class Gomokulogic {
    //Define the size of the chessboard
    private int size = 15;
    private int [][] board;
    //Consteuctor
    public  Gomokulogic(){
        board = new int[size][size];
    }
    public void printBoard(){
        for (int i=0; i < size; i++){
            for (int j=0; j < size; j++){
                System.out.print(board[i][j]+"");
            }
            System.out.println();
        }
    }
}
