import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import javax.swing.*;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class UserReceiver {
    public final static String QUEUE_NAME1="user1";
    public final static String QUEUE_NAME2="user2";
    public final static String QUEUE_NAME3="user3";
    static JLabel userLabel1, userLabel2, userLabel3;
    public static void main(String[] args) throws IOException, TimeoutException {
        receive();
    }

    static void receive() throws IOException, TimeoutException {
        JFrame f= new JFrame("Responses");

        userLabel1=new JLabel("User 1: ");
        userLabel1.setBounds(50,50, 300,30);
        f.add(userLabel1);

        userLabel2=new JLabel("User 2: ");
        userLabel2.setBounds(50,100, 300,30);
        f.add(userLabel2);


        userLabel3=new JLabel("User 3: ");
        userLabel3.setBounds(50,150, 300,30);
        f.add(userLabel3);

        f.setSize(500,500);
        f.setLayout(null);
        f.setVisible(true);


        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");

        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME1,false,false,false,null);


        Connection connection2 = connectionFactory.newConnection();
        Channel channel2 = connection2.createChannel();
        channel2.queueDeclare(QUEUE_NAME2,false,false,false,null);

        Connection connection3 = connectionFactory.newConnection();
        Channel channel3 = connection3.createChannel();
        channel3.queueDeclare(QUEUE_NAME3,false,false,false,null);

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String receivedMessage = new String(delivery.getBody(),"UTF-8");
            userLabel1.setText("User 1: "+receivedMessage);
            System.out.println(" User 1 sent '"+receivedMessage+" '");

        };
        channel.basicConsume(QUEUE_NAME1,true,deliverCallback,consumerTag -> {});

        DeliverCallback deliverCallback2 = (consumerTag, delivery) -> {
            String receivedMessage = new String(delivery.getBody(),"UTF-8");
            userLabel2.setText("User 2: "+receivedMessage);
            System.out.println(" User 2 sent '"+receivedMessage+" '");

        };
        channel.basicConsume(QUEUE_NAME2,true,deliverCallback2,consumerTag -> {});


        DeliverCallback deliverCallback3 = (consumerTag, delivery) -> {
            String receivedMessage = new String(delivery.getBody(),"UTF-8");
            userLabel3.setText("User 3: "+receivedMessage);
            System.out.println(" User 3 sent '"+receivedMessage+" '");

        };
        channel.basicConsume(QUEUE_NAME3,true,deliverCallback3,consumerTag -> {});
    }
}
