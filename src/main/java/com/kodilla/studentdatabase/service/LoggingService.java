package com.kodilla.studentdatabase.service;

import com.kodilla.studentdatabase.domain.Logging;
import com.kodilla.studentdatabase.domain.Student;
import com.kodilla.studentdatabase.exceptions.LoggingException;
import com.kodilla.studentdatabase.exceptions.StudentNotFoundException;
import com.kodilla.studentdatabase.repository.LoggingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoggingService {

    private final LoggingRepository loggingRepository;

    public List<Logging> getAllStudents() {
        return loggingRepository.findAll();
    }

    public Logging getStudent(final Long id) {
        return loggingRepository.findById(id).orElseThrow();
    }

    public Logging saveStudent(final Logging newLogging) {
        return loggingRepository.save(newLogging);
    }

    public void deleteStudent(final Long id) {
        loggingRepository.deleteById(id);
    }
}
