package com.kafka.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.stream.IntStream;

@RestController
@RequestMapping("p1")
@Slf4j
public class Producer_controller {

    @Autowired
    KafkaTemplate<String, String>
            kafkaTemplate;

    @Autowired
    RestTemplate restTemplate;

    static final String TOPIC = "testProducer1";

    @GetMapping("publish/{message}")
    public String publish_message(
            @PathVariable("message") String message) {
        log.info("process start at::{}",System.currentTimeMillis());
        IntStream.range(0, 1000000).forEach(value -> {
            kafkaTemplate.send(TOPIC, message + " " + value);
        });
        return "Message Published on Kafka !";
    }

    @GetMapping("publishRest/{message}")
    public String publish_messageRest(
            @PathVariable("message") String message) {
        log.info("process start at::{}",System.currentTimeMillis());
        IntStream.range(0, 1000000).forEach(value -> {
            restTemplate.getForObject("http://localhost:9091/consumeRest/"+message+" "+value, String.class);
        });
        //TODO add rest endpont call to consumer rest Endpoint till 1L

        return "Message Published on Kafka !";
    }

}
