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

    private ObjectMapper mapper = new ObjectMapper();

    private String main_url = "https://opensky-network.org/api";
    private String states_url = "https://opensky-network.org/api/states/all?lamin=36.1435462&lomin=-7.8022543&lamax=43.50133&lomax=2.933316";
    private String test_url = "https://jsonplaceholder.typicode.com/todos/1";
    private String aircraft_url = "https://opensky-network.org/api/flights/aircraft?icao24=3c675a&begin=1517184000&end=1517270400";

    private String states__file_path = "docker.json";
    private String aircraft_file_path = "aircrafts.json";
    private String coordinates_file_path = "coordinates.json";

    private List<State> states;
    private String popularCountry;

    @GetMapping("/")
    public String states(Model model) throws IOException
    {
        logger.debug("MAIN DEBUG");
        logger.warn("MAIN WARN");
        logger.error("MAIN ERROR");
        logger.info("MAIN INFO");
        model.addAttribute("eventName", "States");
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

        model.addAttribute("eventName", "Welelel");
        return "home";
    }
    
    @GetMapping("/coordinates")
    @ResponseBody
    @Scheduled(fixedRate = 5000)
    public void coordinates()
    {
        logger.info("Getting information from planes.");
        ResponseEntity<Object> response = parsingObject.parseObject(states_url);
        Object objects = response.getBody();

        StateInfo state_info = mapper.convertValue(objects, StateInfo.class);
        state_info.Fill_States();        
        for(State state : state_info.getStateObj())
        {
            if(stateRepository.findByicao24(state.icao24).size() == 0)
            {
                stateRepository.save(state);
            }
        }

        this.states = state_info.getStateObj();
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
        return list;

    }

    @GetMapping("/popular_country")
    @ResponseBody
    @Scheduled(fixedRate = 5000)
    public void PopularCountry()
    {
        logger.info("Calculating the most popular country.");
        String country = "Default";
        int max_ocurrences = 0;
        List<String> seen_countries = new ArrayList<String>();
        for(State state : stateRepository.findAll())
        {
            if(!seen_countries.contains(state.origin_country))
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
        return this.popularCountry;
    }

    // @RequestMapping("/test_kafka")
    // @Scheduled(fixedRate = 5000)
    // public ResponseEntity<String> testKafka() throws URISyntaxException
    // {
    //     URI URI = new URI("/kafka/publish/?message='Hello");
    //     return (ResponseEntity<String>) ResponseEntity.created(URI);
    // }
}