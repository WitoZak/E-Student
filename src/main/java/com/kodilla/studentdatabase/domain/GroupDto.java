package com.kodilla.studentdatabase.domain;

import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link Group}
 */
@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class GroupDto implements Serializable {
    Long id;
    String groupName;
    List<StudentDto> studentsDtos;
}