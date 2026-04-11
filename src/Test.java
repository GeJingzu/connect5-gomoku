public class Test {
    public static void main(String[] args) {
        System.out.println("Test");

        testPlacePiece();
        // We will add more tests here later
    }

    // Test 1: Check basic place and bounds
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
}
