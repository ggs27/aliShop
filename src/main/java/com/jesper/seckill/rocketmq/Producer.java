package com.jesper.seckill.rocketmq;

/**
 *
 * @author guoguisong
 * @date 2022/5/21
 */

public class Producer {
    public static void main(String[] args) {

        String nameServer = "localhost:9876";
        String topic = "test";
        String groupID = "groupid";
        String tag = "TAG";
        RocketMQProducerHelper producer = RocketMQProducerHelper.getInstance(nameServer, topic, groupID);
        for (int i=0; i<10; i++) {
            String key = "1000"+i;
            String value = "value="+i;
            producer.send(tag, key, value.getBytes());
        }
    }
}