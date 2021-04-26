package com.example.controller;
import org.apache.logging.log4j.Logger;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ConsumerClass {
    // private static final Logger logger = LogManager.getLogger(ConsumerClass.class);
    // private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
    // //private List<LogsClass> logs = new ArrayList<LogsClass>();

    // @KafkaListener(topics = "aircrafts", groupId = "labproj")
    // public void consume_aircrafts(String msg)
    // {
    //     String date = simpleDateFormat.format(new Date());
    //     logger.info(String.format(" #### RECEIVE AIRCRAFTS -> %s\n #### Received at: %s", msg, date));
    //     logs.add(new LogsClass(msg, date, "aircrafts"));
    // }

    // @KafkaListener(topics = "states", groupId = "labproj")
    // public void consume_states(String msg)
    // {
    //     String date = simpleDateFormat.format(new Date());
    //     logger.info(String.format(" #### RECEIVE STATES -> %s\n #### Received at: %s", msg, date));
    //     logs.add(new LogsClass(msg, date, "states"));
    // }

    // @KafkaListener(topics = "alerts", groupId = "labproj")
    // public void consume_alerts(String msg)
    // {
    //     String date = simpleDateFormat.format(new Date());
    //     logger.info(String.format(" #### RECEIVE ALERT -> %s\n #### Received at: %s", msg, date));
    //     logs.add(new LogsClass(msg, date, "alerts"));
    // }

    // public List<LogsClass> getLogs()
    // {
    //     return this.logs;
    // }
}
