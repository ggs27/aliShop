package com.example.demo.rocketmq.MessageSelectorBySql;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 *
 * @author guoguisong
 * @date 2022/6/12
 */

/**
 * 发送消息时，你能通过putUserProperty来设置消息的属性
 */
public class Producer {

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("Producer");
        // 设置NameServer的地址
        producer.setNamesrvAddr("localhost:9876");
        producer.start();
        String tag = "TagSql";
        int i = 1;
        Message msg = new Message("TopicTest",
                tag,
                ("ConsumerBySql" + i).getBytes(RemotingHelper.DEFAULT_CHARSET)
        );
        // 设置一些属性
        msg.putUserProperty("a", String.valueOf(i));
        SendResult sendResult = producer.send(msg);
        producer.shutdown();
    }

}
