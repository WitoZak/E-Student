package com.kodilla.studentdatabase.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TeacherTest {

    @Test
    void testTeacherFactory() {
        // Given
        String firstName = "John";
        String lastName = "Doe";
        String email = "john.doe@example.com";
        String phone = "123-456-7890";
        List<Subject> subjects = Arrays.asList(new Subject(), new Subject());

        // When
        Teacher teacher1 = Teacher.TeacherFactory.createTeacher(firstName, lastName, email);
        Teacher teacher2 = Teacher.TeacherFactory.createContactTeacher(firstName, lastName, email, phone);
        Teacher teacher3 = Teacher.TeacherFactory.createTeacherWithSubjects(firstName, lastName, email, subjects);

        // Then
        assertEquals(firstName, teacher1.getFirstName());
        assertEquals(lastName, teacher1.getLastName());
        assertEquals(email, teacher1.getMail());

        assertEquals(firstName, teacher2.getFirstName());
        assertEquals(lastName, teacher2.getLastName());
        assertEquals(email, teacher2.getMail());
        assertEquals(phone, teacher2.getPhone());

        assertEquals(firstName, teacher3.getFirstName());
        assertEquals(lastName, teacher3.getLastName());
        assertEquals(email, teacher3.getMail());
        assertEquals(subjects, teacher3.getSubjects());
    }
}
