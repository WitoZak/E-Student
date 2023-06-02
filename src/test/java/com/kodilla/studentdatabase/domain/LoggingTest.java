package com.kodilla.studentdatabase.domain;

import com.kodilla.studentdatabase.domain.Logging;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LoggingTest {

    @Test
    void shouldCreateLogging() {
        // Given
        Long id = 1L;
        String username = "john.doe";
        String password = "password123";
        String role = "user";
        LocalDateTime logTimestamp = LocalDateTime.now();

        // When
        Logging logging = new Logging(id, username, password, role, logTimestamp);

        // Then
        assertEquals(id, logging.getId());
        assertEquals(username, logging.getUsername());
        assertEquals(password, logging.getPassword());
        assertEquals(logTimestamp, logging.getCreationTimestamp());
    }
}
