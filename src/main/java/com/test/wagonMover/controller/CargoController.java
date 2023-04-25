package com.test.wagonMover.controller;

import com.test.wagonMover.model.Cargo;
import com.test.wagonMover.service.CargoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cargo")
@RequiredArgsConstructor
public class CargoController {

    private final CargoService cargoService;


    @GetMapping
    @Operation(summary = "Получаем список всех грузов")
    public ResponseEntity<List<Cargo>> getAllCargo() {
        return ResponseEntity.ok(cargoService.getAllCargo());
    }

    @PostMapping
    @Operation(summary = "Создаем груз",
            description = "В качестве парамеров передаем номер и имя груза")
    public ResponseEntity<Cargo> createCargo(@RequestParam Long number, @RequestParam String name) {
        return ResponseEntity.ok(cargoService.createCargo(number, name));
    }

    @DeleteMapping
    @Operation(summary = "Удаляение груза по id")
    public ResponseEntity deleteCargo(@RequestParam Long id) {
        cargoService.deleteCargo(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity handleException(RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}
