package com.kodilla.studentdatabase.mapper;

import com.kodilla.studentdatabase.domain.*;
import com.kodilla.studentdatabase.exceptions.GradeNotFoundException;
import com.kodilla.studentdatabase.exceptions.GroupNotFoundException;
import com.kodilla.studentdatabase.exceptions.SubjectNotFoundException;
import com.kodilla.studentdatabase.exceptions.TeacherNotFoundException;
import com.kodilla.studentdatabase.service.GradeService;
import com.kodilla.studentdatabase.service.GroupService;
import com.kodilla.studentdatabase.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentMapper {

    private final GroupService groupService;
    public Student mapToStudent(final StudentDto studentDto) throws  GradeNotFoundException, TeacherNotFoundException {
        Group group = groupService.getGroupByName(studentDto.getGroupName());
        return new Student(
                studentDto.getId(),
                studentDto.getLogNumber(),
                studentDto.getFirstName(),
                studentDto.getStudentLastName(),
                studentDto.getDateOfBirth(),
                studentDto.getAddress(),
                studentDto.getMail(),
                studentDto.getPhone(),
                null,
                group
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
                student.getMail(),
                student.getPhone(),
                student.getGroup().getGroupName()
        );
    }

    public List<StudentDto> mapToStudentDtoList(final List<Student> listOfStudents) {
        return listOfStudents.stream()
                .map(this::mapToStudentDto)
                .toList();
    }

    public List<Student> mapToStudentList(final List<StudentDto> listOfStudentDtos) {
        return listOfStudentDtos.stream()
                .map(studentDto -> {
                    try {
                        return mapToStudent(studentDto);
                    } catch (GradeNotFoundException | TeacherNotFoundException e) {
                        throw new RuntimeException("Group not found", e);
                    }
                })
                .toList();
    }

}
