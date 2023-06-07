package com.kodilla.studentdatabase.mapper;

import com.kodilla.studentdatabase.domain.*;
import com.kodilla.studentdatabase.exceptions.TeacherNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherMapper {

    private final GradeMapper gradeMapper;
    private final SubjectMapper subjectMapper;

    public Teacher mapToTeacher(final TeacherDto teacherDto) throws TeacherNotFoundException {
        List<Subject> subjects = teacherDto.getSubjectsDtos() != null ? subjectMapper.mapToSubjectList(teacherDto.getSubjectsDtos()) : null;
        List<Grade> grades = teacherDto.getGradesDtos() != null ? gradeMapper.mapToGradeList(teacherDto.getGradesDtos()) : null;
        return new Teacher(
                teacherDto.getId(),
                teacherDto.getFirstName(),
                teacherDto.getLastName(),
                teacherDto.getMail(),
                teacherDto.getPhone(),
                subjects,
                grades
        );
    }

    public TeacherDto mapToTeacherDto(final Teacher teacher) {
        List<GradeDto> gradesDtos = teacher.getGrades() != null ? gradeMapper.mapToGradeDtoList(teacher.getGrades()) : null;
        List<SubjectDto> subjectDtos = teacher.getSubjects() != null ? subjectMapper.mapToSubjectDtoList(teacher.getSubjects()) : null;
        return new TeacherDto(
                teacher.getId(),
                teacher.getFirstName(),
                teacher.getLastName(),
                teacher.getMail(),
                teacher.getPhone(),
                subjectDtos,
                gradesDtos
        );
    }

    public List<TeacherDto> mapToTeachDtoList(final List<Teacher> listOfTeachers) {
        return listOfTeachers.stream()
                .map(this::mapToTeacherDto)
                .toList();
    }
}
