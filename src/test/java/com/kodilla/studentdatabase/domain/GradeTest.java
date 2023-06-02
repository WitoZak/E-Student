package com.kodilla.studentdatabase.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class GradeTest {

    @Test
    void shouldCreateGrade() {

        // Given
        Long id = 1L;
        String value = "A";
        LocalDateTime gradeTimestamp = LocalDateTime.now();
        Subject subject = new Subject();
        Teacher teacher = new Teacher();
        Student student = new Student();

        // When
        Grade grade = new Grade(id, value, gradeTimestamp, subject, teacher, student);

        // Then
        assertEquals(id, grade.getId());
        assertEquals(value, grade.getValue());
        assertEquals(gradeTimestamp, grade.getGradeTimestamp());
        assertEquals(subject, grade.getSubject());
        assertEquals(teacher, grade.getTeacher());
        assertEquals(student, grade.getStudent());
    }
}
