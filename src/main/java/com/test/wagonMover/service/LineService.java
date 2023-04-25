package com.test.wagonMover.service;

import com.test.wagonMover.model.Line;
import com.test.wagonMover.model.Station;
import com.test.wagonMover.model.Wagon;
import com.test.wagonMover.repository.LineRepository;
import com.test.wagonMover.repository.StationRepository;
import com.test.wagonMover.repository.WagonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class LineService {

    private final LineRepository lineRepository;

    private final WagonRepository wagonRepository;

    private final StationRepository stationRepository;


    public List<Line> getAllLine() {
        return lineRepository.findAll();
    }

    public List<Wagon> getLineWagons(Long lineId) {
        return wagonRepository.findByLineIdOrderByIndex(lineId);
    }

    public Line createLine(Long number, Long stationId) {
        Station station = stationRepository.findById(stationId)
                .orElseThrow(() -> new EntityNotFoundException("Стания с id " + stationId + " не найдена"));
        log.info("Создание лайна с номерм " + number);
        return lineRepository.save(Line.builder().number(number).station(station).build());
    }

    public void deleteLine(Long id) {
        lineRepository.deleteById(id);
    }
}
