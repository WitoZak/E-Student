package com.kodilla.studentdatabase.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoggingDto {

    private Long id;
    private String username;
    private String password;
    private String role;
    private LocalDateTime creationTimestamp;

}
