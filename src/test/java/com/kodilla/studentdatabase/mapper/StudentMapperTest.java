package com.kodilla.studentdatabase.mapper;

import com.kodilla.studentdatabase.domain.Group;
import com.kodilla.studentdatabase.domain.Student;
import com.kodilla.studentdatabase.domain.StudentDto;
import com.kodilla.studentdatabase.exceptions.GradeNotFoundException;
import com.kodilla.studentdatabase.exceptions.TeacherNotFoundException;
import com.kodilla.studentdatabase.service.GroupService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class StudentMapperTest {

    @Mock
    private GroupService groupService;

    private StudentMapper studentMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        studentMapper = new StudentMapper();
    }

    @Test
    void testMapToStudentDto() {
        // Given
        Student student = new Student();
        student.setId(1L);
        student.setLogNumber(12345);
        student.setFirstName("John");
        student.setLastName("Doe");
        student.setDateOfBirth("1995-10-15");
        student.setAddress("123 Main St");
        student.setMail("john.doe@example.com");
        student.setPhone(123456789);

        Group group = new Group();
        group.setGroupName("Group A");
        group.setId(1L);
        student.setGroup(group);

        StudentDto expectedStudentDto = new StudentDto();
        expectedStudentDto.setId(1L);
        expectedStudentDto.setLogNumber(12345);
        expectedStudentDto.setFirstName("John");
        expectedStudentDto.setStudentLastName("Doe");
        expectedStudentDto.setDateOfBirth("1995-10-15");
        expectedStudentDto.setAddress("123 Main St");
        expectedStudentDto.setMail("john.doe@example.com");
        expectedStudentDto.setPhone(123456789);
        expectedStudentDto.setGroupName("Group A");

        // When
        StudentDto actualStudentDto = studentMapper.mapToStudentDto(student);

        // Then
        assertEquals(expectedStudentDto, actualStudentDto);
    }
}
