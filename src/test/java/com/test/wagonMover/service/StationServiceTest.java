package com.test.wagonMover.service;

import com.test.wagonMover.model.Line;
import com.test.wagonMover.model.Station;
import com.test.wagonMover.repository.LineRepository;
import com.test.wagonMover.repository.StationRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class StationServiceTest {

    @Mock
    private StationRepository stationRepository;

    @Mock
    private LineRepository lineRepository;


    @InjectMocks
    private StationService stationService;

    @Test
    void getAllStation() {
        List<Station> stationList = new ArrayList<>();
        stationList.add(Station.builder().name("station1").id(1L).build());
        stationList.add(Station.builder().name("station2").id(2L).build());
        when(stationService.getAllStation()).thenReturn(stationList);
        List<Station> result = stationService.getAllStation();

        assertEquals(stationList, result);
    }

    @Test
    void getLineByStationId() {
        List<Line> lineList = Arrays.asList(new Line(), new Line());
        when(lineRepository.findByStationId(1L)).thenReturn(lineList);
        List<Line> result = stationService.getLineByStationId(1L);

        assertEquals(lineList, result);
    }

    @Test
    void createStation() {
        String name = "Station1";
        Station station = Station.builder().name(name).id(1L).build();
        when(stationRepository.save(any(Station.class))).thenReturn(station);
        Station result = stationService.createStation(name);

        assertEquals(station, result);
    }
}