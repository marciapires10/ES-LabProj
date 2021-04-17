package com.example.controller;

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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import com.example.repository.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
// import com.fasterxml.jackson.core.JsonProcessingException;
// import com.fasterxml.jackson.databind.JsonMappingException;
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
    private CoordinatesDaoImp coordinatesDao;

    private ObjectMapper mapper = new ObjectMapper();

    private String main_url = "https://opensky-network.org/api";
    private String states_url = "https://opensky-network.org/api/states/all?lamin=36.1435462&lomin=-7.8022543&lamax=43.50133&lomax=2.933316";
    private String test_url = "https://jsonplaceholder.typicode.com/todos/1";
    private String aircraft_url = "https://opensky-network.org/api/flights/aircraft?icao24=3c675a&begin=1517184000&end=1517270400";

    private String states__file_path = "docker.json";
    private String aircraft_file_path = "aircrafts.json";
    private String coordinates_file_path = "coordinates.json";

    @GetMapping("/")
    public String states(Model model) throws IOException
    {
        model.addAttribute("eventName", "States");
        return "index";
    }

    @GetMapping("/states_file")
    public String states_files(Model model) throws IOException
    {
        
        Object objects = mapper.readValue(new File(states__file_path), Object.class);

        StateInfo state_info = mapper.convertValue(objects, StateInfo.class);
        List<String> states_str = new ArrayList<String>();
        for(State state : state_info.getStateObj())
        {
            states_str.add(state.toString());
        }

        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        mapper.writeValue(new File(states__file_path), state_info);

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

    @GetMapping("/aircrafts_file")
    @ResponseBody
    public String aircraft_file(Model model) throws IOException
    {
        Object objects = mapper.readValue(new File(aircraft_file_path), Object.class);

        Aircraft[] aircrafts = mapper.convertValue(objects, Aircraft[].class);
        List<String> aircrafts_str = new ArrayList<String>();
        for(Aircraft aircraft : aircrafts)
        {
            aircrafts_str.add(aircraft.toString());
        }
        
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        mapper.writeValue(new File(aircraft_file_path), aircrafts);

        model.addAttribute("eventName", "Aircrafts");
        return "home";
    }
    
    @GetMapping("/coordinates")
    @ResponseBody
    public List<State> coordinates_file()
    {

        ResponseEntity<Object> response = parsingObject.parseObject(states_url);
        Object objects = response.getBody();

        StateInfo state_info = mapper.convertValue(objects, StateInfo.class);
        state_info.Fill_States();

        logger.debug("READING COORDINATES");
        
        for(State state : state_info.getStateObj())
        {
            stateRepository.save(state);
            coordinatesDao.insertCoordinates(state.getCoordinates());
        }

        return state_info.getStateObj();

    }

    @GetMapping("/get_coordinares_bd")
    @ResponseBody
    public List<Coordinates> get_coordinares_bd()
    {

        return coordinatesDao.getAllCoordinates();
    }

    @GetMapping("/hist")
    @ResponseBody
    public List<State> historic()
    {

        //ResponseEntity<Object> response = parsingObject.parseObject(states_url);
        //Object objects = response.getBody();

        //StateInfo state_info = mapper.convertValue(objects, StateInfo.class);
        //state_info.Fill_States();

        // logger.debug("READING COORDINATES");
        
        // for(State state : state_info.getStateObj())
        // {
        //     stateRepository.save(state);
        // }
        var list = (List<State>) stateRepository.findAll();
        return list;

    }

    // @RequestMapping("/testBD")
    // public String create(){
    //     String userID = "";
    //     try{
    //         State st = new State();
    //         stateRepository.save(st);
    //         userID = String.valueOf(st.getIcao24());

    //     }
    //     catch (Exception ex){
    //         return "Error";
    //     }
    //     return "State successfully created";
    // }
}