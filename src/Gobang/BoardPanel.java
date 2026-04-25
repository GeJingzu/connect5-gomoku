package Gobang;
/**
 * NAME:Yunlong Wang
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//A panel class responsible for drawing checkerboards and handling mouse events.
class BoardPanel extends JPanel implements MouseListener {
    private static final int SIZE = 15; //Chessboard 15*15.
    private Gomokulogic logic; //Use logic class instead of local board array.
    private int cellSize = 380/(SIZE-1); //Size of each grid.
    private boolean canPlay = false;
    private Image[] images = new Image[3];

    // Implementation of countdown function
    private int blackTime = 300; // The remaining time of black side (this is just the beginning), the initial is 5 minutes
    private int whiteTime = 300; // The remaining time of black side (this is just the beginning), the initial is 5 minutes
    private Timer timer; //Swing Timer

    //Text showing time(Initial)
    private String blackTimeText = "05:00";
    private String whiteTimeText = "05:00";


    //Update countdown
    private void updateTimer(){
        if (!canPlay){
            return;//If the game does not start, the time will not be counted
        }
        if(logic.getCurrentTurn() ==1){  //black
            if(blackTime > 0){
                blackTime--;
                updateTimeDisplay();
                repaint();
                // When the time runs out, the black side will judge the negative in case of overtime
                if (blackTime == 0) {
                    timer.stop();
                    canPlay = false;
                    int result = JOptionPane.showConfirmDialog(this,
                            "Black timeout! White side wins!\n" +
                                    "Start over?",
                            "Overtime negative judgment",
                            JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        clearBoard();
                        startNewGame();
                    }
                }
            }
        } else {

            if (whiteTime > 0) {
                whiteTime--;
                updateTimeDisplay();
                repaint();

                if (whiteTime == 0) {
                    timer.stop();
                    canPlay = false;
                    int result = JOptionPane.showConfirmDialog(this,
                            "White timeout! Black side wins!\n" +
                                    "Start over?",
                            "Overtime negative judgment",
                            JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        clearBoard();
                        startNewGame();
                    }
                }
            }
        }
    }

    //Update time display text
    private void updateTimeDisplay(){
        blackTimeText = formatTime(blackTime);
        whiteTimeText = formatTime(whiteTime);
    }

    //Format time display
    private String formatTime(int seconds){
        int minutes = seconds / 60;
        int secs = seconds % 60;
        return String.format("%02d:%02d", minutes, secs);
    }

    private int startX = 130;
    private int startY = 100;

    private SimpleAI ai;
    private HistoryManager history;
    private MergeSystem mergeSystem;

    public BoardPanel() {

        //load picture
        try {
            images[0] = new ImageIcon(getClass().getResource("/images/Title_picture.png")).getImage();
            images[1] = new ImageIcon(getClass().getResource("/images/Clock_picture.png")).getImage();
            images[2] = new ImageIcon(getClass().getResource("/images/Help_picture.png")).getImage();
        } catch (Exception e) {
            e.printStackTrace();
        }


        setBackground(Color.WHITE);
        this.addMouseListener(this);
        logic = new Gomokulogic();
        mergeSystem = new MergeSystem(logic);
        history = new HistoryManager(logic);
        ai = new SimpleAI(logic);
        setBackground(Color.WHITE);
        timer = new Timer(1000, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                updateTimer();
            }
        });
    }

    //Start a new game (without timer)
    private void startNewGame() {
        logic.reset(); // Reset game logic
        blackTime = 300;
        whiteTime = 300;
        blackTimeText = "05:00";
        whiteTimeText = "05:00";
        canPlay = true;
        repaint();

        // Restart timer
        if (timer.isRunning()) {
            timer.stop();
        }
        timer.start();
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

    //Draw chessboard buttons, text, chessboard structure.

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); //Background security refresh
        //draw title picture

            g.drawImage(images[0], 11, 8, 185, 80, this); //set Title picture.
            g.drawImage(images[1], 381, 72, 22, 26, this); //set Clock picture.(next to 'White time').
            g.drawImage(images[1], 585, 72, 22, 26, this); //set Clock picture.(next to 'Black time').


        g.setColor(Color.BLACK);
        g.drawRect(217,70,200,28); //Make a rectangle(Whit time frame)
        g.drawRect(418,70,200,28); //Make a rectangle(Black time frame)

        //Draw a rectangular box
        g.drawRect(170,118,380,380); //Make a rectangle(Go box)
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));
        g.drawRect(15,447,40,26); //Make a rectangle(Help button)
        g.drawRect(15,487,98,26); //Make a rectangle(End the game button)
        g.drawRect(242,23,80,28); //Make a rectangle(AI MODE button)
        g.drawRect(439,23,83,28); //Make a rectangle(Classic mode button)
        g.drawRect(537,23,65,28); //Make a rectangle(Merge button)
        g.drawRect(340,23,80,28); //Make a rectangle(END GAME button)
        g.drawRect(18,185,128,28); //Make a rectangle(Restart the game button)
        g.drawRect(18,287,128,28); //Make a rectangle(Repentance button)
        g.drawRect(18,235,128,28); //Make a rectangle(Admit defeat button)
        g.drawRect(18,138,128,28); //Make a rectangle(Start button)
        Graphics2D g3 = (Graphics2D) g;
        g3.setStroke(new BasicStroke(3));
        g.setColor(Color.GRAY);
        g.drawRect(219,3,398,65); //Make a rectangle(Frame function box 1)
        g.drawRect(8,118,158,220); //Make a rectangle(Frame function box 2)
        g.drawRect(8,438,158,90); //Make a rectangle(Frame function box 3)

        g.setColor(Color.BLACK);
        Graphics2D g4 = (Graphics2D) g;
        g4.setStroke(new BasicStroke(1));

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
        g.drawString("Help", 19, 463);
        g.drawString("AI MODE",255,42);
        g.drawString("START", 58, 156);
        g.drawString("RESTART THE GAME", 20, 205);
        g.drawString("ADMIT DEFEAT:", 28, 255);
        g.drawString("REPENTANCE(Undo)", 22, 306);

        g.setColor(Color.RED);
        g.setFont(new Font("Microsoft YaHei", Font.BOLD, 10));
        g.drawString("CLASSIC MODE", 440, 40);
        g.drawString("MERGE", 552, 40);
        g.drawString("END GAME", 348, 41);

        g.setColor(Color.GREEN);
        g.setFont(new Font("Microsoft YaHei", Font.BOLD, 13));
        g.drawString("(MODE)", 400, 16);

        g.setColor(Color.BLUE);
        g.setFont(new Font("Microsoft YaHei", Font.BOLD, 12));
        g.drawString("END THE GAME", 19, 505);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Microsoft YaHei", Font.BOLD, 9));

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
        if (logic.getCurrentTurn() == 1 && canPlay) {
            g.setColor(Color.RED);
        } else {
            g.setColor(Color.GRAY);
        }
        g.drawString(blackTimeText, 524, 89);

        if (logic.getCurrentTurn() == 2 && canPlay) {
            g.setColor(Color.RED);
        } else {
            g.setColor(Color.GRAY);
        }
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
            startNewGame();
            canPlay = true; // Start the game to play chess
            return;
        }

        //'REPENTANCE' button
        if (e.getX() >= 63 && e.getX() <= 154 && e.getY() >= 298 && e.getY() <= 324) {
            if (!canPlay) return;
            history.undo(); // Call repentance algorithm
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

            int[] aiMove = ai.makeMove(aiColor);

            if (aiMove != null) {
                repaint();
                checkWinOrDraw(aiMove[0], aiMove[1], aiColor);
            }
            return;
        }

        //"CLASSIC MODE" BUTTON
        if (e.getX() >= 466 && e.getX() <= 531 && e.getY() >= 24 && e.getY() <= 50) {
            clearBoard();
            mergeSystem.setClassic(); //We can also use "logic.gameMode = 0;" this Statement. Because I added many branches on the basis of Yutian Wang. Because we do it separately, there are more repetitions.
            JOptionPane.showMessageDialog(this, "Switched to CLASSIC MODE");
            repaint();
            return;
        }

        //"MERGE" BUTTON
        if (e.getX() >= 547 && e.getX() <= 611 && e.getY() >= 23 && e.getY() <= 49) {
            clearBoard();
            mergeSystem.setMerge(); //We can also use "logic.gameMode = 1;" this Statement. Because I added many branches on the basis of Yutian Wang. Because we do it separately, there are more repetitions.
            JOptionPane.showMessageDialog(this, "Switched to MERGE MODE\nYou can now merge pieces of same color!");
            repaint();
            return;
        }

        //"END GAME(MODE)" BUTTON
        if(e.getX() >= 377 && e.getX() <= 447 && e.getY() >= 23 && e.getY() <= 50){
            clearBoard();
            //Open file selector
            JFileChooser fileChooser = new JFileChooser(".");
            fileChooser.setDialogTitle("Select Endgame File");
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Text Files", "txt"));

            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                String filePath = fileChooser.getSelectedFile().getAbsolutePath();

                //Directly use EndgameLoader to load the endgame
                int[][] endgameBoard = EndgameLoader.loadEndgame(filePath);

                if (endgameBoard != null) {
                    //Load into Logic
                    logic.loadEndgame(endgameBoard);

                    //Reset timer
                    blackTime = 300;
                    whiteTime = 300;
                    updateTimeDisplay();
                    canPlay = true;
                    repaint();

                    //Restart timer
                    if (timer.isRunning()) {
                        timer.stop();
                    }
                    timer.start();

                    JOptionPane.showMessageDialog(this, "Endgame loaded successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to load endgame file!\nPlease check file format.");
                }
            }
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

        int col = (mouseX - 170 + cellSize / 2) / cellSize;
        int row = (mouseY - 118 + cellSize / 2) / cellSize;

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
        } else if (logic.gameMode == 1 && (existingPiece == currentColor || existingPiece == currentColor + 10)) {
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
