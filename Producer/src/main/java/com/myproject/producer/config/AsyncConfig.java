package com.myproject.producer.config;


import java.util.concurrent.Executor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@EnableAsync
@Configuration
@RequiredArgsConstructor
public class AsyncConfig {

    private final ThreadConfig threadConfig;

    @Bean("asyncTransactionExecutor")
    public Executor asyncTransactionExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(threadConfig.getCorePoolSize());
        executor.setMaxPoolSize(threadConfig.getMaxPoolSize());
        executor.setQueueCapacity(threadConfig.getQueueCapacity());
        executor.setThreadNamePrefix("async-thread-");
        executor.initialize();
        return executor;
    }

}
