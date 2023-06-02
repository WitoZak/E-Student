package com.kodilla.studentdatabase.domain;

import com.kodilla.studentdatabase.domain.Mail;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MailTest {

    @Test
    void shouldCreateMail() {
        // Given
        String mailTo = "john.doe@example.com";
        String subject = "Test Email";
        String message = "This is a test email.";
        String toCc = "jane.smith@example.com";

        // When
        Mail mail = new Mail(mailTo, subject, message, toCc);

        // Then
        assertEquals(mailTo, mail.getMailTo());
        assertEquals(subject, mail.getSubject());
        assertEquals(message, mail.getMessage());
        assertEquals(toCc, mail.getToCc());
    }
}
