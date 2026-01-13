package com.example.demo.messageservice;



import com.example.demo.entity.MessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
public class KafkaProducerService {
    @Autowired
    private blockeduserservice blockeduserservice;
    @Autowired
    private sendmessages sendmessages;
    @Value("${app.kafka.topic}")
    private String topic;

    private final KafkaTemplate<String, MessageRequest> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, MessageRequest> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(MessageRequest request) {
        System.out.println("Sending message to Kafka: " + request.getContent());

        if (blockeduserservice.blockeduserservice(request.getNumber())) {
            System.out.println("Blocked user");
            return;
        }
        try {
            sendmessages.sendmessage(request.getContent(), request.getNumber());
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        kafkaTemplate.send(topic, request);
    }
}