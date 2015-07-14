package sample.rabbitmq;

import java.util.HashMap;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Binding.DestinationType;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dhval on 3/22/15.
 */
@Service("messageQueueManager")
public class MessageQueueManagerImpl implements MessageQueueManager{
    @Autowired
    private AmqpAdmin admin;
    @Autowired
    private AmqpTemplate template;
    @Autowired
    private ConnectionFactory connectionFactory;
    @Autowired
    private SimpleMessageListenerContainer container;

    @Override
    public String createQueue(String queueName) {
        //Log.debug("creating queue with name: " + queueName);

        //create queue
        Queue newQueue = new Queue(queueName,true,false,true);
        queueName = admin.declareQueue(newQueue);

        //create binding with exchange
        admin.declareBinding(new Binding(queueName, DestinationType.QUEUE, "directExchange", queueName, new HashMap<String,Object>()));

        //Log.debug("queue successfully created: " + queueName);

        //add queue to listener
        container.addQueues(newQueue);

        //start listener
        container.start();
        return queueName;
    }
    @Override
    public void sendMessage(String message,String destinationQueueName) throws Exception {
        template.convertAndSend("directExchange", destinationQueueName,   MessageBuilder.withBody(message.getBytes()).build());
    }

    @Override
    public void onMessage(Message message) {
        //Log.debug(new String(message.getBody()));
    }
}
