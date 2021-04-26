package com.example.controller;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;

@Service
public class ProducerClass {
    private static final Logger logger = LogManager.getLogger(ProducerClass.class);
    private static final String TOPIC_STATES = "states";
    private static final String TOPIC_AIRCRAFTS = "aircrafts";
    private static final String TOPIC_ALERTS = "alerts";

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    // Topic States
    public void sendMessageStates(String msg)
    {
        logger.info(String.format("#### SEND STATES -> %s", msg));
        this.kafkaTemplate.send(TOPIC_STATES,msg);
    }

    // Topic aircrafts
    public void sendMessageAircrafts(String msg)
    {
        logger.info(String.format("#### SEND AIRCRAFTS -> %s", msg));
        this.kafkaTemplate.send(TOPIC_AIRCRAFTS,msg);
    }

    // Topic alerts
    public void sendMessageAlerts(String msg)
    {
        logger.info(String.format("#### SEND ALERT -> %s", msg));
        this.kafkaTemplate.send(TOPIC_ALERTS,msg);
    }
}
