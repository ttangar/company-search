package com.company.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication(scanBasePackages = "com.company.search")
@EntityScan(basePackages = "com.company.search.model")
public class CompanySearchApplication {

    @Bean
    public WebClient webClient() {
        return WebClient.create();
    }

    public static void main(String[] args) {
        SpringApplication.run(CompanySearchApplication.class, args);
    }

}
