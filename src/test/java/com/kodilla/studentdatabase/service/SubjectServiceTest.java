package com.kodilla.studentdatabase.service;

import com.kodilla.studentdatabase.domain.Subject;
import com.kodilla.studentdatabase.exceptions.SubjectNotFoundException;
import com.kodilla.studentdatabase.repository.SubjectRepository;
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

class SubjectServiceTest {

    @Mock
    private SubjectRepository subjectRepository;

    @InjectMocks
    private SubjectService subjectService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllSubjects() {
        // Given
        List<Subject> subjects = new ArrayList<>();
        subjects.add(new Subject());
        subjects.add(new Subject());
        when(subjectRepository.findAll()).thenReturn(subjects);

        // When
        List<Subject> result = subjectService.getAllSubjects();

        // Then
        assertEquals(2, result.size());
    }

    @Test
    void testGetSubjectExists() throws SubjectNotFoundException {
        // Given
        Long subjectId = 1L;
        Subject subject = new Subject();
        when(subjectRepository.findById(subjectId)).thenReturn(Optional.of(subject));

        // When
        Subject result = subjectService.getSubject(subjectId);

        // Then
        assertNotNull(result);
        assertEquals(subject, result);
    }

    @Test
    void testGetSubjectNotExists() {
        // Given
        Long subjectId = 1L;
        when(subjectRepository.findById(subjectId)).thenReturn(Optional.empty());

        // When
        assertThrows(SubjectNotFoundException.class, () -> subjectService.getSubject(subjectId));
    }

    @Test
    void testSaveSubject() {
        // Given
        Subject subject = new Subject();
        when(subjectRepository.save(subject)).thenReturn(subject);

        // When
        Subject result = subjectService.saveSubject(subject);

        // Then
        assertNotNull(result);
        assertEquals(subject, result);
    }

    @Test
    void testUpdateSubjectNotExists() {
        // Given
        Long subjectId = 1L;
        Subject updatedSubject = new Subject();
        updatedSubject.setId(subjectId);
        when(subjectRepository.findById(subjectId)).thenReturn(Optional.empty());

        // When
        assertThrows(SubjectNotFoundException.class, () -> subjectService.updateSubject(updatedSubject));
    }

    @Test
    void testDeleteSubject() {
        // Given
        Long subjectId = 1L;

        // When
        subjectService.deleteSubject(subjectId);

        // Then
        verify(subjectRepository, times(1)).deleteById(subjectId);
    }
}
