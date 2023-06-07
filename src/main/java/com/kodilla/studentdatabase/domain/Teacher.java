package com.kodilla.studentdatabase.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "TEACHERS")
public class Teacher {
    @Id
    @GeneratedValue
    @Column(name = "TEACHER_ID", unique = true)
    private Long id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "EMAIL")
    private String mail;

    @Column(name = "PHONE")
    private String phone;

    @OneToMany(mappedBy = "teacher")
    private List<Subject> subjects;

    @OneToMany(mappedBy = "teacher")
    private List<Grade> grades;

    static class TeacherFactory {

        public static Teacher createTeacher(String firstName, String lastName, String email) {
            Teacher teacher = new Teacher();
            teacher.setFirstName(firstName);
            teacher.setLastName(lastName);
            teacher.setMail(email);
            return teacher;
        }

        public static Teacher createContactTeacher(String firstName, String lastName, String email, String phone) {
            Teacher teacher = createTeacher(firstName, lastName, email);
            teacher.setPhone(phone);
            return teacher;
        }

        public static Teacher createTeacherWithSubjects(String firstName, String lastName, String email, List<Subject> subjects) {
            Teacher teacher = createTeacher(firstName, lastName, email);
            teacher.setSubjects(subjects);
            return teacher;
        }
    }
}
