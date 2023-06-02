package com.kodilla.studentdatabase.controller;

import com.kodilla.studentdatabase.domain.Teacher;
import com.kodilla.studentdatabase.domain.TeacherDto;
import com.kodilla.studentdatabase.exceptions.TeacherNotFoundException;
import com.kodilla.studentdatabase.mapper.TeacherMapper;
import com.kodilla.studentdatabase.service.TeacherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TeacherControllerTest {

    private TeacherController teacherController;

    @Mock
    private TeacherService teacherService;

    @Mock
    private TeacherMapper teacherMapper;

    @BeforeEach
    void setUp() {
        teacherService = mock(TeacherService.class);
        teacherMapper = mock(TeacherMapper.class);
        teacherController = new TeacherController(teacherService, teacherMapper);
    }

    @Test
    void testFindAllTeachers() {
        // Given
        List<Teacher> teacherList = new ArrayList<>();
        teacherList.add(new Teacher());
        when(teacherService.getAllTeacher()).thenReturn(teacherList);
        when(teacherMapper.mapToTeachDtoList(teacherList)).thenReturn(new ArrayList<>());

        // When
        ResponseEntity<List<TeacherDto>> response = teacherController.findAllTeachers();

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(0, response.getBody().size());
    }

    @Test
    void testGetTeacherById() throws TeacherNotFoundException {
        // Given
        Long teacherId = 1L;
        Teacher teacher = new Teacher();
        TeacherDto teacherDto = new TeacherDto();
        when(teacherService.getTeacher(teacherId)).thenReturn(teacher);
        when(teacherMapper.mapToTeacherDto(teacher)).thenReturn(teacherDto);

        // When
        ResponseEntity<TeacherDto> response = teacherController.getTeacherById(teacherId);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(teacherDto, response.getBody());
    }

    @Test
    void testAddTeacher() throws TeacherNotFoundException {
        // Given
        TeacherDto teacherDto = new TeacherDto();
        Teacher teacher = new Teacher();
        when(teacherMapper.mapToTeacher(teacherDto)).thenReturn(teacher);

        // When
        ResponseEntity<Void> response = teacherController.addTeacher(teacherDto);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(teacherService, times(1)).saveTeacher(teacher);
    }

    @Test
    void testUpdateTeacher() throws TeacherNotFoundException {
        // Given
        Long teacherId = 1L;
        TeacherDto teacherDto = new TeacherDto();
        Teacher teacherToUpdate = new Teacher();
        teacherToUpdate.setId(teacherId);
        Teacher updatedTeacher = new Teacher();
        TeacherDto updatedTeacherDto = new TeacherDto();
        when(teacherService.getTeacher(teacherId)).thenReturn(teacherToUpdate);
        when(teacherService.updateTeacher(teacherToUpdate)).thenReturn(updatedTeacher);
        when(teacherMapper.mapToTeacherDto(updatedTeacher)).thenReturn(updatedTeacherDto);

        // When
        ResponseEntity<TeacherDto> response = teacherController.updateTeacher(teacherDto);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedTeacherDto, response.getBody());
    }

    @Test
    void testDeleteTeacher() {
        // Given
        Long teacherId = 1L;

        // When
        ResponseEntity<Void> response = teacherController.deleteTeacher(teacherId);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(teacherService, times(1)).deleteTeacher(teacherId);
    }
}
