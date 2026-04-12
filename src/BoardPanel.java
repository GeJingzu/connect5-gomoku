
/**
 * NAME:Yunlong Wang
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

//A panel class responsible for drawing checkerboards and handling mouse events.
class BoardPanel extends JPanel implements MouseListener {
    private static final int SIZE = 15; //Chessboard 15*15.
    private Gomokulogic logic; //Use logic class instead of local board array.
    private int cellSize = 380/(SIZE-1); //Size of each grid.
    private boolean canPlay = false;

    // Timer display placeholders
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
    private void startNewGame() {
        logic.reset(); // Reset game logic
        blackTimeText = "--:--";
        whiteTimeText = "--:--";
        canPlay = true;
        repaint();
    }

    //Clear the chessboard and refresh the interface
    public void clearBoard(){
        logic.reset(); //Use logic's reset method.
        repaint();
    }

    private void checkWinOrDraw(int row, int col, int color) {
        if (logic.checkWin(row, col, color)) {
            canPlay = false;
            String winner = (color == 1) ? "Black" : "White";
            int result = JOptionPane.showConfirmDialog(this,
                    winner + " side wins!\nStart over?",
                    "Game Over",
                    JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                clearBoard();
                startNewGame();
            }
        } else if (logic.isFull()) {
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
        g.drawRect(170,118,380,380); //Make a rectangle
        g.drawRect(19,36,100,26); //Make a rectangle
        g.drawRect(140,36,40,26); //Make a rectangle
        g.drawRect(217,70,200,28); //Make a rectangle
        g.drawRect(418,70,200,28); //Make a rectangle
        g.drawRect(19,110,85,26); //Make a rectangle
        g.drawRect(15,487,95,26); //Make a rectangle
        g.drawRect(18,185,120,26); //Make a rectangle
        g.drawRect(206,36,80,26); //Make a rectangle
        g.drawRect(455,4,163,64); //Make a rectangle
        g.drawRect(466,23,65,28); //Make a rectangle
        g.drawRect(547,23,65,28); //Make a rectangle

        //Draw a checkerboard grid
        int cellSize = 380 / (SIZE-1);
        for (int i = 0; i < SIZE; i++) {
            int y = 118 + i * cellSize;
            g.drawLine(170, y, 550, y);
        }
        for (int i = 0; i < SIZE; i++) {
            int x = 170 + i * cellSize;
            g.drawLine(x, 118, x, 498);
        }

        // Draw star position
        int[] starPoints = {3, 7, 11};

        for (int r : starPoints) {
            for (int c : starPoints) {
                int x = 170 + c * cellSize;
                int y = 118 + r * cellSize;
                g.fillOval(x - 2, y - 2, 4, 4);
            }
        }

        // Draw pieces using logic.getBoard()
        int[][] board = logic.getBoard();
        for (int r = 0; r < SIZE; r++) {
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
        g.drawString("(MODE)", 516, 16);

        g.setColor(Color.BLUE);
        g.setFont(new Font("Microsoft YaHei", Font.BOLD, 12));
        g.drawString("END THE GAME", 19, 508);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Microsoft YaHei", Font.BOLD, 9));
        g.drawString("REPENTANCE(Undo):", 70, 320);
        g.drawString("ADMIT DEFEAT:", 73, 276);
        g.setColor(Color.BLUE);
        g.drawOval(60,295,100,40);
        g.drawOval(60,252,100,40);

        // Show countdown display frame
        g.setFont(new Font("Microsoft YaHei", Font.BOLD, 14));
        g.setColor(Color.BLACK);
        g.drawString("WHITE TIME:", 220, 90);
        g.drawString("BLACK TIME:", 420, 90);

        // Current round instruction
        g.setFont(new Font("Microsoft YaHei", Font.BOLD, 12));
        if (canPlay) {
            int currentTurn = logic.getCurrentTurn();
            if (currentTurn == 1) {
                g.setColor(Color.BLACK);
                g.drawString("▼ Black Turn ▼", 280, 550);
            } else {
                g.setColor(Color.GRAY);
                g.drawString("▼ White Turn ▼", 280, 550);
            }
        }

        // Display time value placeholders (The display "--: --" indicates that the timing function has not been realized yet)
        g.setFont(new Font("Monospaced", Font.BOLD, 16));
        g.setColor(Color.GRAY);
        g.drawString(blackTimeText, 524, 89);
        g.drawString(whiteTimeText, 322, 89);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();
        System.out.println(mouseX + " " + mouseY);

        //'START THE GAME' button
        if (e.getX() >= 19 && e.getX() <= 104 && e.getY() >= 111 && e.getY() <= 135) {
            JOptionPane.showMessageDialog(this, "Then the game starts now!");
            clearBoard();
            startNewGame(); // Start the game to play chess
            return;
        }

        //'REPENTANCE' button
        if (e.getX() >= 63 && e.getX() <= 154 && e.getY() >= 298 && e.getY() <= 324) {
            if (!canPlay) return;
            logic.undo(); // Call repentance algorithm
            repaint();
            return;
        }

        //'HELP' button
        if (e.getX() >= 140 && e.getX() <= 180 && e.getY() >= 35 && e.getY() <= 61) {
            JOptionPane.showMessageDialog(this, "=== Five Chess Game Help ===\n\n" +
                    "【Game Rules】\n" +
                    "• You need connect 5 pieces in a line, can be horizontal, vertical or diagonal, then you win\n" +
                    "• Black chess always go first\n" +
                    "• Every player has 5 minutes time, don't be too slow\n\n" +
                    "【Button Functions】\n" +
                    "• START - Begin a new game, let's play\n" +
                    "• RESTART - Clear all chess and start again\n" +
                    "• REPENTANCE - Take back your last step (now we have this function in Milestone 2)\n" +
                    "• ADMIT DEFEAT - If you feel cannot win, you can admit lose\n" +
                    "• END THE GAME - Close the game, goodbye\n\n" +
                    "【Countdown】\n" +
                    "• If your time finish, you will lose automatically\n" +
                    "• Timer will change to another player by itself when turn changed\n\n" +
                    "【Milestone 2 Updates】\n" +
                    "• Undo function is ready to use now!\n" +
                    "• Countdown timer is working good\n" +
                    "• Game look more beautiful than before");
            return;
        }

        //"AI MODE" BUTTON
        if (e.getX() >= 206 && e.getX() <= 285 && e.getY() >= 36 && e.getY() <= 60) {
            //Check whether the game is in progress
            if (!canPlay) {
                JOptionPane.showMessageDialog(this, "Please start the game first!");
                return;
            }

            // Get the current turn (the chess pieces that AI should play in the current turn)
            int aiColor = logic.getCurrentTurn();

            int[] aiMove = logic.easyAIMove(aiColor);

            if (aiMove != null) {
                repaint();
                checkWinOrDraw(aiMove[0], aiMove[1], aiColor);
            }
            return;
        }

        //"CLASSIC MODE" BUTTON
        if (e.getX() >= 466 && e.getX() <= 531 && e.getY() >= 24 && e.getY() <= 50) {
            logic.gameMode = 0;
            JOptionPane.showMessageDialog(this, "Switched to CLASSIC MODE");
            repaint();
            return;
        }

        //"MERGE" BUTTON
        if (e.getX() >= 547 && e.getX() <= 611 && e.getY() >= 23 && e.getY() <= 49) {
            logic.gameMode = 1;
            JOptionPane.showMessageDialog(this, "Switched to MERGE MODE\nYou can now merge pieces of same color!");
            repaint();
            return;
        }

        //'END THE GAME'BUTTON
        if (e.getX() >= 15 && e.getX() <= 101 && e.getY() >= 488 && e.getY() <= 512) {
            JOptionPane.showMessageDialog(this, "Goodbye~");
            System.exit(0);
            return;
        }

        //'RESTART THE GAME' button
        if (e.getX() >= 16 && e.getX() <= 139 && e.getY() >= 184 && e.getY() <= 209) {
            JOptionPane.showMessageDialog(this, "Then the chessboard will be cleared");
            clearBoard();
            startNewGame(); // Start the game to play chess
            return;
        }

        //'ADMIT DEFEAT' button
        if (e.getX() >= 60 && e.getX() <= 155 && e.getY() >= 258 && e.getY() <= 287) {
            int result2 = JOptionPane.showConfirmDialog(this, "Are you sure you want to admit defeat?");
            System.out.println("result2=" + result2);
            if (result2 == 0) {
                JOptionPane.showMessageDialog(this, "Okay, so the game is banned");
                clearBoard();
                canPlay = false;
            } else if (result2 == 1) {
                JOptionPane.showMessageDialog(this, "I see, so keep playing.");
                canPlay = true;
            } else {
                JOptionPane.showMessageDialog(this, "I see, so keep playing.");
            }
            return;
        }

        // Check if click is on the chessboard
        if (mouseX < 170 || mouseX > 550 || mouseY < 118 || mouseY > 498) {
            System.out.println("Click outside the chessboard, invalid");
            return;
        }

        if (!canPlay) {
            System.out.println("The game has not started or has ended. You can't drop pieces!");
            JOptionPane.showMessageDialog(this, "The game has not started or has ended!");
            return;
        }

        int col = Math.round((mouseX - 170) / (float) cellSize);
        int row = Math.round((mouseY - 118) / (float) cellSize);

        // Boundary check
        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE) {
            return;
        }

        int currentColor = logic.getCurrentTurn();
        int existingPiece = logic.getPiece(row, col);

        //Check whether it can be dropped
        boolean canPlace = false;

        if (existingPiece == 0) {
            //Empty seat, you can go down
            canPlace = true;
        } else if (logic.gameMode == 1 && existingPiece == currentColor) {
            //Merge mode: you can merge on your own chess pieces
            canPlace = true;
        } else {
            //Other conditions: not applicable
            JOptionPane.showMessageDialog(this, "Cannot place here!");
            return;
        }

        if (canPlace && logic.placePiece(row, col, currentColor)) {
            repaint();

            // Check win
            if (logic.checkWin(row, col, currentColor)) {
                canPlay = false;
                String winner = (currentColor == 1) ? "Black" : "White";
                int result = JOptionPane.showConfirmDialog(this,
                        winner + " side wins!\nStart over?",
                        "Game Over",
                        JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    clearBoard();
                    startNewGame();
                }
                return;
            }

            // Check draw
            if (logic.isFull()) {
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
    }

    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}

}
