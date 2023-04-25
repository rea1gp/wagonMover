package com.test.wagonMover.repository;

import com.test.wagonMover.model.WagonPassport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WagonPassportRepository extends JpaRepository<WagonPassport, Long> {

    List<WagonPassport> findByWagonIsNullOrderById();
}
