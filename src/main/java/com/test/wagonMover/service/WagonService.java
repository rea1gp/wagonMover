package com.test.wagonMover.service;

import com.test.wagonMover.dto.WagonDTO;
import com.test.wagonMover.dto.WagonRunDTO;
import com.test.wagonMover.model.*;
import com.test.wagonMover.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class WagonService {

    private final WagonRepository wagonRepository;

    private final WagonPassportRepository wagonPassportRepository;

    private final CargoRepository cargoRepository;

    private final StatusRepository statusRepository;

    private final LineRepository lineRepository;

    public Wagon createWagonFromDto(WagonDTO wagonDTO) {
        WagonPassport passport = wagonPassportRepository.findById(wagonDTO.getWagonPassportId()).orElseThrow(() ->
                new EntityNotFoundException("Вагон-паспорт с данным id не найден."));
        if (passport.getCapacity() < wagonDTO.getWeight())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Указанный вес больше допустимого.");
        Cargo cargo = cargoRepository.findById(wagonDTO.getCargoId()).orElseThrow();
        Status status = statusRepository.findById(1L).get();

        Wagon wagon = Wagon.builder()
                .wagonPassport(passport)
                .cargo(cargo)
                .cargoWeight(wagonDTO.getWeight())
                .status(status).build();
        log.info("Создан и загружен новый вагон из вагона-паспорта(бланка) с номером " + wagonDTO.getWagonPassportId());

        return wagonRepository.save(wagon);
    }

    public List<Wagon> getAllWagonsForRun() {
        return wagonRepository.findByStatusId(1L);
    }

    public List<Wagon> moveToEnd(List<WagonRunDTO> dtos, Long lineId) {
        Line line = lineRepository.findById(lineId).orElseThrow(() ->
                new EntityNotFoundException("Указанный лайн не найден"));
        AtomicLong size = new AtomicLong(wagonRepository.countByLineId(lineId) + 1);
        Status stationStatus = statusRepository.findById(2L).get();
        Optional<Line> oldLine = lineRepository.findByWagonId(dtos.get(0).getWagonId());

        List<Wagon> newWagons = dtos.stream().sorted(Comparator.comparingLong(WagonRunDTO::getIndex)).map(dto -> {
            Wagon wagon = wagonRepository.findById(dto.getWagonId()).orElseThrow(() ->
                    new EntityNotFoundException("Вагон с id " + dto.getWagonId() + " не найден"));
            wagon.setLine(line);
            wagon.setIndex(size.getAndIncrement());
            wagon.setStatus(stationStatus);
            return wagon;
        }).collect(Collectors.toList());
        wagonRepository.saveAll(newWagons);
        log.info("Поступившие вагоны перемещены в хвост состава пути " + lineId);

        if (oldLine.isPresent() && oldLine.get().getId() != lineId) {
            updateIndexes(oldLine.get().getId());
        }

        return newWagons;
    }

    public List<Wagon> moveToTop(List<WagonRunDTO> dtos, Long lineId) {
        Line line = lineRepository.findById(lineId).orElseThrow(() ->
                new EntityNotFoundException("Указанный лайн не найден"));
        List<Wagon> oldWagons = wagonRepository.findByLineIdOrderByIndex(lineId);
        Optional<Line> oldLine = lineRepository.findByWagonId(dtos.get(0).getWagonId());

        //Обновновляем индекс уже существующих вагонов на лайне
        if (!oldWagons.isEmpty()) {
            oldWagons.forEach(wagon -> wagon.setIndex(wagon.getIndex() + dtos.size()));
            wagonRepository.saveAll(oldWagons);
            log.info("Сдвиг старых вагонов на количество вновь прибывших на пути  " + lineId);
        }

        //Добавляем на лайн новые вагоны
        AtomicLong size = new AtomicLong(1);

        List<Wagon> newWagons = dtos.stream().sorted(Comparator.comparingLong(WagonRunDTO::getIndex)).map(dto -> {
            Wagon wagon = wagonRepository.findById(dto.getWagonId()).orElseThrow(() ->
                    new EntityNotFoundException("Вагона с id " + dto.getWagonId() + " не найден"));
            wagon.setLine(line);
            wagon.setIndex(size.getAndIncrement());
            return wagon;
        }).collect(Collectors.toList());
        wagonRepository.saveAll(newWagons);

        log.info("Вагоны пермещены в начало состава на пути " + lineId);


        //Перераспределяем индексы на старом лайне
        if (oldLine.isPresent() && oldLine.get().getId() != lineId) {
            updateIndexes(oldLine.get().getId());
        }

        return newWagons;
    }

    public List<Wagon> toRzd(Long lineId, int count) {
        Status rzdStatus = statusRepository.findById(3L).get();
        List<Wagon> wagons = wagonRepository.findByLineIdOrderByIndex(lineId).stream()
                .sorted(Comparator.comparingLong(Wagon::getIndex))
                .limit(count)
                .peek(wagon -> {
                    wagon.setIndex(null);
                    wagon.setLine(null);
                    wagon.setStatus(rzdStatus);
                }).collect(Collectors.toList());
        wagonRepository.saveAll(wagons);
        log.info(count + " вагона из начала состава пути " + lineId + " отправилист в сеть РЖД");

        updateIndexes(lineId);
        return wagons;
    }

    private void updateIndexes(Long lineId) {
        AtomicLong size = new AtomicLong(1);
        List<Wagon> oldLineWagons = wagonRepository.findByLineIdOrderByIndex(lineId);
        if (!oldLineWagons.isEmpty()) {
            oldLineWagons.stream()
                    .filter(w -> w.getIndex() != null)
                    .sorted(Comparator.comparingLong(Wagon::getIndex)).forEach(wagon -> wagon.setIndex(size.getAndIncrement()));
            wagonRepository.saveAll(oldLineWagons);
            log.info("Переиндексация вагонов произошла на пути " + lineId);
        }
    }

    public List<Wagon> getRzdWagon() {
        log.info("Получение всех вагонов в сети РЖД");
        return wagonRepository.findByStatusId(3L);
    }

    public void deleteWagon(Long id) {
        Optional<Long> line = wagonRepository.findLineIdByWagonId(id);
        wagonRepository.deleteById(id);
        if (line.isPresent()) {
            updateIndexes(line.get());
        }
    }
}
