package com.kodilla.studentdatabase.scheduler;

import com.kodilla.studentdatabase.domain.Mail;
import com.kodilla.studentdatabase.domain.Student;
import com.kodilla.studentdatabase.repository.StudentRepository;
import com.kodilla.studentdatabase.service.mail.SimpleEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EmailScheduler {

    public static final String SUBJECT = "Lucky number: Once a day email";

    private final SimpleEmailService simpleEmailService;
    private final StudentRepository studentRepository;

    @Scheduled(cron = "0 0 5 * * MON-FRI")
    public void sendInformationEmail() {

        List<String> studentEmails = studentRepository.findAll().stream()
                .map(Student::getMail)
                .toList();

        for (String email : studentEmails) {
            simpleEmailService.send(
                    new Mail(
                            email,
                            SUBJECT,
                            getMessage(),
                            null
                    )
            );
        }
    }

    String getMessage() {
        int number = (int) (Math.random() * 30) + 1;
        return "Today's lucky number is " + number + ". To all the winners, may the force be with you!";
    }
}