package com.jesper.seckill.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 *
 * @author guoguisong
 * @date 2022/5/15
 */


public class Consumer {

    public static void main(String[] args) throws MQClientException {

        String nameServer = "localhost:9876";
        String groupID = "groupid";
        String topic = "test";

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(groupID);
        consumer.setNamesrvAddr(nameServer);
        consumer.subscribe(topic, "*");
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                            ConsumeConcurrentlyContext context) {
                for(MessageExt msg : msgs) {
                    String result = msg.getBody().toString();
                    System.out.println("--------------------msg="+result);
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();
    }
}
