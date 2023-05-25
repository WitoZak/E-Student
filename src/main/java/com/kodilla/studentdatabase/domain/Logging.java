package com.kodilla.studentdatabase.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "USER")
public class Logging {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "LOG_TIMESTAMP")
    private LocalDateTime logTimestamp;

    @OneToOne
    @JoinColumn(name = "STUDENT_ID")
    private Student student;

    @OneToOne
    @JoinColumn(name = "TEACHER_ID")
    private Teacher teacher;
}
