package com.kodilla.studentdatabase.service;

import com.kodilla.studentdatabase.domain.Student;
import com.kodilla.studentdatabase.domain.StudentDto;
import com.kodilla.studentdatabase.exceptions.StudentNotFoundException;
import com.kodilla.studentdatabase.mapper.StudentMapper;
import com.kodilla.studentdatabase.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepo;
    private final StudentMapper studentMapper;

    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    public List<StudentDto> findAllStudentsDto() {
        List<Student> students = studentRepo.findAll();
        return studentMapper.mapToStudentDtoList(students);
    }
    public Student getStudent(final Long id) throws StudentNotFoundException {
        return studentRepo.findById(id).orElseThrow(StudentNotFoundException::new);
    }

    public Student getStudentByLastName(String lastName) throws StudentNotFoundException {
        Optional<Student> optionalStudent = Optional.ofNullable(studentRepo.findByLastName(lastName));
        return optionalStudent.orElseThrow(StudentNotFoundException::new);
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
                    updatedStudent.getMail(),
                    updatedStudent.getPhone(),
                    updatedStudent.getGrades(),
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
