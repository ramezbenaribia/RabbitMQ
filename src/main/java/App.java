import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class App {
    int user_id =0 ;


    App() {
        JFrame f = new JFrame("UsersHome");
        Label label;
        label = new Label("Welcome to our small application  :");
        label.setBounds(15, 60, 280, 40);
        label.setAlignment(Label.CENTER);
        label.setBackground(Color.GRAY);
        label.setForeground(Color.WHITE);
        Font fieldFont = new Font("Arial", Font.PLAIN, 16);
        label.setFont(fieldFont);

        JButton button_client = new JButton("add user");
        button_client.setBounds(10, 120, 90, 35);
        button_client.addActionListener(new ActionListener()  {
            @Override
            public void actionPerformed(ActionEvent e) {
                user_id++;
                try {
                    new ClientInterface(user_id, "test-exchange");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (TimeoutException timeoutException) {
                    timeoutException.printStackTrace();
                }
            }
        });
        JButton button_Receiver = new JButton("open the Receiver Panel");
        button_Receiver.setBounds(120, 120, 190, 35);
        button_Receiver.addActionListener(new ActionListener()  {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new   UserReceiver() ;
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (TimeoutException timeoutException) {
                    timeoutException.printStackTrace();
                }
            }
        });
        f.add(label);

        f.add(button_client);
        f.add(button_Receiver);
        f.setSize(350, 250);
        f.setLayout(null);
        f.setVisible(true);
    }
    public static void main (String[]args) throws IOException, TimeoutException{
        new App();
    }
}
