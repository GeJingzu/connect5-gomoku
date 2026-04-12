
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
    if (logic.checkWin(row, col, color)) {
        canPlay = false;
        String winner = (color == 1) ? "Black" : "White";
        int result = JOptionPane.showConfirmDialog(this, winner + "side wins!\nStart over?", "Game Over", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            clearBoard();
            startNewGame();
        }
    }else if (logic.isFull()) {
        canPlay = false;
        int result = JOptionPane.showConfirmDialog(this,
                "Draw! Board is full.\nStart over?",
                "Game Over",
                JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            clearBoard();
            startNewGame();
        }
    }
    }
    /**
     * Draw chessboard buttons, text, chessboard structure.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); //Background security refresh
        g.setColor(Color.BLACK);

        //Draw a rectangular box
        g.drawRect(170, 118, 380, 380); //Make a rectangle
        g.drawRect(19, 36, 100, 26); //Make a rectangle
        g.drawRect(140, 36, 40, 26); //Make a rectangle
        g.drawRect(217, 70, 200, 28); //Make a rectangle
        g.drawRect(418, 70, 200, 28); //Make a rectangle
        g.drawRect(19, 110, 85, 26); //Make a rectangle
        g.drawRect(15, 487, 95, 26); //Make a rectangle
        g.drawRect(18, 185, 120, 26); //Make a rectangle
        g.drawRect(206, 36, 80, 26); //Make a rectangle
        g.drawRect(455, 4, 163, 64); //Make a rectangle
        g.drawRect(466, 23, 65, 28); //Make a rectangle
        g.drawRect(547, 23, 65, 28); //Make a rectangle

        //Draw a checkerboard grid
        int cellSize = 380 / (SIZE-1);
        for (int i = 0; i <SIZE; i++){
            int y = 118 + i * cellSize;
            g.drawLine(170, y, 550, y);
        }
        for (int i = 0; i < SIZE; i++) {
            int x = 170 + i * cellSize;
            g.drawLine(x, 118, x, 498);
        }

        //Draw star position
        int[] starPoints = {3,7,11};

        for (int r : starPoints) {
            for (int c : starPoints) {
                int x = 170 + c * cellSize;
                int y = 118 + r * cellSize;
                g.fillOval(x - 2, y - 2, 4, 4);
            }
        }

        //Draw pieces using logic.getBoard()
        int[][] board = logic.getBoard();
        for (int r = 0; r < SIZE; r++){
            for (int c = 0; c < SIZE; c++) {
                int piece = board[r][c];
                if (piece != 0) {
                    int x = 170 + c * cellSize;
                    int y = 118 + r * cellSize;

                    //Judge the type of chess pieces
                    if (piece == 1 || piece == 11) {
                        //Black chess (including the merged black chess)
                        g.setColor(Color.BLACK);
                        g.fillOval(x - 10, y - 10, 20, 20);
                        g.setColor(Color.BLACK);
                        g.drawOval(x - 10, y - 10, 20, 20);

                        //If the pieces are merged, draw ★ mark
                        if (piece == 11) {
                            g.setColor(Color.RED);
                            g.setFont(new Font("Microsoft YaHei", Font.BOLD, 12));
                            g.drawString("★", x - 4, y + 5);
                        }
                    }
                    else if (piece == 2 || piece == 12) {
                        //White (including the merged white)
                        g.setColor(Color.WHITE);
                        g.fillOval(x - 10, y - 10, 20, 20);
                        g.setColor(Color.BLACK);
                        g.drawOval(x - 10, y - 10, 20, 20);

                        //If the pieces are merged, draw ★ mark
                        if (piece == 12) {
                            g.setColor(Color.RED);
                            g.setFont(new Font("Microsoft YaHei", Font.BOLD, 12));
                            g.drawString("★", x - 4, y + 5);
                        }
                    }
                }
            }
        }
        //Draw title and time bar labels
        g.setColor(Color.RED);
        g.setFont(new Font("Microsoft YaHei", Font.BOLD, 12));
        g.drawString("Five chess game", 20, 55);
        g.drawString("Help", 145, 55);
        g.drawString("AI MODE",220,55);
        g.drawString("START", 42, 130);
        g.drawString("RESTART THE GAME", 20, 205);

        g.setColor(Color.RED);
        g.setFont(new Font("Microsoft YaHei", Font.BOLD, 9));
        g.drawString("CLASSIC MODE", 465, 40);
        g.drawString("MERGE", 562, 40);

        g.setColor(Color.GREEN);
        g.setFont(new Font("Microsoft YaHei", Font.BOLD, 13));

    }
    }
    }
