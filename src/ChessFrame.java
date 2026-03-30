
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
    public void clearBoard(){
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
        int mouseX = e.getX();
        int mouseY = e.getY();
        System.out.println(mouseX + " " + mouseY);
        //'START THE GAME' button
        if(e.getX()>=19 && e.getX()<= 104 && e.getY()>=111 && e.getY()<=135){
            JOptionPane.showMessageDialog(this,"Then the game starts now!");
            if (e.getX() >= 19 && e.getX() <= 104 && e.getY() >= 111 && e.getY() <= 135) {
                clearBoard();
                canPlay = true; // Start the game to play chess
            }
        }
        //'REPENTANCE' button
        if (e.getX()>=63 && e.getX()<= 154 && e.getY()>=298 && e.getY()<=324) {
            JOptionPane.showMessageDialog(this,"Sorry, this feature is not actually installed");
        }
        //'HELP' button
        if(e.getX()>=140 && e.getX()<= 180 && e.getY()>=35 && e.getY()<=61){
            JOptionPane.showMessageDialog(this,"The core goal of Gobang is to put five pieces of the same color in the horizontal, vertical or diagonal direction. It seems simple, but the layout and rhythm are very important in the high-level game. The following are systematic strategies and techniques, which are divided into two levels: tactics and Strategies");
        }
        //'END THE GAME'BUTTON
        if(e.getX()>=15 && e.getX()<= 101 && e.getY()>=488 && e.getY()<=512) {
            JOptionPane.showMessageDialog(this, "Goodbye~");
            System.exit(0);
        }
        //'RESTART THE GAME' button
        if(e.getX()>=16 && e.getX()<= 139 && e.getY()>=184 && e.getY()<=209){
            JOptionPane.showMessageDialog(this,"Then the chessboard will be cleared");
            clearBoard();
        }
        //'ADMIT DEFEAT' button
        if(e.getX()>=60 && e.getX()<= 155 && e.getY()>=258 && e.getY()<=287){
            int result2 = JOptionPane.showConfirmDialog(this,"Are you sure you want to admit defeat?");
            System.out.println("result2="+result2);
            if(result2==0){
                JOptionPane.showMessageDialog(this,"Okay, so the game is banned");
                clearBoard();
                canPlay = false;
            }else if(result2==1){
                JOptionPane.showMessageDialog(this,"I see, so keep playing.");
                canPlay = true;
            }else{
                JOptionPane.showMessageDialog(this,"I see, so keep playing.");
            }

        }


        if (mouseX < 170 || mouseX > 550 || mouseY < 118 || mouseY > 498) {
            System.out.println("Click outside the chessboard, invalid");
            return;
        }
        if (!canPlay) {
            System.out.println("The game has not started or has ended. You can't leave it alone!");
            JOptionPane.showMessageDialog(this, "The game has not started or has ended!");
            return;
        }
        int col = Math.round((mouseX - 170) / (float) cellSize);
        int row = Math.round((mouseY - 118) / (float) cellSize);

        // Can only drop in the empty position
        if (board[row][col] != 0) {
            System.out.println("There are already chess pieces here!");
            return;
        }
        board[row][col] = isBlackTurn ? 1 : 2;
        isBlackTurn = !isBlackTurn; // Switch player
        repaint();

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
