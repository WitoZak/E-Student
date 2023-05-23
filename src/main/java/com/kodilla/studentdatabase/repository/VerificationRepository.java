package com.kodilla.studentdatabase.repository;

import com.kodilla.studentdatabase.domain.Verification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationRepository extends CrudRepository<Verification, Long> {
}
