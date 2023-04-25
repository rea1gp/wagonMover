package com.test.wagonMover.controller;

import com.test.wagonMover.model.Line;
import com.test.wagonMover.model.Wagon;
import com.test.wagonMover.service.LineService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("line")
@RequiredArgsConstructor
public class LineController {

    private final LineService lineService;

    @GetMapping
    @Operation(summary = "Получаем список всех лайнов")
    public ResponseEntity<List<Line>> getAll() {
        return ResponseEntity.ok(lineService.getAllLine());
    }


    @GetMapping("/wagon")
    @Operation(summary = "Получаем список всех вагонов, находящихся на указанном лайне")
    public ResponseEntity<List<Wagon>> getLineWagons(@RequestParam Long lineId) {
        return ResponseEntity.ok(lineService.getLineWagons(lineId));
    }

    @PostMapping
    @Operation(summary = "Создание нового лайна",
            description = "В качестве парамера передаем номер лайна и id станции лайна")
    public ResponseEntity<Line> createLine(@RequestParam Long number, @RequestParam Long stationId) {
        return ResponseEntity.ok(lineService.createLine(number, stationId));
    }

    @DeleteMapping
    @Operation(summary = "Удаляение лайна по id")
    public ResponseEntity deleteLine(@RequestParam Long id) {
        lineService.deleteLine(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity handleException(RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
