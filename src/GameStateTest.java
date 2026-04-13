import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class GameStateTest {

    private GameState game;

    @BeforeEach
    void setUp() {
        game = new GameState();
    }

    @Test
    @DisplayName("New game starts empty")
    public void testNewGame() {
        int[][] b = game.getBoard();
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                assertEquals(Constants.EMPTY, b[i][j]);
            }
        }
        assertEquals(Constants.BLACK, game.getCurrentPlayer());
        assertFalse(game.isGameOver());
    }
}
