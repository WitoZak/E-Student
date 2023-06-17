package com.kodilla.studentdatabase.service;

import com.kodilla.studentdatabase.domain.Subject;
import com.kodilla.studentdatabase.exceptions.StudentNotFoundException;
import com.kodilla.studentdatabase.exceptions.SubjectNotFoundException;
import com.kodilla.studentdatabase.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository subjectRepository;

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    public Subject getSubject(final Long id) throws SubjectNotFoundException {
        return subjectRepository.findById(id).orElseThrow(SubjectNotFoundException::new);
    }

    public Subject getSubjectByName(String subjectName) throws SubjectNotFoundException {
        Optional<Subject> optionalSubject = Optional.ofNullable(subjectRepository.findBySubjectName(subjectName));
        return optionalSubject.orElseThrow(SubjectNotFoundException::new);
    }

    public Subject saveSubject(final Subject newSubject) {
        return subjectRepository.save(newSubject);
    }

    public Subject updateSubject(Subject updatedSubject) throws SubjectNotFoundException {
        Long id = updatedSubject.getId();
        if (subjectRepository.findById(id).isPresent()) {
            Subject subject = new Subject(
                    id,
                    updatedSubject.getSubjectName(),
                    updatedSubject.getGrades(),
                    updatedSubject.getTeacher()
            );
            return subjectRepository.save(subject);
        } else {
            throw new SubjectNotFoundException();
        }
    }

    public void deleteSubject(final Long id) {
        subjectRepository.deleteById(id);
    }
}
