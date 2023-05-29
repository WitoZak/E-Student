package com.kodilla.studentdatabase.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "GROUPS_NAME")
public class Group {

    @Id
    @GeneratedValue
    @Column(name = "GROUPS_ID", unique = true)
    private Long id;

    @Column(name = "CLASS_NAME")
    private String className;

    @OneToMany(targetEntity = Student.class,
            mappedBy = "group",
            fetch = FetchType.LAZY)
    private List<Student> students;
}
