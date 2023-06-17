package com.kodilla.studentdatabase.controller;

import com.kodilla.studentdatabase.domain.Group;
import com.kodilla.studentdatabase.domain.GroupDto;
import com.kodilla.studentdatabase.exceptions.GroupNotFoundException;
import com.kodilla.studentdatabase.mapper.GroupMapper;
import com.kodilla.studentdatabase.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/groups")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;
    private final GroupMapper groupMapper;

    @GetMapping
    public ResponseEntity<List<GroupDto>> findAllGroups() {
        List<Group> allGroups = groupService.getAllGroups();
        return ResponseEntity.ok(groupMapper.mapToGroupDtoList(allGroups));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupDto> getGroupById(@PathVariable Long id) throws GroupNotFoundException {
        Group group = groupService.getGroup(id);
        GroupDto groupDto = groupMapper.mapToGroupDto(group);
        return ResponseEntity.ok(groupDto);
    }

    @PostMapping
    public ResponseEntity<Void> addGroup(@RequestBody GroupDto groupDto) {
        Group group = groupMapper.mapToGroup(groupDto);
        groupService.saveGroup(group);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<GroupDto> updateGroup(@RequestBody GroupDto groupDto) throws GroupNotFoundException {
        Group groupToUpdate = groupService.getGroup(groupDto.getId());
        groupToUpdate.setGroupName(groupDto.getGroupName());
        Group updatedGroup = groupService.updateGrade(groupToUpdate);
        GroupDto updatedGroupDto = groupMapper.mapToGroupDto(updatedGroup);
        return ResponseEntity.ok(updatedGroupDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable Long id) {
        groupService.deleteGroup(id);
        return ResponseEntity.ok().build();
    }
}
