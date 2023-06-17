package com.kodilla.studentdatabase.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class WeatcherConfig {

    @Value("${weather.api.key}")
    private String ApiKey;

}
