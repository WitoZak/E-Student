package com.kodilla.studentdatabase.mapper;

import com.kodilla.studentdatabase.domain.*;
import com.kodilla.studentdatabase.exceptions.GradeNotFoundException;
import com.kodilla.studentdatabase.exceptions.GroupNotFoundException;
import com.kodilla.studentdatabase.exceptions.SubjectNotFoundException;
import com.kodilla.studentdatabase.exceptions.TeacherNotFoundException;
import com.kodilla.studentdatabase.service.GroupService;
import com.kodilla.studentdatabase.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentMapper {

    private final GroupService groupService;
    private final GradeMapper gradeMapper;
    private final SubjectService subjectService;

    public Student mapToStudent(final StudentDto studentDto) throws GroupNotFoundException, GradeNotFoundException, SubjectNotFoundException, TeacherNotFoundException {
        List<Grade> grades = studentDto.getGradesDtos() != null ? gradeMapper.mapToGradeList(studentDto.getGradesDtos()) : null;
        Subject subjects = studentDto.getSubjectId() != null ? subjectService.getSubject(studentDto.getSubjectId()) : null;
        Group groups = studentDto.getGroupId() != null ? groupService.getGroup(studentDto.getGroupId()) : null;

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
                subjects,
                groups
        );
    }

    public StudentDto mapToStudentDto(final Student student) {
        List<GradeDto> gradesDtos = student.getGrades() != null ? gradeMapper.mapToGradeDtoList(student.getGrades()) : null;

        return new StudentDto(
                student.getId(),
                student.getLogNumber(),
                student.getFirstName(),
                student.getLastName(),
                student.getDateOfBirth(),
                student.getAddress(),
                student.getStudentMail(),
                student.getStudentPhone(),
                gradesDtos,
                student.getSubject().getId(),
                student.getSubject().getSubjectName(),
                student.getGroup().getId(),
                student.getGroup().getClassName()
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
                    } catch (GroupNotFoundException | GradeNotFoundException | SubjectNotFoundException |
                             TeacherNotFoundException e) {
                        throw new RuntimeException("Group not found", e);
                    }
                })
                .toList();
    }

}
