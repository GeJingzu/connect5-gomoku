import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;

//Yutian Wang: Unit Test for EndgameLoader
 //This class verifies that the level loading system works as expected.
public class EndgameLoaderTest {

    public static void main(String[] args) {
        String testFilePath = "test_level.txt";

        System.out.println("=== Starting EndgameLoader Unit Test ===");

        // 1. Create a mock endgame file
        if (createMockFile(testFilePath)) {
            System.out.println("[Step 1] Mock level file created.");
        } else {
            System.err.println("[Step 1] Failed to create mock file. Test aborted.");
            return;
        }

        // 2. Use EndgameLoader to read the file
        System.out.println("[Step 2] Loading data using EndgameLoader...");
        int[][] board = EndgameLoader.loadEndgame(testFilePath);

        // 3. Validation Logic
        if (board == null) {
            System.err.println("[Result] FAILED: Loader returned null.");
        } else if (verifyData(board)) {
            System.out.println("[Result] PASSED: Board data matches the mock file!");
        } else {
            System.err.println("[Result] FAILED: Board data is incorrect.");
        }

        // 4. Cleanup: Delete the temporary test file
        File file = new File(testFilePath);
        if (file.delete()) {
            System.out.println("[Step 4] Cleanup: Temporary file deleted.");
        }

        System.out.println("=== Test Finished ===");
    }

    // Helper: Create a 15x15 file with some specific stones
    private static boolean createMockFile(String path) {
        try (PrintWriter out = new PrintWriter(path)) {
            for (int r = 0; r < 15; r++) {
                for (int c = 0; c < 15; c++) {
                    // Place a Black stone at (0,0) and a White stone at (7,7)
                    if (r == 0 && c == 0) out.print("1 ");
                    else if (r == 7 && c == 7) out.print("2 ");
                    else out.print("0 ");
                }
                out.println();
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    // Helper: Check if the loader read the specific stones correctly
    private static boolean verifyData(int[][] board) {
        // Check specific coordinates defined in createMockFile
        boolean checkBlack = (board[0][0] == 1);
        boolean checkWhite = (board[7][7] == 2);
        boolean checkEmpty = (board[1][1] == 0);

        return checkBlack && checkWhite && checkEmpty;
    }
}
