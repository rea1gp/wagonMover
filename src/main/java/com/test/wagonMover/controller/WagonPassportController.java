package com.test.wagonMover.controller;

import com.test.wagonMover.dto.WagonPassportDTO;
import com.test.wagonMover.model.WagonPassport;
import com.test.wagonMover.service.WagonPassportService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("wagon-passport")
@RequiredArgsConstructor
public class WagonPassportController {

    private final WagonPassportService wagonPassportService;


    @GetMapping
    @Operation(summary = "Получаем список всех паспортов-вагонов(бланков), готовых к загрузке")
    public ResponseEntity<List<WagonPassport>> getAll() {
        return ResponseEntity.ok(wagonPassportService.getAll());
    }

    @PostMapping()
    @Operation(summary = "Создаем новый вагон-паспорт(бланк)",
            description = "В тело запроса передаем dto с номером, id типа груза, весом вагона и его вместимосью.")
    public ResponseEntity<WagonPassport> createWagonPassport(WagonPassportDTO wpDto) {
        return ResponseEntity.ok(wagonPassportService.createWagonPassport(wpDto));
    }

    @DeleteMapping
    @Operation(summary = "Удаляение паспорта-вагона по id")
    public ResponseEntity deleteLine(@RequestParam Long id) {
        wagonPassportService.deletePassport(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity handleException(RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}
