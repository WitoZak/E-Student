package com.kodilla.studentdatabase.service;

import com.kodilla.studentdatabase.domain.Teacher;
import com.kodilla.studentdatabase.exceptions.TeacherNotFoundException;
import com.kodilla.studentdatabase.repository.TeacherRepository;
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

class TeacherServiceTest {

    @Mock
    private TeacherRepository teacherRepository;

    @InjectMocks
    private TeacherService teacherService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllTeachers() {
        // Given
        List<Teacher> teachers = new ArrayList<>();
        teachers.add(new Teacher());
        teachers.add(new Teacher());
        when(teacherRepository.findAll()).thenReturn(teachers);

        // When
        List<Teacher> result = teacherService.getAllTeacher();

        // Then
        assertEquals(2, result.size());
    }

    @Test
    void testGetTeacherExists() throws TeacherNotFoundException {
        // Given
        Long teacherId = 1L;
        Teacher teacher = new Teacher();
        when(teacherRepository.findById(teacherId)).thenReturn(Optional.of(teacher));

        // When
        Teacher result = teacherService.getTeacher(teacherId);

        // Then
        assertNotNull(result);
        assertEquals(teacher, result);
    }

    @Test
    void testGetTeacherNotExists() {
        // Given
        Long teacherId = 1L;
        when(teacherRepository.findById(teacherId)).thenReturn(Optional.empty());

        // When
        assertThrows(TeacherNotFoundException.class, () -> teacherService.getTeacher(teacherId));
    }

    @Test
    void testSaveTeacher() {
        // Given
        Teacher teacher = new Teacher();
        when(teacherRepository.save(teacher)).thenReturn(teacher);

        // When
        Teacher result = teacherService.saveTeacher(teacher);

        // Then
        assertNotNull(result);
        assertEquals(teacher, result);
    }

    @Test
    void testUpdateTeacherNotExists() {
        // Given
        Long teacherId = 1L;
        Teacher updatedTeacher = new Teacher();
        updatedTeacher.setId(teacherId);
        when(teacherRepository.findById(teacherId)).thenReturn(Optional.empty());

        // When
        assertThrows(TeacherNotFoundException.class, () -> teacherService.updateTeacher(updatedTeacher));
    }

    @Test
    void testDeleteTeacher() {
        // Given
        Long teacherId = 1L;

        // When
        teacherService.deleteTeacher(teacherId);

        // Then
        verify(teacherRepository, times(1)).deleteById(teacherId);
    }
}
