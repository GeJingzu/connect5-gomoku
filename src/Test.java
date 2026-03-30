
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
        if(result==2){
            JOptionPane.showMessageDialog(cf,"We need say goodbye if you want leave.");
            System.exit(0);
        }


        String name;
        while(true){
            name = JOptionPane.showInputDialog("Input your name,please."); //Input box message.
            if(name==null){
                int result2 = JOptionPane.showConfirmDialog(cf,"Do you want to exit input?");
                if(result2==2){
                    continue;
                } else if(result2==0){
                    System.exit(0);
                }
                else if(result2==1){
                    continue;
                }
            }
            if(name.trim().isEmpty()){
                JOptionPane.showMessageDialog(null,"Please input your name again.");
                continue;
            }else{
                System.out.println("Your name is:" + name);
            }
        }
    }
}
