package Gobang;

import org.junit.Test;

import static org.junit.Assert.*;

public class GomokulogicTest {

    @Test
    public void testPlacePiece(){
        Gomokulogic logic = new Gomokulogic(); //Creat object.

        boolean result = logic.placePiece(0, 0, 1); //At (0,0)Put Color 1 (Black Chess).Call method.

        assertTrue(result); //judge its succeed or not.


    }

    @Test
    public void testCheckWinHorizontal(){ //Will 5 pieces in a row horizontally win
        Gomokulogic logic = new Gomokulogic(); //Creat object.

        for (int i = 0; i < 5; i++) { //Place chess pieces
            logic.setPiece(0, i, 1);
        }

        boolean win = logic.checkWin(0, 1, 1);

        assertTrue(win);
    }

    @Test
    public void testCheckWinVertical(){
        Gomokulogic logic = new Gomokulogic();
        for (int i = 0; i < 5; i++) {
            logic.setPiece(i, 0, 1);
        }
        boolean win = logic.checkWin(1, 0, 1);

        assertTrue(win);
    }

    @Test
    public void testCheckWinDiagonal(){
        Gomokulogic logic = new Gomokulogic();

        for (int i = 0; i < 5; i++) {
            logic.setPiece(i, i, 1);
        }

        assertTrue(logic.checkWin(2, 2, 1));
    }




}