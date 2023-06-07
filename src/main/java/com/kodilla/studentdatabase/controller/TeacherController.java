package com.kodilla.studentdatabase.controller;

import com.kodilla.studentdatabase.domain.Teacher;
import com.kodilla.studentdatabase.domain.TeacherDto;
import com.kodilla.studentdatabase.exceptions.TeacherNotFoundException;
import com.kodilla.studentdatabase.mapper.TeacherMapper;
import com.kodilla.studentdatabase.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;
    private final TeacherMapper teacherMapper;

    @GetMapping
    public ResponseEntity<List<TeacherDto>> findAllTeachers() {
        List<Teacher> allTeachers = teacherService.getAllTeacher();
        return ResponseEntity.ok(teacherMapper.mapToTeachDtoList(allTeachers));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDto> getTeacherById(@PathVariable Long id) throws TeacherNotFoundException {
        Teacher teacher = teacherService.getTeacher(id);
        TeacherDto teacherDto = teacherMapper.mapToTeacherDto(teacher);
        return ResponseEntity.ok(teacherDto);
    }

    @PostMapping
    public ResponseEntity<Void> addTeacher(@RequestBody TeacherDto teacherDto) throws TeacherNotFoundException {
        Teacher teacher = teacherMapper.mapToTeacher(teacherDto);
        teacherService.saveTeacher(teacher);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<TeacherDto> updateTeacher(@RequestBody TeacherDto teacherDto) throws TeacherNotFoundException {
        Teacher teacherToUpdate = teacherService.getTeacher(teacherDto.getId());
        teacherToUpdate.setFirstName(teacherDto.getFirstName());
        teacherToUpdate.setLastName(teacherDto.getLastName());
        teacherToUpdate.setMail(teacherDto.getMail());
        teacherToUpdate.setPhone(teacherDto.getPhone());
        Teacher updatedTeacher = teacherService.updateTeacher(teacherToUpdate);
        TeacherDto updatedTeacherDto = teacherMapper.mapToTeacherDto(updatedTeacher);
        return ResponseEntity.ok(updatedTeacherDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
        return ResponseEntity.ok().build();
    }
}