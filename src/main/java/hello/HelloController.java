package hello;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.plugins.util.ResolverUtil.Test;
import org.attoparser.dom.INode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
// import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
// import com.fasterxml.jackson.core.JsonProcessingException;
// import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
// @RestController
public class HelloController {

    // private List<Integer> num = Arrays.asList(1, 2, 3, 4, 5);
    private List<String> names = Arrays.asList("Tomás", "Márcia"); 


    private static final Logger logger = LogManager.getLogger(HelloController.class);

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ParsingObject parsingObject;

    private ObjectMapper mapper = new ObjectMapper();

    private String main_url = "https://opensky-network.org/api";
    private String states_url = "https://opensky-network.org/api/states/all?lamin=36.1435462&lomin=-7.8022543&lamax=43.50133&lomax=2.933316";
    private String test_url = "https://jsonplaceholder.typicode.com/todos/1";
    private String aircraft_url = "https://opensky-network.org/api/flights/aircraft?icao24=3c675a&begin=1517184000&end=1517270400";

    @GetMapping("/states")
    public String states(Model model) throws IOException
    {
        
        ResponseEntity<Object> response = parsingObject.parseObject(states_url);
        Object objects = response.getBody();

        StateInfo state_info = mapper.convertValue(objects, StateInfo.class);
        state_info.Fill_States();
        List<String> states_str = new ArrayList<String>();
        for(State state : state_info.getStateObj())
        {
            logger.debug(state.toString());
            states_str.add(state.toString());
        }

        model.addAttribute("eventName", "States");
        return "home";
    }

    @GetMapping("/aircrafts")
    public String aircraft(Model model) throws IOException
    {

        
        ResponseEntity<Object[]> response = parsingObject.parseObjects(aircraft_url);
        Object objects = response.getBody();

        Aircraft[] aircrafts = mapper.convertValue(objects, Aircraft[].class);
        List<String> aircrafts_str = new ArrayList<String>();
        for(Aircraft aircraft : aircrafts)
        {
            aircrafts_str.add(aircraft.toString());
        }
        

        model.addAttribute("eventName", "Aircrafts");
        return "home";
    }
    
}