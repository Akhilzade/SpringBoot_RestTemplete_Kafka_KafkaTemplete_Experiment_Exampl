package com.kafka.services;


import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaServices {

//       @Autowired
//        MessageRepo messageRepo;

        @KafkaListener(topics = "testProducer1",
                groupId = "kafkaUser")
        public String publish(String message)
        {
            consumedMessage(message);
            return message;
        }


        public void consumedMessage(String message){
            log.info("Message is::{}",message);
        }
}
