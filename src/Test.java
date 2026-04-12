public class Test {
    public static void main(String[] args) {
        System.out.println("Test");
        testWinLogic();
        testPlacePiece();
        testMergeMode();
        testTurnSwitch();
        testUndo();
        testEasyAI();
    }

    // Check basic place and bounds
    public static void testPlacePiece() {
        Gomokulogic game = new Gomokulogic();

        // 1. Normal place
        boolean res1 = game.placePiece(7, 7, 1);
        System.out.println("Test place (7,7): " + res1); // Expected: true

        // 2. Place on taken spot
        boolean res2 = game.placePiece(7, 7, 2);
        System.out.println("Test spot taken: " + !res2); // Expected: true (since res2 should be false)

        // 3. Out of bounds
        boolean res3 = game.placePiece(-1, 0, 1);
        System.out.println("Test out of bounds: " + !res3); // Expected: true
    }
    // Check horizontal win logic
    public static void testWinLogic() {
        Gomokulogic game = new Gomokulogic();

        game.placePiece(0, 0, 1);
        game.placePiece(0, 1, 1);
        game.placePiece(0, 2, 1);
        game.placePiece(0, 3, 1);

        boolean win1 = game.checkWin(0, 3, 1);
        System.out.println(win1); // Expected: true

        game.placePiece(0, 4, 1);
        boolean win2 = game.checkWin(0, 4, 1);
        System.out.println(win2); // Expected: true
    }
    //Check piece merge mode
    public static void testMergeMode() {
        Gomokulogic game = new Gomokulogic();
        game.gameMode = 1; // Enable merge mode

        game.placePiece(5, 5, 2);

        game.placePiece(5, 5, 2);

        int val = game.getPiece(5, 5);
        System.out.println((val == 12)); // Expected: true
    }
    // Check turn switching
    public static void testTurnSwitch() {
        Gomokulogic game = new Gomokulogic();
        System.out.println((game.getCurrentTurn() == 1)); // Expected: true
        game.placePiece(0, 0, 1);
        System.out.println((game.getCurrentTurn() == 2)); // Expected: true
    }
    // Check undo functionality
    public static void testUndo() {
        Gomokulogic game = new Gomokulogic();
        System.out.println(!game.undo()); // Expected: true
        game.placePiece(7, 7, 1);
        game.undo();
        System.out.println((game.getPiece(7, 7) == 0)); // Expected: true
    }

    // Check AI move
    public static void testEasyAI() {
        Gomokulogic game = new Gomokulogic();
        int[] aiMove = game.easyAIMove(2);
        boolean isValid = (aiMove != null && aiMove.length == 2);
        System.out.println(isValid); // Expected: true
        if (isValid) {
            System.out.println((game.getPiece(aiMove[0], aiMove[1]) == 2)); // Expected: true
        }
    }
}
