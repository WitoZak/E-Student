package com.kodilla.studentdatabase.service;

import com.kodilla.studentdatabase.domain.Student;
import com.kodilla.studentdatabase.exceptions.StudentNotFoundException;
import com.kodilla.studentdatabase.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllStudents() {
        // Given
        List<Student> students = new ArrayList<>();
        students.add(new Student());
        students.add(new Student());
        when(studentRepository.findAll()).thenReturn(students);

        // When
        List<Student> result = studentService.getAllStudents();

        // Then
        assertEquals(2, result.size());
    }

    @Test
    void testGetStudentExists() throws StudentNotFoundException {
        // Given
        Long studentId = 1L;
        Student student = new Student();
        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));

        // When
        Student result = studentService.getStudent(studentId);

        // Then
        assertNotNull(result);
        assertEquals(student, result);
    }

    @Test
    void testGetStudentNotExists() {
        // Given
        Long studentId = 1L;
        when(studentRepository.findById(studentId)).thenReturn(Optional.empty());

        // When
        assertThrows(StudentNotFoundException.class, () -> studentService.getStudent(studentId));
    }

    @Test
    void testSaveStudent() {
        // Given
        Student student = new Student();
        when(studentRepository.save(student)).thenReturn(student);

        // When
        Student result = studentService.saveStudent(student);

        // Then
        assertNotNull(result);
        assertEquals(student, result);
    }

    @Test
    void testUpdateStudentNotExists() {
        // Given
        Long studentId = 1L;
        Student updatedStudent = new Student();
        updatedStudent.setId(studentId);
        when(studentRepository.findById(studentId)).thenReturn(Optional.empty());

        // When
        assertThrows(StudentNotFoundException.class, () -> studentService.updateStudent(updatedStudent));
    }

    @Test
    void testDeleteStudent() {
        // Given
        Long studentId = 1L;

        // When
        studentService.deleteStudent(studentId);

        // Then
        verify(studentRepository, times(1)).deleteById(studentId);
    }
}
