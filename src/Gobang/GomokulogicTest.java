package Gobang;

import org.junit.Test;

public class GomokulogicTest {

    @Test
    public void testPlacePiece(){
        Gomokulogic logic = new Gomokulogic(); //Creat object.

        boolean result = logic.placePiece(0, 0, 1); //At (0,0)Put Color 1 (Black Chess).


    }
}