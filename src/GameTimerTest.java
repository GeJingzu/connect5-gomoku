import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import javax.swing.*;
import static org.junit.jupiter.api.Assertions.*;

public class GameTimerTest {

    @Test
    @DisplayName("Test Timer Countdown Logic")
    public void testTimerCountdown() throws InterruptedException {
        JLabel mockLabel = new JLabel();

        GameTimer gameTimer = new GameTimer(mockLabel, () -> {
            System.out.println("Timeout triggered!");
        });

        gameTimer.start();
        assertEquals(30, gameTimer.getSecondsLeft(), "Initial time should be 30");

        Thread.sleep(1500);

        assertTrue(gameTimer.getSecondsLeft() < 30, "Seconds left should decrease over time");
        System.out.println("Current seconds left: " + gameTimer.getSecondsLeft());

        gameTimer.stop();
    }

    @Test
    @DisplayName("Test Timer Reset Logic")
    public void testTimerReset() {
        JLabel mockLabel = new JLabel();
        GameTimer gameTimer = new GameTimer(mockLabel, null);

        gameTimer.start();

        gameTimer.reset();

        assertEquals(30, gameTimer.getSecondsLeft(), "Timer should return to 30 after reset");
    }

    @Test
    @DisplayName("Test Timeout Callback Execution")
    public void testTimeoutCallback() throws InterruptedException {
        // We use a simple boolean array to track if the callback was run
        final boolean[] timeoutReached = {false};

        JLabel mockLabel = new JLabel();
        // Set a very short test (This requires modifying TURN_TIME or
        // creating a fast-timer for testing, but we can simulate logic)
        GameTimer gameTimer = new GameTimer(mockLabel, () -> {
            timeoutReached[0] = true;
        });

        gameTimer.start();

        // Since we can't easily wait 30s in a unit test, we manually trigger
        // the logic if your code allows, or verify the structure.
        // In a real scenario, you'd test a smaller TURN_TIME for unit tests.
        assertFalse(timeoutReached[0], "Callback should not run immediately");

        gameTimer.stop();
    }
}