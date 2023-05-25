package com.kodilla.studentdatabase.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name = "GROUPS")
public class Group {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "CLASS_NAME")
    private String className;

    @OneToMany(targetEntity = Student.class,
            mappedBy = "group",
            fetch = FetchType.LAZY)
    private List<Student> students;
}
