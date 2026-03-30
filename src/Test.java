
import javax.swing.*;

public class Test {
    public static void main(String[] args){
        ChessFrame cf = new ChessFrame();
        JOptionPane.showMessageDialog(cf,"Hello"); //Open the message dialog box returned to you by the form(Mainly to prompt information)
        int result = JOptionPane.showConfirmDialog(cf,"You want play now?"); //Give you a choice message.
        System.out.println("The result you chose is:" + result);
        if(result==0){
            JOptionPane.showMessageDialog(cf,"Play now!");

        }
        if(result==1){
            JOptionPane.showMessageDialog(cf,"We need say goodbye if you want leave.");
            System.exit(0);
        }
    }
}
