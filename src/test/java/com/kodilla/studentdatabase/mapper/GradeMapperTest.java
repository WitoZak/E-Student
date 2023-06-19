package com.kodilla.studentdatabase.mapper;

import com.kodilla.studentdatabase.domain.Grade;
import com.kodilla.studentdatabase.domain.Subject;
import com.kodilla.studentdatabase.domain.Teacher;
import com.kodilla.studentdatabase.domain.Student;
import com.kodilla.studentdatabase.domain.GradeDto;
import com.kodilla.studentdatabase.exceptions.SubjectNotFoundException;
import com.kodilla.studentdatabase.exceptions.TeacherNotFoundException;
import com.kodilla.studentdatabase.exceptions.StudentNotFoundException;
import com.kodilla.studentdatabase.service.SubjectService;
import com.kodilla.studentdatabase.service.TeacherService;
import com.kodilla.studentdatabase.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class GradeMapperTest {

    private GradeMapper gradeMapper;

    @Mock
    private SubjectService subjectService;

    @Mock
    private TeacherService teacherService;

    @Mock
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        gradeMapper = new GradeMapper(subjectService, teacherService, studentService);
    }

    @Test
    void testMapToGrade() throws SubjectNotFoundException, TeacherNotFoundException, StudentNotFoundException {
        // Given
        GradeDto gradeDto = new GradeDto();
        gradeDto.setId(1L);
        gradeDto.setValue("4.5");
        gradeDto.setGradeTimestamp(LocalDateTime.of(2023, 5, 31, 0, 0));
        gradeDto.setSubjectName("Math");
        gradeDto.setLastName("Smith");
        gradeDto.setStudentLastName("Johnson");

        Subject subject = new Subject();
        subject.setSubjectName("Math");

        Teacher teacher = new Teacher();
        teacher.setLastName("Smith");

        Student student = new Student();
        student.setLastName("Johnson");

        Mockito.when(subjectService.getSubjectByName("Math"))
                .thenReturn(subject);
        Mockito.when(teacherService.getTeacherByLastName("Smith"))
                .thenReturn(teacher);
        Mockito.when(studentService.getStudentByLastName("Johnson"))
                .thenReturn(student);

        // When
        Grade grade = gradeMapper.mapToGrade(gradeDto);

        // Then
        assertNotNull(grade);
        assertEquals(1L, grade.getId());
        assertEquals("4.5", grade.getValue());
        assertEquals("2023-05-31", grade.getGradeTimestamp().toLocalDate().toString());
        assertEquals(subject, grade.getSubject());
        assertEquals(teacher, grade.getTeacher());
        assertEquals(student, grade.getStudent());
    }

    @Test
    void testMapToGradeDto() {
        // Given
        Subject subject = new Subject();
        subject.setSubjectName("Math");

        Teacher teacher = new Teacher();
        teacher.setLastName("Smith");

        Student student = new Student();
        student.setLastName("Johnson");

        Grade grade = new Grade();
        grade.setId(1L);
        grade.setValue("4.5");
        grade.setGradeTimestamp(LocalDateTime.of(2023, 5, 31, 0, 0));
        grade.setSubject(subject);
        grade.setTeacher(teacher);
        grade.setStudent(student);

        // When
        GradeDto gradeDto = gradeMapper.mapToGradeDto(grade);

        // Then
        assertNotNull(gradeDto);
        assertEquals(1L, gradeDto.getId());
        assertEquals("4.5", gradeDto.getValue());
        assertEquals("2023-05-31", gradeDto.getGradeTimestamp().toLocalDate().toString());
        assertEquals("Math", gradeDto.getSubjectName());
        assertEquals("Smith", gradeDto.getLastName());
        assertEquals("Johnson", gradeDto.getStudentLastName());
    }

    @Test
    void testMapToGradeDtoList() {
        // Given
        Subject subject = new Subject();
        subject.setSubjectName("Math");

        Teacher teacher = new Teacher();
        teacher.setLastName("Smith");

        Student student = new Student();
        student.setLastName("Johnson");

        Grade grade = new Grade();
        grade.setId(1L);
        grade.setValue("4.5");
        grade.setGradeTimestamp(LocalDateTime.of(2023, 5, 31, 0, 0));
        grade.setSubject(subject);
        grade.setTeacher(teacher);
        grade.setStudent(student);

        List<Grade> listOfGrades = Collections.singletonList(grade);

        // When
        List<GradeDto> listOfGradeDtos = gradeMapper.mapToGradeDtoList(listOfGrades);

        // Then
        assertNotNull(listOfGradeDtos);
        assertEquals(1, listOfGradeDtos.size());

        GradeDto gradeDto = listOfGradeDtos.get(0);
        assertEquals(1L, gradeDto.getId());
        assertEquals("4.5", gradeDto.getValue());
        assertEquals("2023-05-31", gradeDto.getGradeTimestamp().toLocalDate().toString());
        assertEquals("Math", gradeDto.getSubjectName());
        assertEquals("Smith", gradeDto.getLastName());
        assertEquals("Johnson", gradeDto.getStudentLastName());
    }

    @Test
    void testMapToGradeList() throws SubjectNotFoundException, TeacherNotFoundException, StudentNotFoundException {
        // Given
        GradeDto gradeDto = new GradeDto();
        gradeDto.setId(1L);
        gradeDto.setValue("4.5");
        gradeDto.setGradeTimestamp(LocalDateTime.of(2023, 5, 31, 0, 0));
        gradeDto.setSubjectName("Math");
        gradeDto.setLastName("Smith");
        gradeDto.setStudentLastName("Johnson");

        Subject subject = new Subject();
        subject.setSubjectName("Math");

        Teacher teacher = new Teacher();
        teacher.setLastName("Smith");

        Student student = new Student();
        student.setLastName("Johnson");

        Mockito.when(subjectService.getSubjectByName("Math"))
                .thenReturn(subject);
        Mockito.when(teacherService.getTeacherByLastName("Smith"))
                .thenReturn(teacher);
        Mockito.when(studentService.getStudentByLastName("Johnson"))
                .thenReturn(student);

        List<GradeDto> listOfGradeDtos = Collections.singletonList(gradeDto);

        // When
        List<Grade> listOfGrades = gradeMapper.mapToGradeList(listOfGradeDtos);

        // Then
        assertNotNull(listOfGrades);
        assertEquals(1, listOfGrades.size());

        Grade grade = listOfGrades.get(0);
        assertEquals(1L, grade.getId());
        assertEquals("4.5", grade.getValue());
        assertEquals("2023-05-31", grade.getGradeTimestamp().toLocalDate().toString());
        assertEquals(subject, grade.getSubject());
        assertEquals(teacher, grade.getTeacher());
        assertEquals(student, grade.getStudent());
    }
}
