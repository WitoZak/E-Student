package com.kodilla.studentdatabase.mapper;

import com.kodilla.studentdatabase.domain.Student;
import com.kodilla.studentdatabase.domain.StudentDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentMapper {

    public Student mapToStudent(final StudentDto studentDto) {
        return new Student(
                studentDto.getId(),
                studentDto.getLogNumber(),
                studentDto.getFirstName(),
                studentDto.getLastName(),
                studentDto.getDateOfBirth(),
                studentDto.getAddress(),
                studentDto.getStudentMail(),
                studentDto.getStudentPhone(),
                null,
                null,
                null,
                null
        );
    }

    public StudentDto mapToStudentDto(final Student student) {
        return new StudentDto(
                student.getId(),
                student.getLogNumber(),
                student.getFirstName(),
                student.getLastName(),
                student.getDateOfBirth(),
                student.getAddress(),
                student.getStudentMail(),
                student.getStudentPhone(),
                null,
                null,
                null,
                null
        );
    }

    public List<StudentDto> mapToStudentDtoList(final List<Student> listOfStudents) {
        return listOfStudents.stream()
                .map(this::mapToStudentDto)
                .toList();
    }
}
