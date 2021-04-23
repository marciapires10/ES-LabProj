package com.example;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableScheduling
// @RestController
public class Application {

  @Bean
  public RestTemplate getRestTemplate()
  {
    return new RestTemplate();
  } 
  
  private static final Logger logger = LogManager.getLogger(Application.class);
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}