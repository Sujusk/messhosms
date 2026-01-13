package com.example.demo.controllers;
import com.example.demo.entity.MessageRequest;
import com.example.demo.messageservice.KafkaProducerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/kafka")
public class KafkaController {

    private final KafkaProducerService producerService;

    public KafkaController(KafkaProducerService producerService) {
        this.producerService = producerService;
    }

    @PostMapping("/send")
    public String pushMessage(@RequestBody MessageRequest request) {
        producerService.sendMessage(request);
        return "Message queued for Kafka!";
    }
}