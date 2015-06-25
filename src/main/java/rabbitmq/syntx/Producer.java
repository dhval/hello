package rabbitmq.syntx;

/**
 * Created by dhval on 3/22/15.
 */
import java.io.IOException;
import java.io.Serializable;

import org.apache.commons.lang.SerializationUtils;


/**
 * The producer endpoint that writes to the queue.
 * @author syntx
 *
 */
public class Producer extends EndPoint{

    public Producer(String host, String endPointName) throws IOException{
        super(host, endPointName);
    }

    public void sendMessage(Serializable object) throws IOException {
        channel.basicPublish("",endPointName, null, SerializationUtils.serialize(object));
    }
}
