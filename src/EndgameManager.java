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
            case 1: // Attack: Black Connect 4
                board[7][7] = 1; board[7][8] = 1; board[7][9] = 1; board[7][10] = 1;
                break;
            case 2: // Defense: Block White 4
                board[5][5] = 2; board[5][6] = 2; board[5][7] = 2; board[5][8] = 2;
                break;
            case 3: // Vertical Strategy
                board[2][5] = 2; board[3][5] = 2; board[4][5] = 2; board[5][5] = 2;
                break;
            case 4: // Diagonal Threat
                board[2][2] = 1; board[3][3] = 1; board[4][4] = 1; board[5][5] = 1;
                break;
            case 5: // Double Three Block
                board[10][10] = 2; board[10][11] = 2; board[11][10] = 2; board[12][10] = 2;
                break;
            default:
                System.out.println("Level not implemented yet.");
                break;
        }
    }
}