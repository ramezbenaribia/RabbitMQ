import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Send {

    public String queueName;

    private String message;
    Send(String message, String queueName) {
        this.message = message;
        this.queueName = queueName;
    }


    public void sendMessage() {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");

        try(Connection connection = connectionFactory.newConnection()) {
            Channel channel = connection.createChannel();
            channel.queueDeclare(this.queueName,false,false,false,null);

            channel.basicPublish("",this.queueName,null, this.message.getBytes());
            System.out.println(" [x] sent '"+message+" '");
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
