package com.kodilla.studentdatabase.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GroupTest {

    @Test
    void shouldCreateGroup() {
        // Given
        Long id = 1L;
        String className = "Class A";
        List<Student> students = Arrays.asList(new Student(), new Student());

        // When
        Group group = new Group(id, className, students);

        // Then
        assertEquals(id, group.getId());
        assertEquals(className, group.getClassName());
        assertEquals(students, group.getStudents());
        assertTrue(group.getStudents().containsAll(students));
    }
}
