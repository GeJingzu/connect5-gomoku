
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

        //Draw a checkerboard grid
        int cellSize = 380 / 18;
        for (int i = 0; i < 19; i++) {
            int y = 118 + i * cellSize;
            g.drawLine(170, y, 550, y);
        }
        for (int i = 0; i < 19; i++) {
            int x = 170 + i * cellSize;
            g.drawLine(x, 118, x, 498);
        }

        // Draw star position
        g.fillOval(483,179,4,4);//Filled circle (for drawing chess pieces)
        g.fillOval(231,179,4,4);//Filled circle (for drawing chess pieces)
        g.fillOval(231,431,4,4);//Filled circle (for drawing chess pieces)
        g.fillOval(483,431,4,4);//Filled circle (for drawing chess pieces)
        g.fillOval(358,305,4,4);//Filled circle (for drawing chess pieces)
        g.fillOval(358,179,4,4);//Filled circle (for drawing chess pieces)
        g.fillOval(358,431,4,4);//Filled circle (for drawing chess pieces)
        g.fillOval(231,305,4,4);//Filled circle (for drawing chess pieces)
        g.fillOval(483,305,4,4);//Filled circle (for drawing chess pieces)

        //Draw chess pieces
        for(int r =0;r < SIZE;r++){
            for(int c =0;c < SIZE;c++){
                if(board[r][c] != 0){
                    int x = 170 + c * cellSize;
                    int y = 118 + r * cellSize;
                    if (board[r][c] == 1) g.setColor(Color.BLACK);
                    else g.setColor(Color.WHITE);
                    g.fillOval(x - 10, y - 10, 20, 20);
                    g.setColor(Color.BLACK);
                    g.drawOval(x - 10, y - 10, 20, 20);

                }
            }
        }
        //Draw title and time bar labels
        g.setColor(Color.RED);
        g.setFont(new Font("Microsoft YaHei", Font.BOLD, 12));
        g.drawString("Five chess game", 20, 55);
        g.drawString("Help", 140, 55);
        g.drawString("START", 20, 130);
        g.drawString("RESTART THE GAME", 20, 205);

        g.setColor(Color.BLUE);
        g.setFont(new Font("Microsoft YaHei", Font.BOLD, 12));
        g.drawString("END THE GAME", 19, 508);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Microsoft YaHei", Font.BOLD, 12));
        g.drawString("REPENTANCE:", 70, 320);
        g.drawString("ADMIT DEFEAT:", 70, 280);
        g.setColor(Color.BLUE);
        g.drawOval(60,295,100,40);
        g.drawOval(60,252,100,40);
        g.setFont(new Font("Microsoft YaHei", Font.BOLD | Font.ITALIC, 12));
        g.setColor(Color.GRAY);
        g.drawString("WHITE TIME:", 220, 90);
        g.drawString("BLACK TIME:", 420, 90);



    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }
    @Override
    public void mousePressed(MouseEvent e) {

    }
    @Override
    public void mouseReleased(MouseEvent e) {

    }
    @Override
    public void mouseEntered(MouseEvent e) {

    }
    @Override
    public void mouseExited(MouseEvent e) {

    }




}
