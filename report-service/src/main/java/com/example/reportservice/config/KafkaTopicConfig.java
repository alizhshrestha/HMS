package com.example.reportservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    public static String topicName = "account";

    @Bean
    public NewTopic accountsTopic(){
        return TopicBuilder.name(topicName)
                .build();
    }
}
