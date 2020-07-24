package demo.rabbitmq.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class App {
    private static final String TOPIC_EXCHANGE_NAME = "topic_exchange";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(TOPIC_EXCHANGE_NAME, "topic");
        String[] routingKeys = new String[]{"usa.news", "usa.weather",
                "europe.news", "europe.weather"};
        String[] messages = new String[] { "美国新闻", "美国天气",
                "欧洲新闻", "欧洲天气"};
        for (int i = 0; i<(routingKeys.length > messages.length ? messages.length : routingKeys.length); i++) {
            String key = routingKeys[i];
            String msg = messages[i];
            channel.basicPublish(TOPIC_EXCHANGE_NAME, key, null, msg.getBytes("UTF-8"));
            System.out.println("发送消息:" + msg + "到路由:" + key);
        }
        channel.close();
        connection.close();
    }
}
