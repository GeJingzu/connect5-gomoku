package Gobang;
//Yutian Wang

import java.util.Random;

public class SimpleAI {
    private Gomokulogic logic;

    public SimpleAI(Gomokulogic logic){
        this.logic = logic;
    }
    public int[] makeMove(int color){
        return logic.mediumAIMove(color);
    }
}

