package com.example.kafkaproducer.controller;

import com.example.kafkaproducer.entity.Message;
import com.example.kafkaproducer.producer.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TestController {

    @Autowired
    private ProducerService producerService;

    @PostMapping("/produce")
    public ResponseEntity<?> produce(@RequestBody Message message) {
        producerService.produce(message.getBody());
        return ResponseEntity.ok().build();
    }
}
