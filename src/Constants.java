//Game constants
public class Constants {
    public static final int BOARD_SIZE = 15;

    //Empty cell
    public static final int EMPTY = 0;
    public static final int BLACK = 1;
    public static final int WHITE = 2;

    //Minimum consecutive pieces required to trigger merge
    public static final int MERGE_THRESHOLD = 3;

    //Range of merge effect (capture enemy pieces within this range)
    public static final int MERGE_RANGE = 1;

    //Data directory for endgame levels
    public static final String ENDGAME_DATA_PATH = "data/endgames/";
}
