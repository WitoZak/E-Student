package com.kodilla.studentdatabase.mapper;

import com.kodilla.studentdatabase.domain.Grade;
import com.kodilla.studentdatabase.domain.Group;
import com.kodilla.studentdatabase.domain.Student;
import com.kodilla.studentdatabase.domain.StudentDto;
import com.kodilla.studentdatabase.exceptions.GroupNotFoundException;
import com.kodilla.studentdatabase.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentMapper {

    private final GroupService groupService;
    private final GradeMapper gradeMapper;


    public Student mapToStudent(final StudentDto studentDto) throws GroupNotFoundException {
        List<Grade> grades = studentDto.getGradesDtos() != null ? gradeMapper.mapToGradeList(studentDto.getGradesDtos()) : null;
        Group group = studentDto.getGroupId() != null ? groupService.getGroup(studentDto.getGroupId()) : null;

        return new Student(
                studentDto.getId(),
                studentDto.getLogNumber(),
                studentDto.getFirstName(),
                studentDto.getLastName(),
                studentDto.getDateOfBirth(),
                studentDto.getAddress(),
                studentDto.getStudentMail(),
                studentDto.getStudentPhone(),
                grades,
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
                student.getStudentMail(),
                student.getStudentPhone(),
                gradeMapper.mapToGradeDtoList(student.getGrades()),
                student.getGroup().getId()
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
                    } catch (GroupNotFoundException e) {
                        throw new RuntimeException("Group not found", e);
                    }
                })
                .toList();
    }

}
