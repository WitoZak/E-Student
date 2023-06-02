package com.kodilla.studentdatabase.repository;

import com.kodilla.studentdatabase.domain.Grade;
import com.kodilla.studentdatabase.domain.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepository extends CrudRepository<Grade, Long> {

    List<Grade> findAll();

}
