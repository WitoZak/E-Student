package com.kodilla.studentdatabase.service;

import com.kodilla.studentdatabase.domain.Student;
import com.kodilla.studentdatabase.exceptions.StudentNotFoundException;
import com.kodilla.studentdatabase.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepo;

    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    public Student getStudent(final Long id) throws StudentNotFoundException {
        return studentRepo.findById(id).orElseThrow(StudentNotFoundException::new);
    }

    public Student saveStudent(final Student newStudent) {
        return studentRepo.save(newStudent);
    }

    public void deleteStudent(final Long id) throws StudentNotFoundException {
        studentRepo.deleteById(id);
    }
}
