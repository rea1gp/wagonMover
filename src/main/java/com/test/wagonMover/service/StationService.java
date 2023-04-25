package com.test.wagonMover.service;

import com.test.wagonMover.model.Line;
import com.test.wagonMover.model.Station;
import com.test.wagonMover.repository.LineRepository;
import com.test.wagonMover.repository.StationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class StationService {

    private final StationRepository stationRepository;

    private final LineRepository lineRepository;


    public List<Station> getAllStation() {
        return stationRepository.findAll();
    }

    public List<Line> getLineByStationId(Long id) {
        log.info("Получение всех лайнов на станции " + id);
        return lineRepository.findByStationId(id);
    }

    public Station createStation(String name) {
        log.info("Создание станции " + name);
        return stationRepository.save(Station.builder().name(name).build());
    }

    public void deleteStation(Long id) {
        stationRepository.deleteById(id);
    }

}
