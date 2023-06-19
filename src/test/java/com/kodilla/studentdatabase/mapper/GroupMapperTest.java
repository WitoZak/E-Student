package com.kodilla.studentdatabase.mapper;

import com.kodilla.studentdatabase.domain.Group;
import com.kodilla.studentdatabase.domain.GroupDto;
import com.kodilla.studentdatabase.domain.Student;
import com.kodilla.studentdatabase.domain.StudentDto;
import com.kodilla.studentdatabase.mapper.GroupMapper;
import com.kodilla.studentdatabase.mapper.StudentMapper;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class GroupMapperTest {

    private final StudentMapper studentMapper = new StudentMapper();
    private final GroupMapper groupMapper = new GroupMapper(studentMapper);



    @Test
    void testMapToGroupWithNullStudents() {
        // Given
        GroupDto groupDto = new GroupDto();
        groupDto.setId(1L);
        groupDto.setGroupName("Group A");

        // When
        Group group = groupMapper.mapToGroup(groupDto);

        // Then
        assertEquals(1L, group.getId());
        assertEquals("Group A", group.getGroupName());
        assertNull(group.getStudents());
    }


    @Test
    void testMapToGroupDtoWithNullStudents() {
        // Given
        Group group = new Group();
        group.setId(1L);
        group.setGroupName("Group A");

        // When
        GroupDto groupDto = groupMapper.mapToGroupDto(group);

        // Then
        assertEquals(1L, groupDto.getId());
        assertEquals("Group A", groupDto.getGroupName());
        assertNull(groupDto.getStudentsDtos());
    }

    @Test
    void testMapToGroupDtoList() {
        // Given
        Group group1 = new Group();
        group1.setId(1L);
        group1.setGroupName("Group A");

        Group group2 = new Group();
        group2.setId(2L);
        group2.setGroupName("Group B");

        // When
        List<GroupDto> groupDtos = groupMapper.mapToGroupDtoList(Arrays.asList(group1, group2));

        // Then
        assertEquals(2, groupDtos.size());
        assertEquals(1L, groupDtos.get(0).getId());
        assertEquals("Group A", groupDtos.get(0).getGroupName());
        assertEquals(2L, groupDtos.get(1).getId());
        assertEquals("Group B", groupDtos.get(1).getGroupName());
    }

    @Test
    void testMapToGroupDtoListWithEmptyList() {
        // When
        List<GroupDto> groupDtos = groupMapper.mapToGroupDtoList(Collections.emptyList());

        // Then
        assertEquals(0, groupDtos.size());
    }
}
