package com.kodilla.studentdatabase.service;

import com.kodilla.studentdatabase.domain.Group;
import com.kodilla.studentdatabase.exceptions.GroupNotFoundException;
import com.kodilla.studentdatabase.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    public Group getGroup(final Long id) throws GroupNotFoundException {
        return groupRepository.findById(id).orElseThrow(GroupNotFoundException::new);
    }

    public Group getGroupByName(String groupName) {
        return groupRepository.findByGroupName(groupName);
    }

    public Group saveGroup(final Group newGroup) {
        return groupRepository.save(newGroup);
    }

    public Group updateGrade(Group updatedGroup) throws GroupNotFoundException {
        Long id = updatedGroup.getId();
        if (groupRepository.findById(id).isPresent()) {
            Group group = new Group(
                    id,
                    updatedGroup.getGroupName(),
                    updatedGroup.getStudents()
            );
            return groupRepository.save(group);
        } else {
            throw  new GroupNotFoundException();
        }
    }

    public void deleteGroup(final Long id) {
        groupRepository.deleteById(id);
    }
}
