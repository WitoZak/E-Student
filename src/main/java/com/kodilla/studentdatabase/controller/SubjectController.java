package com.kodilla.studentdatabase.controller;

import com.kodilla.studentdatabase.domain.Subject;
import com.kodilla.studentdatabase.domain.SubjectDto;
import com.kodilla.studentdatabase.exceptions.SubjectNotFoundException;
import com.kodilla.studentdatabase.exceptions.TeacherNotFoundException;
import com.kodilla.studentdatabase.mapper.SubjectMapper;
import com.kodilla.studentdatabase.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/subjects")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;
    private final SubjectMapper subjectMapper;

    @GetMapping
    public ResponseEntity<List<SubjectDto>> findAllSubjects() {
        List<Subject> allSubjects = subjectService.getAllSubjects();
        return ResponseEntity.ok(subjectMapper.mapToSubjectDtoList(allSubjects));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubjectDto> getSubjectById(@PathVariable Long id) throws SubjectNotFoundException {
        Subject subject = subjectService.getSubject(id);
        SubjectDto subjectDto = subjectMapper.mapToSubjectDto(subject);
        return ResponseEntity.ok(subjectDto);
    }

    @PostMapping
    public ResponseEntity<Void> addSubject(@RequestBody SubjectDto subjectDto) throws TeacherNotFoundException {
        Subject subject = subjectMapper.mapToSubject(subjectDto);
        subjectService.saveSubject(subject);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<SubjectDto> updateSubject(@RequestBody SubjectDto subjectDto) throws SubjectNotFoundException {
        Subject subjectToUpdate = subjectService.getSubject(subjectDto.getId());
        subjectToUpdate.setSubjectName(subjectDto.getSubjectName());
        Subject updatedSubject = subjectService.updateSubject(subjectToUpdate);
        SubjectDto updatedSubjectDto = subjectMapper.mapToSubjectDto(updatedSubject);
        return ResponseEntity.ok(updatedSubjectDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubject(@PathVariable Long id) {
        subjectService.deleteSubject(id);
        return ResponseEntity.ok().build();
    }
}