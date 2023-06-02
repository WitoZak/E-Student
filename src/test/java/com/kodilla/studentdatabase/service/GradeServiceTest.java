package com.kodilla.studentdatabase.service;

import com.kodilla.studentdatabase.domain.Grade;
import com.kodilla.studentdatabase.exceptions.GradeNotFoundException;
import com.kodilla.studentdatabase.repository.GradeRepository;
import com.kodilla.studentdatabase.service.GradeService;
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

class GradeServiceTest {

    @Mock
    private GradeRepository gradeRepository;

    @InjectMocks
    private GradeService gradeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllGrades() {
        // Given
        List<Grade> grades = new ArrayList<>();
        grades.add(new Grade());
        grades.add(new Grade());
        when(gradeRepository.findAll()).thenReturn(grades);

        // When
        List<Grade> result = gradeService.getAllGrades();

        // Then
        assertEquals(2, result.size());
    }

    @Test
    void testGetGradeExists() throws GradeNotFoundException {
        // Given
        Long gradeId = 1L;
        Grade grade = new Grade();
        when(gradeRepository.findById(gradeId)).thenReturn(Optional.of(grade));

        // When
        Grade result = gradeService.getGrade(gradeId);

        // Then
        assertNotNull(result);
        assertEquals(grade, result);
    }

    @Test
    void testGetGradeNotExists() {
        // Given
        Long gradeId = 1L;
        when(gradeRepository.findById(gradeId)).thenReturn(Optional.empty());

        // When
        assertThrows(GradeNotFoundException.class, () -> gradeService.getGrade(gradeId));
    }

    @Test
    void testSaveGrade() {
        // Given
        Grade grade = new Grade();
        when(gradeRepository.save(grade)).thenReturn(grade);

        // When
        Grade result = gradeService.saveGrade(grade);

        // Then
        assertNotNull(result);
        assertEquals(grade, result);
    }

    @Test
    void testUpdateGradeNotExists() {
        // Given
        Long gradeId = 1L;
        Grade updatedGrade = new Grade();
        updatedGrade.setId(gradeId);
        when(gradeRepository.findById(gradeId)).thenReturn(Optional.empty());

        // When
        assertThrows(GradeNotFoundException.class, () -> gradeService.updateGrade(updatedGrade));
    }

    @Test
    void testDeleteGrade() {
        // Given
        Long gradeId = 1L;

        // When
        gradeService.deleteGrade(gradeId);

        // Then
        verify(gradeRepository, times(1)).deleteById(gradeId);
    }
}
