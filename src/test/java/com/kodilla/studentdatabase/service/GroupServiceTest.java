package com.kodilla.studentdatabase.service;

import com.kodilla.studentdatabase.domain.Group;
import com.kodilla.studentdatabase.exceptions.GroupNotFoundException;
import com.kodilla.studentdatabase.repository.GroupRepository;
import com.kodilla.studentdatabase.service.GroupService;
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

class GroupServiceTest {

    @Mock
    private GroupRepository groupRepository;

    @InjectMocks
    private GroupService groupService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllGroups() {
        // Given
        List<Group> groups = new ArrayList<>();
        groups.add(new Group());
        groups.add(new Group());
        when(groupRepository.findAll()).thenReturn(groups);

        // When
        List<Group> result = groupService.getAllGroups();

        // Then
        assertEquals(2, result.size());
    }

    @Test
    void testGetGroupExists() throws GroupNotFoundException {
        // Given
        Long groupId = 1L;
        Group group = new Group();
        when(groupRepository.findById(groupId)).thenReturn(Optional.of(group));

        // When
        Group result = groupService.getGroup(groupId);

        // Then
        assertNotNull(result);
        assertEquals(group, result);
    }

    @Test
    void testGetGroupNotExists() {
        // Given
        Long groupId = 1L;
        when(groupRepository.findById(groupId)).thenReturn(Optional.empty());

        // When
        assertThrows(GroupNotFoundException.class, () -> groupService.getGroup(groupId));
    }

    @Test
    void testSaveGroup() {
        // Given
        Group group = new Group();
        when(groupRepository.save(group)).thenReturn(group);

        // When
        Group result = groupService.saveGroup(group);

        // Then
        assertNotNull(result);
        assertEquals(group, result);
    }

    @Test
    void testUpdateGroupNotExists() {
        // Given
        Long groupId = 1L;
        Group updatedGroup = new Group();
        updatedGroup.setId(groupId);
        when(groupRepository.findById(groupId)).thenReturn(Optional.empty());

        // When
        assertThrows(GroupNotFoundException.class, () -> groupService.updateGrade(updatedGroup));
    }

    @Test
    void testDeleteGroup() {
        // Given
        Long groupId = 1L;

        // When
        groupService.deleteGroup(groupId);

        // Then
        verify(groupRepository, times(1)).deleteById(groupId);
    }
}
