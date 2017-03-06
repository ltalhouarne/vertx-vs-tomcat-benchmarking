package com.ltalhouarne.vertx.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ltalhouarne.domain.Transaction;
import com.ltalhouarne.service.KafkaService;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class StaticServer extends AbstractVerticle {
    
    @Autowired
    private KafkaService kafkaService;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @Override
    public void start() throws Exception {
        Router router = Router.router(vertx);
    
        router.route().handler(BodyHandler.create());
        router.post("/vertx").handler(routingContext -> {
            HttpServerResponse response = routingContext.response();
    
            List<String> listOfErrors = new ArrayList<>();
    
            try {
                kafkaService.sendToKafka(objectMapper.readValue(routingContext.getBody().toString(), Transaction.class),
                        result -> System.out.println("success"),
                        ex -> listOfErrors.add(ex.getMessage()));
            } catch (IOException e) {
                response.setStatusCode(500).end("failure");
            }
            if(listOfErrors.isEmpty()){
                response.setStatusCode(200).end("success");
            } else {
                response.setStatusCode(500).end("failure");
            }
        });
    
        vertx.createHttpServer().requestHandler(router::accept).listen(8081);
    }
}
