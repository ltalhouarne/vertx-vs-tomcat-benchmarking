package com.ltalhouarne.tomcat.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ltalhouarne.domain.Transaction;
import com.ltalhouarne.service.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {
    
    @Autowired
    private KafkaService kafkaService;
    
    @ResponseBody
    @RequestMapping(value = "tomcat",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> adjust(@RequestBody Transaction message) throws JsonProcessingException, InterruptedException {
    
        List<String> listOfErrors = new ArrayList<>();
    
        try {
            kafkaService.sendToKafka(message,
                    result -> System.out.println("success"),
                    ex -> listOfErrors.add(ex.getMessage()));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("failure");
        }
    
        return listOfErrors.isEmpty() ? ResponseEntity.ok("success") : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("failure");
    }
}
