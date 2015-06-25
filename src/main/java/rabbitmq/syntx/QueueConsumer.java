package rabbitmq.syntx;

/**
 * Created by dhval on 3/22/15.
 */
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.SerializationUtils;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * The endpoint that consumes messages off of the queue. Happens to be runnable.
 * @author syntx
 *
 */
@Component
public class QueueConsumer extends EndPoint implements Runnable, Consumer {

    @Autowired
    public QueueConsumer( @Value("${rabbit.host:192.168.0.112}") String host,
                          @Value("${rabbit.endpoint:queue}") String endPointName) throws IOException{
        super(host, endPointName);
    }

    public void run() {
        try {
            //start consuming messages. Auto acknowledge messages.
            channel.basicConsume(endPointName, true,this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Called when consumer is registered.
     */
    public void handleConsumeOk(String consumerTag) {
        System.out.println("Consumer "+consumerTag +" registered");
    }

    /**
     * Called when new message is available.
     */
    public void handleDelivery(String consumerTag, Envelope env,
                               BasicProperties props, byte[] body) throws IOException {
        Map map = (HashMap)SerializationUtils.deserialize(body);
        System.out.println("Message Number "+ map.get("message number") + " received.");

    }

    public void handleCancel(String consumerTag) {}
    public void handleCancelOk(String consumerTag) {}
    public void handleRecoverOk(String consumerTag) {}
    public void handleShutdownSignal(String consumerTag, ShutdownSignalException arg1) {}
}
