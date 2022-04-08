package com.kadioglumf.interview.repository;

import com.kadioglumf.interview.entity.OutgoingOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutgoingOperationRepository extends JpaRepository<OutgoingOperation,String> {
}
