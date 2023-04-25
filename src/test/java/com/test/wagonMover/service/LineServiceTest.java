package com.test.wagonMover.service;

import com.test.wagonMover.model.Line;
import com.test.wagonMover.model.Station;
import com.test.wagonMover.model.Wagon;
import com.test.wagonMover.repository.LineRepository;
import com.test.wagonMover.repository.StationRepository;
import com.test.wagonMover.repository.WagonRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class LineServiceTest {

    @Mock
    private LineRepository lineRepository;

    @Mock
    private WagonRepository wagonRepository;

    @Mock
    private StationRepository stationRepository;

    @InjectMocks
    private LineService lineService;

    @Test
    void getAllLine() {
        List<Line> lineList = new ArrayList<>();
        lineList.add(Line.builder().number(9999L).id(1L).build());
        lineList.add(Line.builder().number(8888L).id(2L).build());
        when(lineRepository.findAll()).thenReturn(lineList);
        List<Line> result = lineService.getAllLine();

        assertEquals(lineList, result);
    }

    @Test
    void getLineWagons() {
        List<Wagon> wagonList = Arrays.asList(new Wagon(), new Wagon());
        Long lineId = 1L;
        when(wagonRepository.findByLineIdOrderByIndex(lineId)).thenReturn(wagonList);
        List<Wagon> result = lineService.getLineWagons(lineId);

        assertEquals(wagonList, result);
    }

    @Test
    void createLine() {
        Station station = Station.builder().name("Station").id(1L).build();
        Line line = Line.builder().number(1111L).id(1L).station(station).build();
        when(stationRepository.findById(1L)).thenReturn(Optional.of(station));
        when(lineRepository.save(any(Line.class))).thenReturn(line);
        Line result = lineService.createLine(1111L, 1L);

        assertEquals(line, result);
    }

    @Test
    void createLineException() {
        Station station = Station.builder().name("Station").id(1L).build();
        Line line = Line.builder().number(1111L).id(1L).station(station).build();
        when(stationRepository.findById(1L)).thenReturn(Optional.empty());
        when(lineRepository.save(any(Line.class))).thenReturn(line);

        assertThrows(EntityNotFoundException.class, () -> lineService.createLine(1111L, 1L));
    }

}