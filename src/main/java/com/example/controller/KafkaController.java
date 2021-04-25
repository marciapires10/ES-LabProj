package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * labproject - KafkaController <br>
 *
 * @author Paulo Vasconcelos paulobvasconcelos@gmail.com
 * @author Pedro Teixeira pedro.teix@ua.pt
 * @version 2.0 - March 11, 2020
 */
@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {
    /* ############################################################################################################## */
    /* Constants */
    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private final ProducerClass producer;

    /* ############################################################################################################## */
    /* Instance Fields */
    // @Autowired
    public KafkaController(ProducerClass producer) {
        this.producer = producer;
    }

    /* ############################################################################################################## */
    /* Publish Methods */
    @PostMapping(value = "/publish")
    public String sendMessageToKafkaTopic(@RequestParam("message") String message){
        this.producer.sendMessage(message);

        String data = "Added data " + message + " at " + dateFormat.format(new Date());
        this.producer.sendMessage(data);

        return "Published Message " + message;
    }

    /* ############################################################################################################## */


}