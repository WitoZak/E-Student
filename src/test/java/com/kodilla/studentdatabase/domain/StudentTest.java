package com.kodilla.studentdatabase.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StudentTest {

    @Test
    void testStudentBuilder() {
        // Given
        Long id = 1L;
        Integer logNumber = 123;
        String firstName = "John";
        String lastName = "Doe";
        String dateOfBirth = "2000-01-01";
        String address = "123 Main St";
        String studentMail = "john.doe@example.com";
        String studentPhone = "123-456-7890";
        List<Grade> grades = Arrays.asList(new Grade(), new Grade());
        List<Subject> subjects = Arrays.asList(new Subject(), new Subject());
        Group group = new Group();

        // When
        Student student = new Student();
        // Then
        assertEquals(id, student.getId());
        assertEquals(logNumber, student.getLogNumber());
        assertEquals(firstName, student.getFirstName());
        assertEquals(lastName, student.getLastName());
        assertEquals(dateOfBirth, student.getDateOfBirth());
        assertEquals(address, student.getAddress());
        assertEquals(studentMail, student.getStudentMail());
        assertEquals(studentPhone, student.getStudentPhone());
        assertEquals(grades, student.getGrades());
        assertEquals(subjects, student.getSubjects());
        assertEquals(group, student.getGroup());
    }
}