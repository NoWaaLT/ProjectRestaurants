package com.orioninc.ProjectRestaurants.kafka.consumer;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    private static final Logger log = LogManager.getLogger(MessageConsumer.class);

    @KafkaListener(topics = "my-topic", groupId = "consumers_1")
    public void listen(String message) {
        log.info("Received message: {}", message);
    }

}