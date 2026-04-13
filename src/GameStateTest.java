import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

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
    @Test
    @DisplayName("Can place piece")
    public void testPlace() {
        assertTrue(game.placePiece(7, 7));
        assertEquals(Constants.BLACK, game.getBoard()[7][7]);
        assertEquals(Constants.WHITE, game.getCurrentPlayer());
    }

    @Test
    @DisplayName("Cannot place on occupied")
    public void testOccupied() {
        game.placePiece(7, 7);
        assertFalse(game.placePiece(7, 7));
    }
}
