package edu.neuralfintech.market_data_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Configuration
public class AppConfig {
    @Bean
    public Random random() {
        return ThreadLocalRandom.current();
    }
}
