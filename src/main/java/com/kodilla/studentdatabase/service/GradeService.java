package com.kodilla.studentdatabase.service;

import com.kodilla.studentdatabase.domain.Grade;
import com.kodilla.studentdatabase.domain.GradeDto;
import com.kodilla.studentdatabase.exceptions.GradeNotFoundException;
import com.kodilla.studentdatabase.exceptions.StudentNotFoundException;
import com.kodilla.studentdatabase.exceptions.SubjectNotFoundException;
import com.kodilla.studentdatabase.exceptions.TeacherNotFoundException;
import com.kodilla.studentdatabase.mapper.GradeMapper;
import com.kodilla.studentdatabase.repository.GradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GradeService {

    private final GradeRepository gradeRepository;
    private final GradeMapper gradeMapper;

    public List<Grade> getAllGrades() {
        return gradeRepository.findAll();
    }

    public List<GradeDto> getAllGradesDto() {
        List<Grade> grades = gradeRepository.findAll();
        return gradeMapper.mapToGradeDtoList(grades);
    }

    public Grade getGrade(final Long id) throws GradeNotFoundException {
        return gradeRepository.findById(id).orElseThrow(GradeNotFoundException::new);
    }

    public Grade saveGrade(final Grade newGrades) {
        return gradeRepository.save(newGrades);
    }

    public void saveGradeDto(GradeDto gradeDto) throws SubjectNotFoundException, TeacherNotFoundException, StudentNotFoundException {
        Grade grade = gradeMapper.mapToGrade(gradeDto);
        Grade savedGrade = gradeRepository.save(grade);
        gradeMapper.mapToGradeDto(savedGrade);
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

    public GradeDto deleteGradeDto(final Long id) throws GradeNotFoundException {
        Grade deletedGrade = gradeRepository.findById(id).orElseThrow(GradeNotFoundException::new);
        gradeRepository.deleteById(id);
        return gradeMapper.mapToGradeDto(deletedGrade);
    }

}
