package Gobang;
/**
 * NAME:Yunlong Wang
 */

import javax.swing.*;
import java.util.Objects;

public class Test {
    public static void main(String[] args){
        ChessFrame cf = new ChessFrame();

        String name;
        while(true){
            name = JOptionPane.showInputDialog("Input your name,please."); //Input box message.
            if(name==null){
                int result3 = JOptionPane.showConfirmDialog(cf,"Do you want to exit input?");
                if(result3==2){
                    continue;
                } else if(result3==0){
                    System.exit(0);
                }
                else if(result3==1){
                    continue;
                }
            }
            if(name.trim().isEmpty()){
                JOptionPane.showMessageDialog(null,"Please input your name again.");
                continue;
            }else{
                JOptionPane.showMessageDialog(null,"OK，let's start!!!");
                break;
            }
        }


    }
}
