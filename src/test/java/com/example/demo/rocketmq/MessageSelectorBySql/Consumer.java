package com.example.demo.rocketmq.MessageSelectorBySql;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MessageSelector;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 *
 * @author guoguisong
 * @date 2022/6/10
 */

/**
 * 用MessageSelector.bySql来使用sql筛选消息
 */
public class Consumer {

    public static void main(String[] args) throws Exception {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("ConsumerInOrder");
        // 设置NameServer的地址
        consumer.setNamesrvAddr("localhost:9876");
        // 只有订阅的消息有这个属性a, a >=0 and a <= 3
        consumer.subscribe("TopicTest", MessageSelector.bySql("a between 0 and 3"));
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();

    }
}
