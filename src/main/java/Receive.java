import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Receive {
//    public final static String QUEUE_NAME="hello";
////
////    public static void main(String[] args) throws IOException, TimeoutException {
////        receive()
////    }
//    public String receivedMessage;
//    public void receive() throws IOException, TimeoutException {
//        ConnectionFactory connectionFactory = new ConnectionFactory();
//        connectionFactory.setHost("localhost");
//        Connection connection = connectionFactory.newConnection();
//        Channel channel = connection.createChannel();
//        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
//        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
//
//        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
//            this.receivedMessage = new String(delivery.getBody(),"UTF-8");
//
//            System.out.println(" [x] sent '"+this.receivedMessage+" '");
//        };
//        channel.basicConsume(QUEUE_NAME,true,deliverCallback,consumerTag -> {});
//    }
}
