import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Yutian Wang: GameTimer Module
 //This class handles the countdown logic independently from the game board.
public class GameTimer {
    private Timer timer;
    private int secondsLeft;
    private final int TURN_TIME = 30; // 30 seconds per turn
    private JLabel timeLabel;         // The UI label to display the countdown
    private Runnable onTimeout;       // The action to perform when time runs out

    // Constructor for GameTimer
     //@param timeLabel The JLabel provided by Member B (UI) to show the time
     //@param onTimeout A callback function (lambda) to execute when secondsLeft == 0
    public GameTimer(JLabel timeLabel, Runnable onTimeout) {
        this.timeLabel = timeLabel;
        this.onTimeout = onTimeout;
        this.secondsLeft = TURN_TIME;

        // Initialize the Swing Timer to trigger every 1000ms (1 second)
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                secondsLeft--;
                updateLabel();

                if (secondsLeft <= 0) {
                    timer.stop();
                    if (onTimeout != null) {
                        onTimeout.run(); // Trigger the game-over/timeout logic
                    }
                }
            }
        });
    }

    // Start the timer from the beginning
    public void start() {
        secondsLeft = TURN_TIME;
        updateLabel();
        timer.start();
    }

    // Reset the timer for the next player's turn
    public void reset() {
        secondsLeft = TURN_TIME;
        updateLabel();
        if (!timer.isRunning()) {
            timer.start();
        }
    }

    // Stop the timer (e.g., when the game ends normally)
    public void stop() {
        timer.stop();
    }

    // Helper method to update the UI text
    private void updateLabel() {
        if (timeLabel != null) {
            timeLabel.setText("Time Left: " + secondsLeft + "s");
        }
    }

    // Getters for status if needed
    public int getSecondsLeft() {
        return secondsLeft;
    }
}