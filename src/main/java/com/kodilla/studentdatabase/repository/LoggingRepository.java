package com.kodilla.studentdatabase.repository;

import com.kodilla.studentdatabase.domain.Logging;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoggingRepository extends CrudRepository<Logging, Long> {

    List<Logging> findAll();
}
