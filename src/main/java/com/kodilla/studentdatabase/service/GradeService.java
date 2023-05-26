package com.kodilla.studentdatabase.service;

import com.kodilla.studentdatabase.domain.Grade;
import com.kodilla.studentdatabase.exceptions.GradeNotFoundException;
import com.kodilla.studentdatabase.repository.GradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GradeService {

    private final GradeRepository gradeRepository;

    public List<Grade> getAllGrades() {
        return gradeRepository.findAll();
    }

    public Grade getGrade(final Long id) throws GradeNotFoundException {
        return gradeRepository.findById(id).orElseThrow(GradeNotFoundException::new);
    }

    public Grade saveGrade(final Grade newGrades) {
        return gradeRepository.save(newGrades);
    }

    public Grade updateGrade(Grade updatedGrade) throws GradeNotFoundException {
        Long id = updatedGrade.getId();
        if (gradeRepository.findById(id).isPresent()) {
            Grade grade = new Grade(
                    id,
                    updatedGrade.getValue(),
                    updatedGrade.getGradeTimestamp(),
                    updatedGrade.getSubject(),
                    updatedGrade.getTeacher(),
                    updatedGrade.getStudent()
            );
            return gradeRepository.save(grade);
        } else {
            throw new GradeNotFoundException();
        }
    }

    public void deleteGrade(final Long id) {
        gradeRepository.deleteById(id);
    }
}
