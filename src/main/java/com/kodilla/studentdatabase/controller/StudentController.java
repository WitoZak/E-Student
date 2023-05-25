package com.kodilla.studentdatabase.controller;

import com.kodilla.studentdatabase.domain.Student;
import com.kodilla.studentdatabase.domain.StudentDto;
import com.kodilla.studentdatabase.exceptions.StudentNotFoundException;
import com.kodilla.studentdatabase.mapper.StudentMapper;
import com.kodilla.studentdatabase.service.GroupService;
import com.kodilla.studentdatabase.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;
    private final StudentMapper studentMapper;
    private final GroupService groupService;

    @GetMapping
    public ResponseEntity<List<StudentDto>> findAllStudents() {
        List<Student> allStudents = studentService.getAllStudents();
        return ResponseEntity.ok(studentMapper.mapToStudentDtoList(allStudents));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable Long id) throws StudentNotFoundException {
        Student student = studentService.getStudent(id);
        StudentDto studentDto = studentMapper.mapToStudentDto(student);
        return ResponseEntity.ok(studentDto);
    }

    @PostMapping
    public ResponseEntity<Void> addStudent(@RequestBody StudentDto studentDto) {
        Student student = studentMapper.mapToStudent(studentDto);
        studentService.saveStudent(student);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> updateStudent(@RequestBody StudentDto studentDto) throws StudentNotFoundException {
        Student studentToUpdate = studentService.getStudent(studentDto.getId());
        studentToUpdate.setFirstName(studentDto.getFirstName());
        studentToUpdate.setLastName(studentDto.getLastName());
        studentToUpdate.setDateOfBirth(studentDto.getDateOfBirth());
        studentToUpdate.setAddress(studentDto.getAddress());
        studentToUpdate.setStudentMail(studentDto.getStudentMail());
        studentToUpdate.setStudentPhone(studentDto.getStudentPhone());
        Student updatedStudent = studentService.updateStudent(studentToUpdate);
        StudentDto updatedStudentDto = studentMapper.mapToStudentDto(updatedStudent);
        return ResponseEntity.ok(updatedStudentDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }
}
