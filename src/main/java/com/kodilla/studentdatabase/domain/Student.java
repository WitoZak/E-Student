package com.kodilla.studentdatabase.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "STUDENTS")
public class Student {

    @Id
    @GeneratedValue
    @Column(name = "STUDENT_ID", unique = true)
    private Long id;

    @Column(name = "LOG_NUMBER")
    private Integer logNumber;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "DATE_OF_BIRTH")
    private String dateOfBirth;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "STUDENT_MAIL")
    private String studentMail;

    @Column(name = "STUDENT_PHONE")
    private String studentPhone;

    @OneToMany(mappedBy = "student")
    private List<Grade> grades;

    @ManyToOne
    @JoinColumn(name = "SUBJECT_NAME")
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "CLASS_NAME")
    private Group group;

    public Student(Builder builder) {
        this.id = builder.id;
        this.logNumber = builder.logNumber;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.dateOfBirth = builder.dateOfBirth;
        this.address = builder.address;
        this.studentMail = builder.studentMail;
        this.studentPhone = builder.studentPhone;
        this.grades = builder.grades;
        this.subject = builder.subject;
        this.group = builder.group;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private Integer logNumber;
        private String firstName;
        private String lastName;
        private String dateOfBirth;
        private String address;
        private String studentMail;
        private String studentPhone;
        private List<Grade> grades;
        private Subject subject;
        private Group group;

        private Builder() {
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }
        public Builder logNumber(Integer logNumber) {
            this.logNumber = logNumber;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder dateOfBirth(String dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder studentMail(String studentMail) {
            this.studentMail = studentMail;
            return this;
        }

        public Builder studentPhone(String studentPhone) {
            this.studentPhone = studentPhone;
            return this;
        }

        public Builder grades(List<Grade> grades) {
            this.grades = grades;
            return this;
        }

        public Builder subject(Subject subject) {
            this.subject = subject;
            return this;
        }

        public Builder group(Group group) {
            this.group = group;
            return this;
        }

        public Student build() {
            return new Student(this);
        }
    }
}