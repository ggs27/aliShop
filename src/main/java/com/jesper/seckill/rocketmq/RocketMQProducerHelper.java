package com.jesper.seckill.rocketmq;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.logging.inner.Logger;

import java.util.List;

/**
 *
 * @author guoguisong
 * @date 2022/5/15
 */


public class RocketMQProducerHelper  {

    private static Logger LOG = Logger.getLogger(RocketMQProducerHelper .class);

    private static String topic;
    private static RocketMQProducerHelper  instance = null;
    private DefaultMQProducer producer;

    private RocketMQProducerHelper (String nameServer, String topicName, String groupID) {
        try {
            if (nameServer != null && topicName != null && groupID != null) {
                topic = topicName;
                producer = new DefaultMQProducer(groupID);
                producer.setRetryTimesWhenSendFailed(4);
                producer.setRetryAnotherBrokerWhenNotStoreOK(true);
                producer.setNamesrvAddr(nameServer);
                producer.start();
                LOG.info("producer started...");
            } else {
                LOG.error("parameter init error");
                throw new Exception("parameter init error");
            }
        } catch (Exception e) {
            LOG.error("producer init error...");
            throw new RuntimeException(e);
        }
    }

    public static RocketMQProducerHelper getInstance(String nameServer, String topic, String groupID) {
        if(instance == null) {
            synchronized (RocketMQProducerHelper.class) {
                if (instance == null) {
                    instance = new RocketMQProducerHelper(nameServer, topic, groupID);
                }
            }
        }
        return instance;
    }

    public SendResult send(byte[] data) {
        return send(topic, null, null, data, null);
    }

    public SendResult send(String tag, byte[] data) {
        return send(topic, tag, null, data, null);
    }

    public SendResult send(String tag, String key, byte[] data) {
        return send(topic, tag, key, data, null);
    }

    public SendResult send(String topic, String tag, String key, byte[] data, final MessageQueueSelector selector) {
        SendResult sendResult = null;
        try {
            Message msg;
            if (tag == null || tag.length() == 0) {
                msg = new Message(topic, data);
            } else if (key == null || key.length() == 0) {
                msg = new Message(topic, tag, data);
            } else {
                msg = new Message(topic, tag, key, data);
            }
            if (selector != null) {
                sendResult = producer.send(msg, new MessageQueueSelector() {
                    public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                        return selector.select(mqs, msg, arg);
                    }
                }, key);
            } else {
                sendResult = producer.send(msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("Send message error");
        }
        return sendResult;
    }
}