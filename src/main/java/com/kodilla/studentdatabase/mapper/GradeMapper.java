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
        Subject subject = gradeDto.getSubjectId() != null ? subjectService.getSubject(gradeDto.getSubjectId()) : null;
        Teacher teacher = gradeDto.getTeacherId() != null ? teacherService.getTeacher(gradeDto.getTeacherId()) : null;
        Student student = gradeDto.getStudentId() != null ? studentService.getStudent(gradeDto.getStudentId()) : null;
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
                grade.getSubject().getId(),
                grade.getSubject().getSubjectName(),
                grade.getTeacher().getId(),
                grade.getTeacher().getLastName(),
                grade.getStudent().getId()
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
