package com.kodilla.studentdatabase.domain;

import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link Group}
 */
@Value
public class GroupDto implements Serializable {
    Long id;
    String className;
    List<Student> studentsDtos;
}