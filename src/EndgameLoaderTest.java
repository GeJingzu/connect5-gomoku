import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class EndgameLoaderTest {

    @Test
    @DisplayName("Test Successful Endgame Loading")
    public void testLoadEndgameSuccess() {
        String testPath = "levels/level1.txt";

        int[][] result = EndgameLoader.loadEndgame(testPath);

        assertNotNull(result, "The board should not be null after loading.");
        assertEquals(15, result.length, "Board should have 15 rows.");
        assertEquals(15, result[0].length, "Board should have 15 columns.");
    }

    @Test
    @DisplayName("Test Invalid File Path Handling")
    public void testLoadEndgameFileNotFound() {
        String invalidPath = "wrong/path/to/level.txt";

        int[][] result = EndgameLoader.loadEndgame(invalidPath);

        assertNull(result, "Should return null when file is not found.");
    }

    @Test
    @DisplayName("Test Data Integrity")
    public void testDataParsing() {
        String testPath = "levels/level1.txt";
        int[][] result = EndgameLoader.loadEndgame(testPath);

        if (result != null) {
            // Check if values are within expected range (0, 1, 2)
            boolean validData = true;
            for (int r = 0; r < 15; r++) {
                for (int c = 0; c < 15; c++) {
                    if (result[r][c] < 0 || result[r][c] > 2) {
                        validData = false;
                        break;
                    }
                }
            }
            assertTrue(validData, "All board values should be 0, 1, or 2.");
        }
    }
}