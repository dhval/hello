package sample.rabbitmq;

/**
 * Created by dhval on 3/22/15.
 *
 * http://syntx.io/getting-started-with-rabbitmq-using-the-spring-framework/
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

@Component
public class Main {

    @Autowired
    QueueConsumer consumer;

    @Value("${rabbit.host:192.168.0.112}") String host;
    @Value("${rabbit.endpoint:queue}") String endPointName;

    private final static ApplicationContext ctx = new ClassPathXmlApplicationContext("rabbitmq-syntx.xml");


    public void staticQ () throws Exception{

        Thread consumerThread = new Thread(consumer);
        consumerThread.start();

        Producer producer = new Producer(host, endPointName);

        for (int i = 0; i < 100000; i++) {
            HashMap message = new HashMap();
            message.put("message number", i);
            producer.sendMessage(message);
            System.out.println("Message Number "+ i +" sent.");
        }
    }

    private MessageQueueManager manager() throws Exception {
        MessageQueueManager manager = ctx.getBean(MessageQueueManagerImpl.class);
        return manager;
    }

    private void create() throws Exception {
        MessageQueueManager manager = manager();
        manager.createQueue("myTestQueue");
        manager.sendMessage("myTestMessage", "myTestQueue");
    }

    private void getMsg() throws Exception {
        MessageQueueManager manager = manager();
       // manager.onMessage();
    }

    /**
     * @param args
     * @throws SQLException
     * @throws IOException
     */
    public static void main(String[] args) throws Exception {
        Main instance = ctx.getBean(Main.class);
        instance.staticQ();
    }
}