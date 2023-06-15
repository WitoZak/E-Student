package com.kodilla.studentdatabase.service;

import com.kodilla.studentdatabase.domain.Teacher;
import com.kodilla.studentdatabase.exceptions.TeacherNotFoundException;
import com.kodilla.studentdatabase.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public List<Teacher> getAllTeacher() {
        return teacherRepository.findAll();
    }

    public List<Teacher> findAllTeachers(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return teacherRepository.findAll();
        } else {
            return teacherRepository.search(stringFilter);
        }
    }

    public Teacher getTeacherByLastName(String lastName) throws TeacherNotFoundException {
        Optional<Teacher> optionalTeacher = Optional.ofNullable(teacherRepository.findByLastName(lastName));
        return optionalTeacher.orElseThrow(TeacherNotFoundException::new);
    }


    public Teacher getTeacher(final Long id) throws TeacherNotFoundException {
        return teacherRepository.findById(id).orElseThrow(TeacherNotFoundException::new);
    }

    public Long findTeacherIdByName(String teacherName) {
        Teacher teacher = teacherRepository.findByLastName(teacherName);
        if (teacher != null) {
            return teacher.getId();
        } else {
            return null;
        }
    }

    public Teacher saveTeacher(final Teacher newTeacher) {
        return teacherRepository.save(newTeacher);
    }

    public Teacher updateTeacher(Teacher updatedTeacher) throws TeacherNotFoundException {
        Long id = updatedTeacher.getId();
        if (teacherRepository.findById(id).isPresent()) {
            Teacher teacher = new Teacher(
                    id,
                    updatedTeacher.getFirstName(),
                    updatedTeacher.getLastName(),
                    updatedTeacher.getMail(),
                    updatedTeacher.getPhone(),
                    updatedTeacher.getSubjects(),
                    updatedTeacher.getGrades()
            );
            return teacherRepository.save(teacher);
        } else {
            throw new TeacherNotFoundException();
        }
    }

    public void deleteTeacher(final Long id) {
        teacherRepository.deleteById(id);
    }
}
