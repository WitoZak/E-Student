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
        String mail = "john.doe@example.com";
        Integer phone = 123-456-7890;
        List<Grade> grades = Arrays.asList(new Grade(), new Grade());
        Subject subject = new Subject();
        Group group = new Group();

        // When
        Student student = Student.builder()
                .id(id)
                .logNumber(logNumber)
                .firstName(firstName)
                .lastName(lastName)
                .dateOfBirth(dateOfBirth)
                .address(address)
                .mail(mail)
                .phone(phone)
                .grades(grades)
                .group(group)
                .build();

        // Then
        assertEquals(id, student.getId());
        assertEquals(logNumber, student.getLogNumber());
        assertEquals(firstName, student.getFirstName());
        assertEquals(lastName, student.getLastName());
        assertEquals(dateOfBirth, student.getDateOfBirth());
        assertEquals(address, student.getAddress());
        assertEquals(mail, student.getMail());
        assertEquals(phone, student.getPhone());
        assertEquals(grades, student.getGrades());
        assertEquals(group, student.getGroup());
    }
}