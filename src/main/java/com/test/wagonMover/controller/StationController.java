package com.test.wagonMover.controller;

import com.test.wagonMover.model.Line;
import com.test.wagonMover.model.Station;
import com.test.wagonMover.service.StationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("station")
@RequiredArgsConstructor
public class StationController {

    private final StationService stationService;


    @GetMapping()
    @Operation(summary = "Получить список всех станций")
    public ResponseEntity<List<Station>> getAllStation() {
        return ResponseEntity.ok(stationService.getAllStation());
    }


    @GetMapping("/line/")
    @Operation(summary = "Получаем список всех лайнов выбранной станции")
    public ResponseEntity<List<Line>> getLinesForStation(@RequestParam Long id) {
        return ResponseEntity.ok(stationService.getLineByStationId(id));
    }

    @PostMapping
    @Operation(summary = "Создаем станцию", description = "В качестве параметра передаем имя станции")
    public ResponseEntity<Station> createStation(@RequestParam String name) {
        return ResponseEntity.ok(stationService.createStation(name));
    }

    @DeleteMapping
    @Operation(summary = "Удаляение станции по id")
    public ResponseEntity deleteStation(@RequestParam Long id) {
        stationService.deleteStation(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity handleException(RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}
