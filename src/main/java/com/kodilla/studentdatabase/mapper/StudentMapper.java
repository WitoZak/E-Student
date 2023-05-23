package com.kodilla.studentdatabase.mapper;

import com.kodilla.studentdatabase.domain.Student;
import com.kodilla.studentdatabase.domain.StudentDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentMapper {

    public Student mapToStudent(final StudentDTO studentDTO) {
        return new Student(
                studentDTO.getId(),
                studentDTO.getLogNumber(),
                studentDTO.getFirstName(),
                studentDTO.getLastName(),
                studentDTO.getDateOfBirth(),
                studentDTO.getAddress(),
                studentDTO.getStudentMail(),
                studentDTO.getStudentPhone()
        );
    }

    public StudentDTO mapToStudentDTO(final Student student) {
        return new StudentDTO(
                student.getId(),
                student.getLogNumber(),
                student.getFirstName(),
                student.getLastName(),
                student.getDateOfBirth(),
                student.getAddress(),
                student.getStudentMail(),
                student.getStudentPhone()
        );
    }

    public List<StudentDTO> mapToStudentDTOList(final List<Student> listOfStudents) {
        return listOfStudents.stream()
                .map(this::mapToStudentDTO)
                .toList();
    }
}
