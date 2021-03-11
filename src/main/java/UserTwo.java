
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserTwo {

    UserTwo(){
        JFrame f= new JFrame();
        JTextArea area=new JTextArea("");
        area.setBounds(20,20, 150,150);
        JButton button = new JButton("Send");
        button.setBounds(200,35,90,35);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Send sender = new Send(area.getText(),"user2");
                sender.sendMessage();
            }
        });
        f.add(area);
        f.add(button);
        f.setSize(400,400);
        f.setLayout(null);
        f.setVisible(true);
    }
    public static void main(String[] args) {
        new UserTwo();
    }
}
