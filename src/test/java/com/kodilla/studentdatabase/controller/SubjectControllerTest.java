package com.kodilla.studentdatabase.controller;

import com.kodilla.studentdatabase.domain.Subject;
import com.kodilla.studentdatabase.domain.SubjectDto;
import com.kodilla.studentdatabase.exceptions.SubjectNotFoundException;
import com.kodilla.studentdatabase.exceptions.TeacherNotFoundException;
import com.kodilla.studentdatabase.mapper.SubjectMapper;
import com.kodilla.studentdatabase.service.SubjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class SubjectControllerTest {

    @InjectMocks
    private SubjectController subjectController;

    @Mock
    private SubjectService subjectService;

    @Mock
    private SubjectMapper subjectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testFindAllSubjects() {
        // Given
        List<Subject> subjectList = new ArrayList<>();
        subjectList.add(new Subject());
        when(subjectService.getAllSubjects()).thenReturn(subjectList);
        when(subjectMapper.mapToSubjectDtoList(subjectList)).thenReturn(new ArrayList<>());

        // When
        ResponseEntity<List<SubjectDto>> response = subjectController.findAllSubjects();

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(0, Objects.requireNonNull(response.getBody()).size());
    }

    @Test
    void testGetSubjectById() throws SubjectNotFoundException {
        // Given
        Long subjectId = 1L;
        Subject subject = new Subject();
        SubjectDto subjectDto = new SubjectDto();
        when(subjectService.getSubject(subjectId)).thenReturn(subject);
        when(subjectMapper.mapToSubjectDto(subject)).thenReturn(subjectDto);

        // When
        ResponseEntity<SubjectDto> response = subjectController.getSubjectById(subjectId);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(subjectDto, response.getBody());
    }

    @Test
    void testAddSubject() throws TeacherNotFoundException {
        // Given
        SubjectDto subjectDto = new SubjectDto();
        Subject subject = new Subject();
        when(subjectMapper.mapToSubject(subjectDto)).thenReturn(subject);

        // When
        ResponseEntity<Void> response = subjectController.addSubject(subjectDto);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(subjectService, times(1)).saveSubject(subject);
    }

    @Test
    void testUpdateSubject() throws SubjectNotFoundException {
        // Given
        Long subjectId = 1L;
        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setId(subjectId);
        subjectDto.setSubjectName("Math");

        Subject subjectToUpdate = new Subject();
        subjectToUpdate.setId(subjectId);

        Subject updatedSubject = new Subject();
        updatedSubject.setId(subjectId);
        updatedSubject.setSubjectName("Math");

        SubjectDto updatedSubjectDto = new SubjectDto();
        updatedSubjectDto.setId(subjectId);
        updatedSubjectDto.setSubjectName("Math");

        Mockito.when(subjectService.getSubject(subjectId))
                .thenReturn(subjectToUpdate);
        Mockito.when(subjectService.updateSubject(Mockito.eq(subjectToUpdate)))
                .thenReturn(updatedSubject);
        Mockito.when(subjectMapper.mapToSubjectDto(updatedSubject))
                .thenReturn(updatedSubjectDto);

        // When
        ResponseEntity<SubjectDto> response = subjectController.updateSubject(subjectDto);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedSubjectDto, response.getBody());
    }

    @Test
    void testDeleteSubject() {
        // Given
        Long subjectId = 1L;

        // When
        ResponseEntity<Void> response = subjectController.deleteSubject(subjectId);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(subjectService, times(1)).deleteSubject(subjectId);
    }
}
