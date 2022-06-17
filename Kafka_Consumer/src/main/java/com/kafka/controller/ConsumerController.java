package com.kafka.controller;

import com.kafka.services.KafkaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {

    @Autowired
    KafkaServices services;


    @GetMapping("consumeRest/{message}")
    public String publish_messageRest(
            @PathVariable("message") String message) {
        services.consumedMessage(message);
        return "Message Published on Kafka !";
    }


}
