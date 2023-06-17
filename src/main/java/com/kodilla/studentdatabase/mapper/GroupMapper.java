package com.kodilla.studentdatabase.mapper;

import com.kodilla.studentdatabase.domain.Group;
import com.kodilla.studentdatabase.domain.GroupDto;
import com.kodilla.studentdatabase.domain.Student;
import com.kodilla.studentdatabase.domain.StudentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupMapper {

    private final StudentMapper studentMapper;

    public Group mapToGroup(final GroupDto groupDto) {
        List<Student> students = groupDto.getStudentsDtos() != null ? studentMapper.mapToStudentList(groupDto.getStudentsDtos()) : null;
        return new Group(
                groupDto.getId(),
                groupDto.getGroupName(),
                students
        );
    }

    public GroupDto mapToGroupDto(final Group group) {
        List<StudentDto> studentsDtos = group.getStudents() != null ? studentMapper.mapToStudentDtoList(group.getStudents()) : null;
        return new GroupDto(
                group.getId(),
                group.getGroupName(),
                studentsDtos
        );
    }

    public List<GroupDto> mapToGroupDtoList(final List<Group> listOfGroups) {
        return listOfGroups.stream()
                .map(this::mapToGroupDto)
                .toList();
    }
}
