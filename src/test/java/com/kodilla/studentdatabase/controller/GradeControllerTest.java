package com.kodilla.studentdatabase.controller;

import com.kodilla.studentdatabase.domain.Grade;
import com.kodilla.studentdatabase.domain.GradeDto;
import com.kodilla.studentdatabase.exceptions.GradeNotFoundException;
import com.kodilla.studentdatabase.exceptions.StudentNotFoundException;
import com.kodilla.studentdatabase.exceptions.SubjectNotFoundException;
import com.kodilla.studentdatabase.exceptions.TeacherNotFoundException;
import com.kodilla.studentdatabase.mapper.GradeMapper;
import com.kodilla.studentdatabase.service.GradeService;
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
import static org.mockito.Mockito.when;

class GradeControllerTest {

    @Mock
    private GradeService gradeService;

    @Mock
    private GradeMapper gradeMapper;

    @InjectMocks
    private GradeController gradeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAllGrades() {
        // Given
        List<Grade> grades = new ArrayList<>();
        grades.add(new Grade());
        grades.add(new Grade());
        when(gradeService.getAllGrades()).thenReturn(grades);
        when(gradeMapper.mapToGradeDtoList(grades)).thenReturn(new ArrayList<>());

        // When
        ResponseEntity<List<GradeDto>> response = gradeController.findAllGrades();

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(0, response.getBody().size());
    }

    @Test
    void testGetGradeById() throws GradeNotFoundException {
        // Given
        Long gradeId = 1L;
        Grade grade = new Grade();
        when(gradeService.getGrade(gradeId)).thenReturn(grade);
        when(gradeMapper.mapToGradeDto(grade)).thenReturn(new GradeDto());

        // When
        ResponseEntity<GradeDto> response = gradeController.getGradeById(gradeId);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testAddGrade() throws SubjectNotFoundException, TeacherNotFoundException, StudentNotFoundException {
        // Given
        GradeDto gradeDto = new GradeDto();
        Grade grade = new Grade();
        when(gradeMapper.mapToGrade(gradeDto)).thenReturn(grade);
        when(gradeService.saveGrade(grade)).thenReturn(grade);

        // When
        ResponseEntity<Void> response = gradeController.addGrade(gradeDto);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testUpdateGrade() throws GradeNotFoundException {
        // Given
        GradeDto gradeDto = new GradeDto();
        Grade grade = new Grade();
        grade.setId(1L);
        when(gradeService.getGrade(gradeDto.getId())).thenReturn(grade);
        when(gradeService.updateGrade(grade)).thenReturn(grade);
        when(gradeMapper.mapToGradeDto(grade)).thenReturn(gradeDto);

        // When
        ResponseEntity<GradeDto> response = gradeController.updateGrade(gradeDto);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(gradeDto, response.getBody());
    }

    @Test
    void testDeleteGrade() {
        // Given
        Long gradeId = 1L;

        // When
        ResponseEntity<Void> response = gradeController.deleteGrade(gradeId);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
