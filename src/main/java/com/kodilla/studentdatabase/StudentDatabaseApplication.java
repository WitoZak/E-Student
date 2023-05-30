package com.kodilla.studentdatabase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class StudentDatabaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentDatabaseApplication.class, args);
    }

}
