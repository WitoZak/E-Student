package com.kodilla.studentdatabase.repository;

import com.kodilla.studentdatabase.domain.Subject;
import com.kodilla.studentdatabase.domain.Teacher;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends CrudRepository<Subject, Long> {

    List<Subject> findAll();

    Subject findBySubjectName(String subjectName);

}
