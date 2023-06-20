package com.kodilla.studentdatabase.mapper;

import com.kodilla.studentdatabase.domain.Logging;
import com.kodilla.studentdatabase.domain.LoggingDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoggingMapper {

    public Logging mapToLogging(final LoggingDto loggingDto) {
        return new Logging(
                loggingDto.getId(),
                loggingDto.getUsername(),
                loggingDto.getPassword(),
                loggingDto.getRole(),
                loggingDto.getCreationTimestamp()

        );
    }

    public LoggingDto mapToLoggingDto(final Logging logging) {
        return new LoggingDto(
                logging.getId(),
                logging.getUsername(),
                logging.getPassword(),
                logging.getRole(),
                logging.getCreationTimestamp()
                );
    }

    public List<LoggingDto> mapToLoggingDtoList(final List<Logging> listOfLoggings) {
        return listOfLoggings.stream()
                .map(this::mapToLoggingDto)
                .toList();
    }
}
