package com.kafka.controller;

import com.kafka.constant.KafkaConstants;
import com.kafka.kafka.kafkaConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@CrossOrigin("*")
@Controller
public class controller {

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    @Autowired
    KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

    @GetMapping(value = "/api/send")
    @ResponseBody
    public void sendMessage(@RequestParam String message) {
        try {
            kafkaTemplate.send(KafkaConstants.KAFKA_TOPIC,message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(value = "/kafka/start")
    @ResponseBody
    public String startKafka() {
        kafkaListenerEndpointRegistry.getListenerContainer("kafka-todo").start();
        return "start";
    }

    @GetMapping(value = "/kafka/end")
    @ResponseBody
    public String endKafka() {
        kafkaListenerEndpointRegistry.getListenerContainer("kafka-todo").stop();
        return "end";
    }

    @GetMapping("/home")
    public String home(){
        return "index";
    }

}
