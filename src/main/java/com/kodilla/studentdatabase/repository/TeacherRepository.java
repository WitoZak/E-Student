package com.kodilla.studentdatabase.repository;

import com.kodilla.studentdatabase.domain.Teacher;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends CrudRepository<Teacher, Long> {

    List<Teacher> findAll();

    @Query("select c from TEACHERS c " +
            "where lower(c.firstName) like lower(concat('%', :searchTerm, '%')) " +
            "or lower(c.lastName) like lower(concat('%', :searchTerm, '%'))")
    List<Teacher> search(@Param("searchTerm") String searchTerm);

    Teacher findByLastName(String lastName);

}
