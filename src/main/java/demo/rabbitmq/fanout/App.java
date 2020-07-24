package demo.rabbitmq.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Hello world!
 *
 */
public class App 
{
    private static final String FANOUT_EXCHANGE_NAME = "fanout_exchange";
    public static void main( String[] args ) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(FANOUT_EXCHANGE_NAME, "fanout");
        for (int i = 5; i<10; i++) {
            String message = "message:" + i;
            channel.basicPublish(FANOUT_EXCHANGE_NAME, "test", null, message.getBytes("UTF-8"));
            System.out.println("发送" + message);
        }
        channel.close();
        connection.close();
    }

}
