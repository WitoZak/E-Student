package com.kodilla.studentdatabase.controller;

import com.kodilla.studentdatabase.domain.Group;
import com.kodilla.studentdatabase.domain.GroupDto;
import com.kodilla.studentdatabase.exceptions.GroupNotFoundException;
import com.kodilla.studentdatabase.mapper.GroupMapper;
import com.kodilla.studentdatabase.service.GroupService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class GroupControllerTest {

    @Mock
    private GroupService groupService;

    @Mock
    private GroupMapper groupMapper;

    @InjectMocks
    private GroupController groupController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAllGroups() {
        // Given
        List<Group> groups = new ArrayList<>();
        groups.add(new Group());
        groups.add(new Group());
        when(groupService.getAllGroups()).thenReturn(groups);
        when(groupMapper.mapToGroupDtoList(groups)).thenReturn(new ArrayList<>());

        // When
        ResponseEntity<List<GroupDto>> response = groupController.findAllGroups();

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(0, response.getBody().size());
    }

    @Test
    void testGetGroupById() throws GroupNotFoundException {
        // Given
        Long groupId = 1L;
        Group group = new Group();
        when(groupService.getGroup(groupId)).thenReturn(group);
        when(groupMapper.mapToGroupDto(group)).thenReturn(new GroupDto());

        // When
        ResponseEntity<GroupDto> response = groupController.getGroupById(groupId);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testAddGroup() {
        // Given
        GroupDto groupDto = new GroupDto();
        Group group = new Group();
        when(groupMapper.mapToGroup(groupDto)).thenReturn(group);
        when(groupService.saveGroup(group)).thenReturn(group);

        // When
        ResponseEntity<Void> response = groupController.addGroup(groupDto);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testUpdateGroup() throws GroupNotFoundException {
        // Given
        GroupDto groupDto = new GroupDto();
        Group group = new Group();
        group.setId(1L);
        when(groupService.getGroup(groupDto.getId())).thenReturn(group);
        when(groupService.updateGrade(group)).thenReturn(group);
        when(groupMapper.mapToGroupDto(group)).thenReturn(groupDto);

        // When
        ResponseEntity<GroupDto> response = groupController.updateGroup(groupDto);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(groupDto, response.getBody());
    }

    @Test
    void testDeleteGroup() {
        // Given
        Long groupId = 1L;

        // When
        ResponseEntity<Void> response = groupController.deleteGroup(groupId);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
