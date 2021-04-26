package com.example.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.context.annotation.Bean;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import com.example.repository.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Controller
// @RestController
@CrossOrigin(origins = "http://localhost:8080")
public class HelloController {

    private static final Logger logger = LogManager.getLogger(HelloController.class);

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ParsingObject parsingObject;
    @Autowired
    StateRepository stateRepository;
    @Autowired
    KafkaController kafkaController;

    private ObjectMapper mapper = new ObjectMapper();

    private String main_url = "https://opensky-network.org/api";
    private String states_url = "https://opensky-network.org/api/states/all?lamin=36.1435462&lomin=-7.8022543&lamax=43.50133&lomax=2.933316";
    private String test_url = "https://jsonplaceholder.typicode.com/todos/1";
    private String aircraft_url = "https://opensky-network.org/api/flights/aircraft?icao24=3c675a&begin=1517184000&end=1517270400";

    private String states__file_path = "docker.json";
    private String aircraft_file_path = "aircrafts.json";
    private String coordinates_file_path = "coordinates.json";

    private List<State> states;
    private String popularCountry = "Default";
    private int nFlights = 0;
    private double velocity = 0; 

    @GetMapping("/")
    public String states(Model model) throws IOException
    {
        // kafkaController.sendMessageToKafkaTopic("MAIN MAIN MAIN MAIN");
        this.popularCountry = "calculating...";
        model.addAttribute("test", this.popularCountry);
        // model.addAttribute("eventName", "States");
        return "index";
    }

    @GetMapping("/aircrafts")
    @ResponseBody
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
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        mapper.writeValue(new File(aircraft_file_path), aircrafts);

        return "home";
    }
    
    @GetMapping("/coordinates")
    @ResponseBody
    @Scheduled(fixedRate = 5000)
    public void coordinates()
    {
        //kafkaController.sendMessageToStates("Getting information from planes.");
        ResponseEntity<Object> response = parsingObject.parseObject(states_url);
        Object objects = response.getBody();

        StateInfo state_info = mapper.convertValue(objects, StateInfo.class);
        state_info.Fill_States();        
        for(State state : state_info.getStateObj())
        {
            if(stateRepository.findByicao24(state.icao24).size() == 0)
            {
                stateRepository.save(state);
               // kafkaController.sendMessageToAlerts(" The plane -> " + state.icao24 + " <- is now flying over Spain.");
            }
        }

        this.states = state_info.getStateObj();
    }

    
    @GetMapping("/check_landing_event")
    @ResponseBody
    @Scheduled(fixedRate = 5000)
    public void check_landings_event()
    {
        ResponseEntity<Object> response = parsingObject.parseObject(states_url);
        Object objects = response.getBody();

        StateInfo state_info = mapper.convertValue(objects, StateInfo.class);
        state_info.Fill_States();        
        for(State state : state_info.getStateObj())
        {
            if(stateRepository.findByicao24(state.icao24).size() == 1)
            {
                if(!stateRepository.findByicao24(state.icao24).get(0).on_ground && state.on_ground)
                {
                    //kafkaController.sendMessageToAlerts(" The plane -> " + state.icao24 + " <- has landed.");
                }
                else if(stateRepository.findByicao24(state.icao24).get(0).on_ground && !state.on_ground)
                {
                    //kafkaController.sendMessageToAlerts(" The plane -> " + state.icao24 + " <- has departed.");
                }
            }
        }
    }

    @GetMapping("/get_coordinates")
    @ResponseBody
    public List<State> get_coordinates()
    {
        return this.states;
    }
    @GetMapping("/get_coordinates_bd")
    @ResponseBody
    public List<State> get_coordinates_bd()
    {

        return (List<State>)stateRepository.findAll();
    }

    @GetMapping("/hist")
    @ResponseBody
    public List<State> historic()
    {

        var list = (List<State>) stateRepository.findAll();
        this.nFlights = list.size();
        return list;

    }

    @GetMapping("/popular_country")
    @ResponseBody
    @Scheduled(fixedRate = 5000)
    public void PopularCountry()
    {
        //kafkaController.sendMessageToStates("Calculating the most popular country.");
        String country = "Default";
        int max_ocurrences = 0;
        List<String> seen_countries = new ArrayList<String>();
        for(State state : stateRepository.findAll())
        {
            if(!seen_countries.contains(state.origin_country) && state.origin_country.compareTo("Spain") != 0)
            {
                seen_countries.add(state.origin_country);
                int countries_found = stateRepository.findByorigin_country(state.origin_country).size();
                if(countries_found > max_ocurrences)
                {
                    country = state.origin_country;
                    max_ocurrences = countries_found;
                }
            }
        }
        this.popularCountry = country;
    }

    @GetMapping("/get_popular_country")
    @ResponseBody
    public String getPopularCountry()
    {
        String pop_country = "Most popular country -> " + this.popularCountry;
        //kafkaController.sendMessageToStates(pop_country);
        return this.popularCountry;
    }

    @GetMapping("/get_num_flights")
    @ResponseBody
    public int getNumFlights(){
        return this.nFlights;
    }

    // @GetMapping("/logs")
    // @ResponseBody
    // public List<LogsClass> logs()
    // {
    //     return kafkaController.getLogs();
    // }

    @GetMapping("/logs_page")
    public String logs_page(Model model) throws IOException
    {
        // kafkaController.sendMessageToKafkaTopic("MAIN MAIN MAIN MAIN");
        this.popularCountry = "Default teste";
        // model.addAttribute("test", this.popularCountry);
        // model.addAttribute("eventName", "States");
        return "Logs";
    }

    @GetMapping("/medium_velocity")
    @ResponseBody
    @Scheduled(fixedRate = 5000)
    public void MediumVelocity()
    {
        //kafkaController.sendMessageToStates("Calculating the medium velocity.");
        double m_velocity = 0;
        List<State> states = stateRepository.findAll();
        for(State state : states)
        {
            if(state.velocity != 0)
            {
                m_velocity += Math.abs(state.velocity);
            }
        }
        m_velocity = m_velocity/states.size() ;
        this.velocity = m_velocity;
    }

    @GetMapping("/get_medium_velocity")
    @ResponseBody
    public int getMediumVelocity()
    {
        String velocity_str = "The medium velocity of the current planes is -> " + this.velocity;
        //kafkaController.sendMessageToStates(velocity_str);
        return (int) this.velocity;
    }

}