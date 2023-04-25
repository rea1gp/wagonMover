package com.test.wagonMover.repository;

import com.test.wagonMover.model.Line;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LineRepository extends JpaRepository<Line, Long> {

    List<Line> findByStationId(Long id);

    Optional<Line> findByWagonId(Long wagonId);

}
