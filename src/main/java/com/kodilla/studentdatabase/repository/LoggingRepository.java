package com.kodilla.studentdatabase.repository;

import com.kodilla.studentdatabase.domain.Logging;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoggingRepository extends CrudRepository<Logging, Long> {
}
