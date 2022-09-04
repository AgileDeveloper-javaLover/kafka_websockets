package com.kafka.kafka;

import com.kafka.constant.KafkaConstants;
import com.kafka.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class kafkaConsumerService {

    @Autowired
    SimpMessagingTemplate template;

    @KafkaListener(
            id="kafka-todo",
            topics = KafkaConstants.KAFKA_TOPIC,
            groupId = KafkaConstants.GROUP_ID,
            autoStartup = "false")
    public void publish(String message)
    {
        template.convertAndSend("/topic/todo", message);
        System.out.println(
                "You have a new message: "
                        + message);
    }
}
