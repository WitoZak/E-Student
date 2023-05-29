package com.kodilla.studentdatabase.repository;

import com.kodilla.studentdatabase.domain.Group;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends CrudRepository<Group, Long> {

    List<Group> findAll();

}
