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
    @Test
    @DisplayName("Undo works")
    public void testUndo() {
        game.placePiece(7, 7);
        game.placePiece(7, 8);

        assertTrue(game.undo());
        assertEquals(Constants.EMPTY, game.getBoard()[7][8]);
    }

    @Test
    @DisplayName("Redo works")
    public void testRedo() {
        game.placePiece(7, 7);
        game.undo();

        assertTrue(game.redo());
        assertEquals(Constants.BLACK, game.getBoard()[7][7]);
    }
    @Test
    @DisplayName("Five in a row wins")
    public void testWin() {
        game.placePiece(7, 5); game.placePiece(0, 0);
        game.placePiece(7, 6); game.placePiece(0, 1);
        game.placePiece(7, 7); game.placePiece(0, 2);
        game.placePiece(7, 8); game.placePiece(0, 3);
        game.placePiece(7, 9);

        assertTrue(game.isGameOver());
        assertEquals(Constants.BLACK, game.getWinner());
    }

    @Test
    @DisplayName("Merge captures enemies")
    public void testMerge() {
        game.placePiece(7, 6);
        game.placePiece(6, 7);
        game.placePiece(7, 7);
        game.placePiece(8, 7);
        game.placePiece(7, 8);

        assertTrue(game.tryMerge(7, 7));
        assertEquals(Constants.BLACK, game.getBoard()[6][7]);
        assertEquals(Constants.BLACK, game.getBoard()[8][7]);
    }
}
