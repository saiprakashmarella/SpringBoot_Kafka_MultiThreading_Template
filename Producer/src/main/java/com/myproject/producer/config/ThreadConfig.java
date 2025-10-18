package com.myproject.producer.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "transactions.threads")
@Data
public class ThreadConfig {

    private int corePoolSize;
    private int maxPoolSize;
    private int queueCapacity;
    private String namePrefix;
}
