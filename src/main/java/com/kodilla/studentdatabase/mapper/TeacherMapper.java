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

    public Teacher mapToTeacher(final TeacherDto teacherDto) {

        return new Teacher(
                teacherDto.getId(),
                teacherDto.getFirstName(),
                teacherDto.getLastName(),
                teacherDto.getMail(),
                teacherDto.getPhone(),
                null,
                null
        );
    }

    public TeacherDto mapToTeacherDto(final Teacher teacher) {
        return new TeacherDto(
                teacher.getId(),
                teacher.getFirstName(),
                teacher.getLastName(),
                teacher.getMail(),
                teacher.getPhone()
        );
    }

    public List<TeacherDto> mapToTeachDtoList(final List<Teacher> listOfTeachers) {
        return listOfTeachers.stream()
                .map(this::mapToTeacherDto)
                .toList();
    }
}
