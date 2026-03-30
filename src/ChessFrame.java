
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ChessFrame extends JFrame {
    private BoardPanel boardPanel;
    private boolean canPlay = false; //Control whether falling is allowed.

    public ChessFrame() {
        this.setTitle("Five Chess Game"); //set form title
        this.setSize(650, 600); //set form size
        this.setResizable(false); //The parameter passed in false means that window maximization fails.
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Closin the window code will also end.
        this.setLocationRelativeTo(null); //Center form display.
        boardPanel = new BoardPanel();
        add(boardPanel);
        this.setVisible(true); //Set whether the form is displayed

    }

    public static void main(String[] args) {
        new ChessFrame();
    }
}
//A panel class responsible for drawing checkerboards and handling mouse events.
class BoardPanel extends JPanel implements MouseListener{
    private static final int SIZE = 19; //Chessboard 19*19
    private final int[] [] board = new int[SIZE][SIZE]; //0=empty,1=black,2=white.
    private boolean isBlackTurn = true; //Current black side
    private int cellSize = 380/18; //Size of each grid
    private boolean canPlay = false;
    public BoardPanel() {
        setBackground(Color.WHITE);
        this.addMouseListener(this);
    }

    //Clear the chessboard and refresh the interface
    public void clearBorad(){
        for(int i =0; i<SIZE; i++){
            for(int j= 0; j< SIZE; j++){
                board[i][j] = 0;
            }
        }
        isBlackTurn = true; //Let the black side take the lead again.
        repaint();
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); //Background security refresh
        g.setColor(Color.BLACK);

        //Draw a rectangular box
        g.drawRect(170,118,380,380); //Make a rectangle
        g.drawRect(19,36,100,26); //Make a rectangle
        g.drawRect(140,36,40,26); //Make a rectangle
        g.drawRect(217,70,200,28); //Make a rectangle
        g.drawRect(418,70,200,28); //Make a rectangle
        g.drawRect(19,110,85,26); //Make a rectangle
        g.drawRect(15,487,95,26); //Make a rectangle
        g.drawRect(18,185,120,26); //Make a rectangle

    }



}
