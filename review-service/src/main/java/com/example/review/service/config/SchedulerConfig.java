package com.example.review.service.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

@Configuration
@EnableAutoConfiguration
@ComponentScan("com.example")
@Slf4j
public class SchedulerConfig {

    @Value("${app.threadPoolSize:10}")
    private Integer threadPoolSize;
    @Value("${app.taskQueueSize:100}")
    private Integer taskQueueSize;
    @Bean
    public Scheduler jdbcScheduler() {
        log.info("Creates a jdbcScheduler with thread pool size = {}", threadPoolSize);
        return Schedulers.newBoundedElastic(threadPoolSize, taskQueueSize, "jdbc-pool");
    }
}
