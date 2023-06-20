package com.kodilla.studentdatabase.service;

import com.kodilla.studentdatabase.domain.Logging;
import com.kodilla.studentdatabase.repository.LoggingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoggingService {

    private final LoggingRepository loggingRepository;

    public List<Logging> getAllLoggings() {
        return loggingRepository.findAll();
    }

    public Logging getLoggingById(final Long id) {
        return loggingRepository.findById(id).orElseThrow();
    }

    public Logging createLogging(final Logging newLogging) {
        return loggingRepository.save(newLogging);
    }

    public Logging updateLogging(Logging updatedLogging) {
        Long id = updatedLogging.getId();
        if (loggingRepository.findById(id).isPresent()) {
            Logging logging = new Logging(
                    id,
                    updatedLogging.getUsername(),
                    updatedLogging.getPassword(),
                    updatedLogging.getRole(),
                    updatedLogging.getCreationTimestamp()
            );
            return loggingRepository.save(logging);
        } else {
            throw new IllegalArgumentException("Not found id=" + id);
        }
    }

    public void deleteLogging(final Long id) {
        loggingRepository.deleteById(id);
    }
}
