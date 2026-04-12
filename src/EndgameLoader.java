import java.io.*;


//Endgame level loader
public class EndgameLoader {

    public static class Level {
        public final String name;
        public final int firstPlayer;
        public final int targetMoves;
        public final int[][] board;

        public Level(String name, int firstPlayer, int targetMoves, int[][] board) {
            this.name = name;
            this.firstPlayer = firstPlayer;
            this.targetMoves = targetMoves;
            this.board = board;
        }
    }

    private String dataPath;

    public EndgameLoader(String path) {
        this.dataPath = path;
        new File(path).mkdirs();
    }

    public Level loadLevel(String filename) {
        try (BufferedReader reader = new BufferedReader(
                new FileReader(dataPath + filename))) {

            String name = reader.readLine();
            String first = reader.readLine();
            int firstPlayer = first.equals("BLACK") ? Constants.BLACK : Constants.WHITE;
            int targetMoves = Integer.parseInt(reader.readLine());

            int[][] board = new int[Constants.BOARD_SIZE][Constants.BOARD_SIZE];
            for (int i = 0; i < Constants.BOARD_SIZE; i++) {
                String[] nums = reader.readLine().trim().split("\\s+");
                for (int j = 0; j < Constants.BOARD_SIZE; j++) {
                    board[i][j] = Integer.parseInt(nums[j]);
                }
            }

            return new Level(name, firstPlayer, targetMoves, board);

        } catch (IOException e) {
            return null;
        }
    }

}


