package com.kodilla.studentdatabase.mapper;

import com.kodilla.studentdatabase.domain.*;
import com.kodilla.studentdatabase.exceptions.StudentNotFoundException;
import com.kodilla.studentdatabase.exceptions.SubjectNotFoundException;
import com.kodilla.studentdatabase.exceptions.TeacherNotFoundException;
import com.kodilla.studentdatabase.service.StudentService;
import com.kodilla.studentdatabase.service.SubjectService;
import com.kodilla.studentdatabase.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class GradeMapper {

    private final SubjectService subjectService;
    private final TeacherService teacherService;
    private final StudentService studentService;

    public Grade mapToGrade(final GradeDto gradeDto) throws SubjectNotFoundException, TeacherNotFoundException, StudentNotFoundException {
        Subject subject = null;
        Teacher teacher = null;
        Student student = null;

        if (gradeDto.getSubjectName() != null) {
            subject = subjectService.getSubjectByName(gradeDto.getSubjectName());
        }

        if (gradeDto.getLastName() != null) {
            teacher = teacherService.getTeacherByLastName(gradeDto.getLastName());
        }

        if (gradeDto.getStudentLastName() != null) {
            student = studentService.getStudentByLastName(gradeDto.getStudentLastName());
        }

        return new Grade(
                gradeDto.getId(),
                gradeDto.getValue(),
                gradeDto.getGradeTimestamp(),
                subject,
                teacher,
                student);
    }

    public GradeDto mapToGradeDto(final Grade grade) {
        return new GradeDto(
                grade.getId(),
                grade.getValue(),
                grade.getGradeTimestamp(),
                grade.getSubject().getSubjectName(),
                grade.getTeacher().getLastName(),
                grade.getStudent().getLastName()
        );
    }

    public List<GradeDto> mapToGradeDtoList(final List<Grade> listOfGrades) {
        return listOfGrades.stream()
                .map(this::mapToGradeDto)
                .toList();
    }

    public List<Grade> mapToGradeList(final List<GradeDto> listOfGradesDtos) {
        return listOfGradesDtos.stream()
                .map(gradeDto -> {
                    try {
                        return mapToGrade(gradeDto);
                    } catch (SubjectNotFoundException | TeacherNotFoundException | StudentNotFoundException e) {
                        throw new RuntimeException("Subject, Teacher or Student were not found", e);
                    }
                })
                .filter(Objects::nonNull)
                .toList();
    }

}
