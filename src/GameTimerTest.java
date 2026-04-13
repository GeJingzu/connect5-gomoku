import javax.swing.*;
import java.awt.*;

//Yutian Wang: Unit Test for GameTimer
 // This test ensures the countdown logic and callback execution are working.

public class GameTimerTest {

    public static void main(String[] args) {
        // Since GameTimer depends on a JLabel, we create a simple window for visual testing
        JFrame frame = new JFrame("GameTimer Unit Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new FlowLayout());

        JLabel timeLabel = new JLabel("Timer initializing...");
        timeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        frame.add(timeLabel);

        // 1. Define what happens when time is up (The Callback)
        Runnable onTimeoutAction = () -> {
            System.out.println("[Test Result] Success: Timeout callback executed!");
            JOptionPane.showMessageDialog(frame, "Time is up! Test Passed.");
        };

        // 2. Initialize the GameTimer
        GameTimer gameTimer = new GameTimer(timeLabel, onTimeoutAction);

        // 3. Add a Reset button to test the reset functionality
        JButton resetBtn = new JButton("Reset Timer (Next Turn)");
        resetBtn.addActionListener(e -> {
            gameTimer.reset();
            System.out.println("[Test Action] Timer reset to 30s.");
        });
        frame.add(resetBtn);

        frame.setVisible(true);

        // 4. Start the test
        System.out.println("=== Starting GameTimer Unit Test ===");
        System.out.println("[Test Step 1] Starting the timer...");
        gameTimer.start();

        // Note: You should see the label updating every second in the window.
    }
}
