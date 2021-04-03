package hello;
 
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
// import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.bind.annotation.GetMapping;
 
import java.util.Arrays;
import java.util.List;
 
@Controller
// @RestController
public class HelloController {
 
    private static final Logger logger = LogManager.getLogger(HelloController.class);
 
    private List<Integer> num = Arrays.asList(1, 2, 3, 4, 5);
    private List<String> names = Arrays.asList("Tomás", "Márcia"); 
 
    @GetMapping("/")
    public String main(Model model) {
        logger.debug("O logger já está operacional");
        // pre-java 8
        if (logger.isDebugEnabled()) {
            logger.debug("Hello from Log4j 2 - num : {}", num);
        }
        else
        {
            logger.debug("Hellooo from the outsiidee");
        }
        // java 8 lambda, no need to check log level
        logger.debug("Hello from Log4j 2 - num : {}", () -> num);
        logger.debug("Este é atualizado");
        model.addAttribute("eventName", "OpenSky ES Lab Project");
        return "home"; //view
    }
}