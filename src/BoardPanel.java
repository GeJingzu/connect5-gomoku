
/**
 * NAME:Yunlong Wang
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

//A panel class responsible for drawing checkerboards and handling mouse events.
public class BoardPanel extends JPanel implements MouseListener {
    private static final int SIZE = 15; //Chessboard 15*15.
    private Gomokulogic logic; //Use logic class instead of local board array.
    private int cellSize = 380/(SIZE-1); //Size of each grid.
    private boolean canPlay = false;

    //Timer display placeholders.
    private String blackTimeText = "--:--";
    private String whiteTimeText = "--:--";


}
