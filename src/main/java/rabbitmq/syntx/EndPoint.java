package rabbitmq.syntx;

/**
 * Created by dhval on 3/22/15.
 */
import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Represents a connection with a queue
 * @author syntx
 *
 */
@Component
public abstract class EndPoint {

    protected String host;
    protected Channel channel;
    protected Connection connection;
    protected String endPointName;

    public EndPoint( @Value("${rabbit.host:192.168.0.112}") String host,
                     @Value("${rabbit.endpoint:queue}") String endPointName) throws IOException {
        this.host = host;
        this.endPointName = endPointName;

        //Create a connection factory
        ConnectionFactory factory = new ConnectionFactory();

        //hostname of your rabbitmq server
        factory.setHost(host);

        //getting a connection
        connection = factory.newConnection();

        //creating a channel
        channel = connection.createChannel();

        //declaring a queue for this channel. If queue does not exist,
        //it will be created on the server.
        channel.queueDeclare(endPointName, false, false, false, null);
    }


    /**
     * Close channel and connection. Not necessary as it happens implicitly any way.
     * @throws IOException
     */
    public void close() throws IOException{
        this.channel.close();
        this.connection.close();
    }
}
