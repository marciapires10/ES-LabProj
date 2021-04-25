package com.example.controller;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;

@Service
public class ProducerClass {
    private static final Logger logger = LogManager.getLogger(ProducerClass.class);
    private static final String TOPIC = "test";

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    public void sendMessage(String msg)
    {
        logger.info(String.format("######################## SEND TESTE -> %s", msg));
        this.kafkaTemplate.send(TOPIC,msg);
    }
}
