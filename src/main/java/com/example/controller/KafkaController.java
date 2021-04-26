package com.example.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {

    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);
    
    private final ProducerClass producer;
    private final ConsumerClass consumer;

    // @Autowired
    public KafkaController(ProducerClass producer, ConsumerClass consumer) {
        this.producer = producer;
        this.consumer = consumer;
    }

     // Different topics
     // Topic States
    @PostMapping(value = "/publish/states")
    public String sendMessageToStates(@RequestParam("message") String message){
        this.producer.sendMessageStates(message);
        return "Message: '" + message + "'' sent";
    }

    // Topic Aircrafts
    @PostMapping(value = "/publish/aircrafts")
    public String sendMessageToAircrafts(@RequestParam("message") String message){
        this.producer.sendMessageAircrafts(message);
        return "Message: '" + message + "'' sent";
    }

    // Topic Alerts
    @PostMapping(value = "/publish/alerts")
    public String sendMessageToAlerts(@RequestParam("message") String message){
        this.producer.sendMessageAlerts(message);
        return "Message: '" + message + "'' sent";
    }

    // public List<LogsClass> getLogs()
    // {
    //     return consumer.getLogs();
    // }
}