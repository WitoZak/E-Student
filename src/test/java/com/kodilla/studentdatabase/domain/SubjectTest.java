package com.kodilla.studentdatabase.domain;

import com.kodilla.studentdatabase.domain.Subject;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SubjectTest {

    @Test
    void shouldCreateSubject() {
        // Given
        Long id = 1L;
        String subjectName = "Mathematics";
        List<Grade> grades = Arrays.asList(new Grade(), new Grade());
        List<Student> students = Arrays.asList(new Student(), new Student());
        Teacher teacher = new Teacher();

        // When
        Subject subject = new Subject(id, subjectName, grades, students, teacher);

        // Then
        assertNotNull(subject);
        assertEquals(id, subject.getId());
        assertEquals(subjectName, subject.getSubjectName());
        assertEquals(grades, subject.getGrades());
        assertEquals(students, subject.getStudents());
        assertEquals(teacher, subject.getTeacher());
    }
}
