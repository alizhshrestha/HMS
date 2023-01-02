package com.icodify.multitenant.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.icodify.multitenant.config.messagequeue.KafkaTopicConfig;
import com.icodify.multitenant.model.entities.Account;
import com.icodify.multitenant.repository.AccountRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/report/messages")
public class MessageController {

    private KafkaTemplate<String, String> kafkaTemplate;
    private AccountRepository accountRepository;
    private ObjectMapper objectMapper;

    public MessageController(KafkaTemplate<String, String> kafkaTemplate,
                             AccountRepository accountRepository,
                             ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.accountRepository = accountRepository;
        this.objectMapper = objectMapper;
    }


    //    @PostMapping
//    public void publish(@RequestBody MessageRequest request){
//        kafkaTemplate.send(KafkaTopicConfig.topicName, request.getMessage());
//    }

    @PostMapping
    public void publish() throws JsonProcessingException {

        List<Account> accounts = accountRepository.findAll();

//        ObjectMapper objectMapper = new ObjectMapper();
        String accJson = objectMapper.writeValueAsString(accounts);

        kafkaTemplate.send(KafkaTopicConfig.topicName, accJson);
    }
}
