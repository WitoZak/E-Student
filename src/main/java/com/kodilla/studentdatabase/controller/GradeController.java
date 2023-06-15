package com.kodilla.studentdatabase.controller;

import com.kodilla.studentdatabase.domain.Grade;
import com.kodilla.studentdatabase.domain.GradeDto;
import com.kodilla.studentdatabase.exceptions.GradeNotFoundException;
import com.kodilla.studentdatabase.exceptions.StudentNotFoundException;
import com.kodilla.studentdatabase.exceptions.SubjectNotFoundException;
import com.kodilla.studentdatabase.exceptions.TeacherNotFoundException;
import com.kodilla.studentdatabase.mapper.GradeMapper;
import com.kodilla.studentdatabase.service.GradeService;
import com.kodilla.studentdatabase.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/grades")
@RequiredArgsConstructor
public class GradeController {

    private final GradeService gradeService;
    private final GradeMapper gradeMapper;
    private final SubjectService subjectService;

    @GetMapping
    public ResponseEntity<List<GradeDto>> findAllGrades() {
        List<Grade> allGrades = gradeService.getAllGrades();
        return ResponseEntity.ok(gradeMapper.mapToGradeDtoList(allGrades));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GradeDto> getGradeById(@PathVariable Long id) throws GradeNotFoundException {
        Grade grade = gradeService.getGrade(id);
        GradeDto gradeDto = gradeMapper.mapToGradeDto(grade);
        return ResponseEntity.ok(gradeDto);
    }

    @PostMapping
    public ResponseEntity<Void> addGrade(@RequestBody GradeDto gradeDto) throws SubjectNotFoundException, TeacherNotFoundException, StudentNotFoundException {
        Grade grade = gradeMapper.mapToGrade(gradeDto);
        subjectService.saveSubject(grade.getSubject());
        gradeService.saveGrade(grade);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<GradeDto> updateGrade(@RequestBody GradeDto gradeDto) throws GradeNotFoundException {
        Grade gradeToUpdate = gradeService.getGrade(gradeDto.getId());
        gradeToUpdate.setValue(gradeDto.getValue());
        gradeToUpdate.setGradeTimestamp(gradeDto.getGradeTimestamp());
        Grade updatedGrade = gradeService.updateGrade(gradeToUpdate);
        GradeDto updatedGradeDto = gradeMapper.mapToGradeDto(updatedGrade);
        return ResponseEntity.ok(updatedGradeDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGrade(@PathVariable Long id) {
        gradeService.deleteGrade(id);
        return ResponseEntity.ok().build();
    }
}
