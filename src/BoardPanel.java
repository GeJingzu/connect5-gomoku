
/**
 * NAME:Yunlong Wang
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

//A panel class responsible for drawing checkerboards and handling mouse events.
public class BoardPanel extends JPanel implements MouseListener {
    private static final int SIZE = 15; //Chessboard 15*15.
    private Gomokulogic logic; //Use logic class instead of local board array.
    private int cellSize = 380/(SIZE-1); //Size of each grid.
    private boolean canPlay = false;

    //Timer display placeholders.
    private String blackTimeText = "--:--";
    private String whiteTimeText = "--:--";

    private int startX = 130;
    private int startY = 100;

public BoardPanel() {
    setBackground(Color.WHITE);
    this.addMouseListener(this);
    logic = new Gomokulogic(); // Initialize game logic.
}

//Start a new game (without timer)
    private void startNewGame(){
        logic.reset(); //Reset game logic
        blackTimeText = "--:--";
        whiteTimeText = "--:--";
        canPlay = true;
        repaint();

    }

    //Clear the chessboard and refresh the interface
    public void clearBoard(){
        logic.reset(); //Use logic's reset method;
        repaint();
    }

    private void checkWinOrDraw(int row, int col, int color){
    if (logic.checkWin(row, col, color)){
        canPlay = false;
        String winner = (color == 1) ? "Black" : "White";
        int result = JOptionPane.showConfirmDialog(this,winner + "side wins!\nStart over?","Game Over",JOptionPane.YES_NO_OPTION);

    }
    }
}
