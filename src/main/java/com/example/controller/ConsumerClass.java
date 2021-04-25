package com.example.controller;
import org.apache.logging.log4j.Logger;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;

@Service
public class ConsumerClass {
    private static final Logger logger = LogManager.getLogger(ConsumerClass.class);

    @KafkaListener(topics = "test", groupId = "labproj")
    public void consume(String msg)
    {
        System.out.println("Entonces??");
        logger.info(String.format("######################## RECEIVE TESTE -> %s", msg));
    }
}
