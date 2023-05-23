package com.kodilla.studentdatabase.repository;

import com.kodilla.studentdatabase.domain.Grade;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeRepository extends CrudRepository<Grade, Long> {
}
