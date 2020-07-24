package demo.rabbitmq.direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class App {
    private static final String DIRECT_EXCHANGE_NAME = "direct_exchange";
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        for (int i = 5; i<10; i++) {
            String message = "direct message:" + i;
            // 参数： exchange、routingKey、其它属性、消息
            channel.basicPublish("", DIRECT_EXCHANGE_NAME, null, message.getBytes("UTF-8"));
            System.out.println(message);
        }
        channel.close();
        connection.close();
    }
}
