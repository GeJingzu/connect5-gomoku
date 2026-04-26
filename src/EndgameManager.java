import javax.swing.JLabel;

public class EndgameManager {
    private int[][] board;       // Reference to the shared 15x15 board
    private GameTimer gameTimer; // Timer instance
    private JLabel timerLabel;   // UI Label for display

    public EndgameManager(int[][] sharedBoard, JLabel label) {
        this.board = sharedBoard;
        this.timerLabel = label;
        // Initialize timer
        this.gameTimer = new GameTimer(label,null);
    }

    public void clearBoard() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                board[i][j] = 0;
            }
        }
    }

    // Level setup framework and timer integration
    public void setupLevel(int levelId) {
        // 1. Reset everything first
        clearBoard();

        // 2. Start/Restart the timer
        if (gameTimer != null) {
            gameTimer.start();
        }

        // 3. Switch logic for levels
        switch (levelId) {
            //Levels will be added in next step
            default:
                System.out.println("Level not implemented yet.");
                break;
        }
    }
}