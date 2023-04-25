package com.test.wagonMover.service;

import com.test.wagonMover.dto.WagonDTO;
import com.test.wagonMover.dto.WagonRunDTO;
import com.test.wagonMover.model.*;
import com.test.wagonMover.repository.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class WagonServiceTest {

    @Mock
    private WagonRepository wagonRepository;

    @Mock
    private WagonPassportRepository wagonPassportRepository;

    @Mock
    private CargoRepository cargoRepository;

    @Mock
    private StatusRepository statusRepository;

    @Mock
    private LineRepository lineRepository;

    @InjectMocks
    private WagonService wagonService;

    @Test
    void createWagonFromDto() {
        WagonDTO wagonDTO = WagonDTO.builder().wagonPassportId(1L).weight(2000.00).cargoId(1L).build();
        WagonPassport passport = WagonPassport.builder().capacity(3000.00).build();
        Cargo cargo = Cargo.builder().build();
        Status status = Status.builder().build();
        when(wagonRepository.save(any(Wagon.class))).thenReturn(new Wagon());
        when(wagonPassportRepository.findById(1L)).thenReturn(Optional.ofNullable(passport));
        when(cargoRepository.findById(1L)).thenReturn(Optional.ofNullable(cargo));
        when(statusRepository.findById(1L)).thenReturn(Optional.ofNullable(status));
        Wagon result = wagonService.createWagonFromDto(wagonDTO);

        assertNotNull(result);
    }

    @Test
    void createWagonFromDtoException() {
        WagonDTO wagonDTO = WagonDTO.builder().wagonPassportId(1L).build();
        when(wagonPassportRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> wagonService.createWagonFromDto(wagonDTO));
    }

    @Test
    void createWagonFromDtoWeightException() {
        WagonDTO wagonDTO = WagonDTO.builder().wagonPassportId(1L).weight(3000D).build();
        WagonPassport wagonPassport = WagonPassport.builder().capacity(2000D).build();
        when(wagonPassportRepository.findById(1L)).thenReturn(Optional.ofNullable(wagonPassport));

        assertThrows(ResponseStatusException.class, () -> wagonService.createWagonFromDto(wagonDTO));
    }


    @Test
    void getAllWagonsForRun() {
        Long statusId = 1L;
        Status status = Status.builder().name("NEW").id(statusId).build();
        List<Wagon> wagonList = Arrays.asList(Wagon.builder().status(status).build(), Wagon.builder().status(status).build());
        when(wagonRepository.findByStatusId(1L)).thenReturn(wagonList);
        List<Wagon> result = wagonService.getAllWagonsForRun();

        assertEquals(statusId, result.get(0).getStatus().getId());
    }

    @Test
    void moveToEnd() {
        Long lineId = 1L;
        Long statusId = 2L;
        Long oldLineId = 2L;
        Status status = Status.builder().name("STATION").id(statusId).build();
        Line line = Line.builder().number(1111L).id(lineId).build();
        Line oldline = Line.builder().number(2222L).id(oldLineId).build();
        List<WagonRunDTO> wagonDTOS = Arrays.asList(WagonRunDTO.builder().wagonId(1L).index(1L).build(), WagonRunDTO.builder().wagonId(2L).index(2L).build());
        List<Wagon> wagonList = Arrays.asList(Wagon.builder().id(1L).build(), Wagon.builder().id(2L).build());

        when(lineRepository.findById(lineId)).thenReturn(Optional.ofNullable(line));
        when(statusRepository.findById(statusId)).thenReturn(Optional.ofNullable(status));
        when(lineRepository.findByWagonId(1L)).thenReturn(Optional.ofNullable(oldline));
        when(wagonRepository.saveAll(any(List.class))).thenReturn(wagonList);
        when(wagonRepository.findById(1L)).thenReturn(Optional.ofNullable(wagonList.get(0)));
        when(wagonRepository.findById(2L)).thenReturn(Optional.ofNullable(wagonList.get(1)));
        List<Wagon> result = wagonService.moveToEnd(wagonDTOS, lineId);


        assertNotNull(result);
        assertEquals(line, result.get(0).getLine());
        assertEquals(status, result.get(0).getStatus());
    }

    @Test
    void wagonToEndException() {
        Long lineId = 1L;
        when(lineRepository.findByWagonId(lineId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> wagonService.moveToEnd(any(), lineId));
    }

    @Test
    void moveToTop() {
        Long lineId = 1L;
        Long statusId = 2L;
        Long oldLineId = 2L;
        Status status = Status.builder().name("STATION").id(statusId).build();
        Line line = Line.builder().number(1111L).id(lineId).build();
        Line oldline = Line.builder().number(2222L).id(oldLineId).build();
        List<WagonRunDTO> wagonDTOS = Arrays.asList(WagonRunDTO.builder().wagonId(1L).index(1L).build(), WagonRunDTO.builder().wagonId(2L).index(2L).build());
        List<Wagon> wagonList = Arrays.asList(Wagon.builder().id(1L).build(), Wagon.builder().id(2L).build());

        when(lineRepository.findById(lineId)).thenReturn(Optional.ofNullable(line));
        when(statusRepository.findById(statusId)).thenReturn(Optional.ofNullable(status));
        when(lineRepository.findByWagonId(1L)).thenReturn(Optional.ofNullable(oldline));
        when(wagonRepository.saveAll(any(List.class))).thenReturn(wagonList);
        when(wagonRepository.findById(1L)).thenReturn(Optional.ofNullable(wagonList.get(0)));
        when(wagonRepository.findById(2L)).thenReturn(Optional.ofNullable(wagonList.get(1)));
        List<Wagon> result = wagonService.moveToEnd(wagonDTOS, lineId);


        assertNotNull(result);
        assertEquals(line, result.get(0).getLine());
        assertEquals(status, result.get(0).getStatus());
    }

    @Test
    void moveToTopException(){
        Long lineId = 1L;
        when(lineRepository.findByWagonId(lineId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> wagonService.moveToTop(any(), lineId));
    }

    @Test
    void toRzd() {
        Long lineId = 1L;
        Status statusRZD = Status.builder().name("RZD").id(3L).build();
        Status statusStation = Status.builder().name("STATION").id(2L).build();
        List<Wagon> wagonStation = Arrays.asList(Wagon.builder().status(statusStation).id(1L).index(1L).build(),
                Wagon.builder().status(statusStation).id(2L).index(2L).build(),
                Wagon.builder().status(statusStation).id(3L).index(3L).build(),
                Wagon.builder().status(statusStation).id(4L).index(4L).build());
        List<Wagon> wagonRZD = Arrays.asList(Wagon.builder().id(1L).status(statusRZD).index(null).line(null).build(),
                Wagon.builder().status(statusRZD).id(2L).index(null).line(null).build());

        when(statusRepository.findById(3L)).thenReturn(Optional.ofNullable(statusRZD));
        when(wagonRepository.findByLineIdOrderByIndex(lineId)).thenReturn(wagonStation);
        when(wagonRepository.saveAll(wagonStation)).thenReturn(wagonRZD);
        when(wagonRepository.findByLineIdOrderByIndex(lineId)).thenReturn(wagonStation);

        List<Wagon> result = wagonService.toRzd(1L, 2);

        assertAll("Проверка изменений полей при отправке",
                () -> assertEquals(statusRZD, result.get(0).getStatus()),
                () -> assertEquals(null, result.get(0).getIndex()),
                () -> assertEquals(null, result.get(0).getLine())
        );
    }


    @Test
    void getRzdWagon() {
        Long statusId = 3L;
        Status status = Status.builder().name("RZD").id(statusId).build();
        List<Wagon> wagonList = Arrays.asList(Wagon.builder().status(status).build(), Wagon.builder().status(status).build());
        when(wagonRepository.findByStatusId(1L)).thenReturn(wagonList);
        List<Wagon> result = wagonService.getAllWagonsForRun();

        assertEquals(statusId, result.get(0).getStatus().getId());
    }
}