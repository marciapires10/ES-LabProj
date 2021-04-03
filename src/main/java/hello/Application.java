package hello;
 
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
 
@SpringBootApplication
// @RestController
public class Application {
 
 // @RequestMapping("/")
 // public String home() {
 // return "Hellooooo";
 // }
 private static final Logger logger = LogManager.getLogger(Application.class);
 public static void main(String[] args) {
 SpringApplication.run(Application.class, args);
 }
}