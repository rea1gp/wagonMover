package com.test.wagonMover.repository;

import com.test.wagonMover.model.Wagon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WagonRepository extends JpaRepository<Wagon, Long> {


    List<Wagon> findByStatusId(Long id);

    List<Wagon> findByLineIdOrderByIndex(Long id);

    Long countByLineId(Long id);

    @Query(value = "SELECT line_id FROM wgn_mover.wagon where id = :id", nativeQuery = true)
    Optional<Long> findLineIdByWagonId(Long id);

}
