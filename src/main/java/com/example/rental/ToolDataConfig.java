package com.example.rental;

import java.io.IOException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ToolDataConfig {
    @Bean
    public ToolData toolData() throws IOException {
        return new ToolData();
    }
}
