import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class UserReceiver {
    public final static String QUEUE_NAME="test-exchange";

      UserReceiver() throws IOException, TimeoutException {
        JPanel jPanel = new JPanel();
        jPanel.setBorder(new TitledBorder(new EtchedBorder(), "Receiver"));
        jPanel.setBounds(10,30,800,600);
        JFrame f= new JFrame("Receiver Text");



        JTextArea area=new JTextArea(20,20);
        Font fieldFont = new Font("Arial", Font.PLAIN, 18);
        area.setFont(fieldFont);
        area.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder(),
                new EmptyBorder(new Insets(15, 25, 15, 25))));
        area.setForeground(Color.red);

        area.setEditable(false);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);


        JScrollPane scroll = new JScrollPane(area);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setBounds(10, 265, 455, 249);

        jPanel.add(scroll);


        f.add(jPanel);
        f.setSize(830,670);
        f.setLayout(null);
        f.setVisible(true);


        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");

        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);


        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String receivedMessage = new String(delivery.getBody(),"UTF-8");
          area.setText(receivedMessage);
            System.out.println(" [x] sent '"+receivedMessage+" '");

        };
        channel.basicConsume(QUEUE_NAME,true,deliverCallback,consumerTag -> {});
    }
}
