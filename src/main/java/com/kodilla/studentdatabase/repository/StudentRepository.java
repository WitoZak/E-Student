package com.kodilla.studentdatabase.repository;

import com.kodilla.studentdatabase.domain.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository <Student, Long> {

    List<Student> findAll();
}
