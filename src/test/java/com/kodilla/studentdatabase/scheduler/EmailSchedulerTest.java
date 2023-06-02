package com.kodilla.studentdatabase.scheduler;

import com.kodilla.studentdatabase.domain.Mail;
import com.kodilla.studentdatabase.domain.Student;
import com.kodilla.studentdatabase.repository.StudentRepository;
import com.kodilla.studentdatabase.service.mail.SimpleEmailService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmailSchedulerTest {

    @Mock
    private SimpleEmailService simpleEmailService;

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private EmailScheduler emailScheduler;

    @Test
    void testSendInformationEmail() {
        Student student1 = Student.builder()
                .firstName("John")
                .lastName("Doe")
                .mail("john.doe@example.com")
                .build();

        Student student2 = Student.builder()
                .firstName("Jane")
                .lastName("Smith")
                .mail("jane.smith@example.com")
                .build();

        when(studentRepository.findAll()).thenReturn(Arrays.asList(student1, student2));

        emailScheduler.sendInformationEmail();

        ArgumentCaptor<Mail> mailCaptor = ArgumentCaptor.forClass(Mail.class);
        verify(simpleEmailService, times(2)).send(mailCaptor.capture());

        List<Mail> sentMails = mailCaptor.getAllValues();
        assertEquals(2, sentMails.size());

        for (int i = 0; i < sentMails.size(); i++) {
            Mail mail = sentMails.get(i);
            Student student = (i == 0) ? student1 : student2;

            assertEquals(student.getMail(), mail.getMailTo());
            assertEquals(EmailScheduler.SUBJECT, mail.getSubject());
            assertTrue(mail.getMessage().contains("Today's lucky number is"));
        }
    }
}