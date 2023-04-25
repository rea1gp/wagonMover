package com.test.wagonMover.controller;

import com.test.wagonMover.dto.WagonDTO;
import com.test.wagonMover.dto.WagonRunDTO;
import com.test.wagonMover.model.Wagon;
import com.test.wagonMover.service.WagonService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("wagon")
@RequiredArgsConstructor
public class WagonController {

    private final WagonService wagonService;


    @PostMapping("/new")
    @Operation(summary = "Создать вагон",
            description = "В теле указываем id вагона-пасспорта, id груза и вес")
    public ResponseEntity<Wagon> createWagon(@RequestBody WagonDTO wagonDTO) {
        return ResponseEntity.ok(wagonService.createWagonFromDto(wagonDTO));
    }

    @GetMapping("/for-run")
    @Operation(summary = "Получаем список вагонов, готовых к отправке.",
            description = "Созданные вагоны, находятся в статусе 'NEW'. Готовы к отправке")
    public ResponseEntity<List<Wagon>> getWagonsForRun() {
        return ResponseEntity.ok(wagonService.getAllWagonsForRun());
    }

    @PostMapping("/run/{lineId}")
    @Operation(summary = "Отправляем вагоны на указанный лайн.",
            description = "В теле запроса указываем id вагонов и порядок их отправки. После отправки статус вагона меняется на 'STATION'")
    public ResponseEntity<List<Wagon>> runWagonOnLine(@RequestBody List<WagonRunDTO> runDto, @PathVariable Long lineId) {
        return ResponseEntity.ok(wagonService.moveToEnd(runDto, lineId));
    }

    @PostMapping("/move/top/")
    @Operation(summary = "Перемещаем вагоны в начало состава другого лайна.",
            description = "В теле запроса указываем id вагонов и порядок их отправки")
    public ResponseEntity<List<Wagon>> moveToTopLine(@RequestBody List<WagonRunDTO> runDto, @RequestParam Long lineId) {
        return ResponseEntity.ok(wagonService.moveToTop(runDto, lineId));
    }

    @PostMapping("/move/end/")
    @Operation(summary = "Перемещаем вагоны в конец состава другого пути.",
            description = "В теле запроса указываем id вагонов и порядок их отправки")
    public ResponseEntity<List<Wagon>> moveToEndLine(@RequestBody List<WagonRunDTO> dto, @RequestParam Long lineId) {
        return ResponseEntity.ok(wagonService.moveToEnd(dto, lineId));
    }

    @PostMapping("/rzd/")
    @Operation(summary = "Отправление вагонов в сеть РЖД.",
            description = "Указываем id лайна и количество передаваемых вагонов с начала состава. Статус вагона меняется на 'RZD'")
    public ResponseEntity<List<Wagon>> toRzd(@RequestParam Long lineId, @RequestParam int count) {
        return ResponseEntity.ok(wagonService.toRzd(lineId, count));
    }

    @GetMapping("RZD")
    @Operation(summary = "Получить все вагоны ушедшие в сеть РЖД.")
    public ResponseEntity<List<Wagon>> getRzdWagons() {
        return ResponseEntity.ok(wagonService.getRzdWagon());
    }

    @DeleteMapping
    @Operation(summary = "Удаляение вагона по id")
    public ResponseEntity deleteWagon(@RequestParam Long id) {
        wagonService.deleteWagon(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity handleException(RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
