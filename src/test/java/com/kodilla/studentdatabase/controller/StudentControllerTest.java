package com.kodilla.studentdatabase.controller;

import com.kodilla.studentdatabase.domain.Student;
import com.kodilla.studentdatabase.domain.StudentDto;
import com.kodilla.studentdatabase.exceptions.*;
import com.kodilla.studentdatabase.mapper.StudentMapper;
import com.kodilla.studentdatabase.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StudentControllerTest {

    @Mock
    private StudentService studentService;

    @Mock
    private StudentMapper studentMapper;

    @InjectMocks
    private StudentController studentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAllStudents() {
        // Given
        List<Student> students = new ArrayList<>();
        students.add(new Student());
        students.add(new Student());
        when(studentService.getAllStudents()).thenReturn(students);
        when(studentMapper.mapToStudentDtoList(students)).thenReturn(new ArrayList<>());

        // When
        ResponseEntity<List<StudentDto>> response = studentController.findAllStudents();

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(0, response.getBody().size());
    }

    @Test
    void testGetStudentById() throws StudentNotFoundException {
        // Given
        Long studentId = 1L;
        Student student = new Student();
        when(studentService.getStudent(studentId)).thenReturn(student);
        when(studentMapper.mapToStudentDto(student)).thenReturn(new StudentDto());

        // When
        ResponseEntity<StudentDto> response = studentController.getStudentById(studentId);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testAddStudent() throws GroupNotFoundException, SubjectNotFoundException, TeacherNotFoundException, GradeNotFoundException {
        // Given
        StudentDto studentDto = mock(StudentDto.class);
        Student student = new Student();
        when(studentMapper.mapToStudent(studentDto)).thenReturn(student);
        when(studentService.saveStudent(student)).thenReturn(student);

        // When
        ResponseEntity<Void> response = studentController.addStudent(studentDto);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testUpdateStudent() throws StudentNotFoundException {
        // Given
        StudentDto studentDto = new StudentDto();
        Student student = new Student();
        student.setId(1L);
        when(studentService.getStudent(studentDto.getId())).thenReturn(student);
        when(studentService.updateStudent(student)).thenReturn(student);
        when(studentMapper.mapToStudentDto(student)).thenReturn(studentDto);

        // When
        ResponseEntity<StudentDto> response = studentController.updateStudent(studentDto);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(studentDto, response.getBody());
    }

    @Test
    void testDeleteStudent() {
        // Given
        Long studentId = 1L;

        // When
        ResponseEntity<Void> response = studentController.deleteStudent(studentId);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
