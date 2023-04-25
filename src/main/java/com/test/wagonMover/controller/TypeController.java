package com.test.wagonMover.controller;

import com.test.wagonMover.model.Type;
import com.test.wagonMover.service.TypeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("type")
@RequiredArgsConstructor
public class TypeController {

    private final TypeService typeService;

    @GetMapping
    @Operation(summary = "Получаем список всех типов")
    public ResponseEntity<List<Type>> getAll() {
        return ResponseEntity.ok(typeService.getAllType());
    }


    @PostMapping
    @Operation(summary = "Операция создания типа",
            description = "В качестве парамера передаем имя типа")
    public ResponseEntity<Type> createType(@RequestParam String type) {
        return ResponseEntity.ok(typeService.createType(type));
    }

    @DeleteMapping
    @Operation(summary = "Удаляение типа по id")
    public ResponseEntity deleteType(@RequestParam Long id) {
        typeService.deleteType(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity handleException(RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
