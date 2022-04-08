package com.kadioglumf.interview.repository;

import com.kadioglumf.interview.entity.IncomingOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomingOperationRepository extends JpaRepository<IncomingOperation,Long> {
}
