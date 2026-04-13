import java.util.Random;

public class SimpleAI {
    private Gomokulogic logic;

    public SimpleAI(Gomokulogic logic){
        this.logic = logic;
    }
    public int[] makeMove(int color){
        Random rand = new Random();

        while(true){
            int r = rand.nextInt(15);
            int c = rand.nextInt(15);

            if(logic.getPiece(r,c) == 0){
                logic.placePiece(r,c,color);
                return new int[]{r,c};
            }
        }
    }
}
