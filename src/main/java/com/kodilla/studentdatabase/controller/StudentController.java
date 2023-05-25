package com.kodilla.studentdatabase.controller;

import com.kodilla.studentdatabase.domain.Student;
import com.kodilla.studentdatabase.mapper.StudentMapper;
import com.kodilla.studentdatabase.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;
    private final StudentMapper studentMapper;

    @GetMapping
    public ResponseEntity<List<StudentDTO>> findAllStudents() {
        List<Student> allStudents =studentService.getAllStudents();
        return ResponseEntity.ok(studentMapper.mapToStudentDTOList(allStudents));
    }
}
