
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Users {
    Users(){
        JFrame f= new JFrame("users");
        JTextArea area=new JTextArea("");
        area.setBounds(20,20, 150,150);
        JButton button = new JButton("Send1");
        button.setBounds(200,35,90,35);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Send sender = new Send(area.getText(),"user1");
                sender.sendMessage();
            }
        });


        JTextArea area2=new JTextArea("");
        area2.setBounds(20,200, 150,150);
        JButton button2 = new JButton("Send2");
        button2.setBounds(200,235,90,35);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Send sender = new Send(area2.getText(),"user2");
                sender.sendMessage();
            }
        });

        JTextArea area3=new JTextArea("");
        area3.setBounds(20,400, 150,150);
        JButton button3 = new JButton("Send3");
        button3.setBounds(200,435,90,35);
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Send sender = new Send(area3.getText(),"user3");
                sender.sendMessage();
            }
        });
        f.add(area);
        f.add(button);
        f.setSize(600,600);
        f.setLayout(null);
        f.setVisible(true);

        f.add(area2);
        f.add(button2);

        f.add(area3);
        f.add(button3);


    }
    public static void main(String[] args) {
        new Users();
    }
}
