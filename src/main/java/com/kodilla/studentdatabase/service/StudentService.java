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

    public Student updateStudent(Student updatedStudent) throws StudentNotFoundException {
        Long id = updatedStudent.getId();
        if (studentRepo.findById(id).isPresent()) {
            Student student = new Student(
                    id,
                    updatedStudent.getLogNumber(),
                    updatedStudent.getFirstName(),
                    updatedStudent.getLastName(),
                    updatedStudent.getDateOfBirth(),
                    updatedStudent.getAddress(),
                    updatedStudent.getStudentMail(),
                    updatedStudent.getStudentPhone(),
                    updatedStudent.getGrades(),
                    updatedStudent.getSubjects(),
                    updatedStudent.getGroup()
            );
            return studentRepo.save(student);
        } else {
            throw new StudentNotFoundException();
        }
    }

    public void deleteStudent(final Long id) {
        studentRepo.deleteById(id);
    }
}
