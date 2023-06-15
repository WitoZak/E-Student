package com.kodilla.studentdatabase.mapper;

import com.kodilla.studentdatabase.domain.*;
import com.kodilla.studentdatabase.exceptions.TeacherNotFoundException;
import com.kodilla.studentdatabase.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectMapper {

    private final GradeMapper gradeMapper;
    private final StudentMapper studentMapper;
    private final TeacherService teacherService;


    public Subject mapToSubject(final SubjectDto subjectDto) throws TeacherNotFoundException {
        List<Grade> grades = subjectDto.getGradesDtos() != null ? gradeMapper.mapToGradeList(subjectDto.getGradesDtos()) : null;
        List<Student> students = subjectDto.getStudentsDtos() != null ? studentMapper.mapToStudentList(subjectDto.getStudentsDtos()) : null;
        Teacher teachers = subjectDto.getTeacherId() != null ? teacherService.getTeacher(subjectDto.getTeacherId()) : null;

        return new Subject(
                subjectDto.getId(),
                subjectDto.getSubjectName(),
                grades,
                students,
                teachers
        );
    }

    public SubjectDto mapToSubjectDto(final Subject subject) {
        List<GradeDto> gradesDtos = subject.getGrades() != null ? gradeMapper.mapToGradeDtoList(subject.getGrades()) : null;
        List<StudentDto> studentsDtos = subject.getStudents() != null ? studentMapper.mapToStudentDtoList(subject.getStudents()) : null;
        return new SubjectDto(
                subject.getId(),
                subject.getSubjectName(),
                gradesDtos,
                studentsDtos,
                subject.getTeacher().getId()
        );
    }

    public List<SubjectDto> mapToSubjectDtoList(final List<Subject> listOfSubjects) {
        return listOfSubjects.stream()
                .map(this::mapToSubjectDto)
                .toList();
    }

    public List<Subject> mapToSubjectList(final List<SubjectDto> listOfSubjectsDtos) {
        List<Subject> subjects = new ArrayList<>();

        for (SubjectDto subjectDto : listOfSubjectsDtos) {
            try {
                Subject subject = mapToSubject(subjectDto);
                subjects.add(subject);
            } catch (TeacherNotFoundException e) {
            }
        }

        return subjects;
    }
}
