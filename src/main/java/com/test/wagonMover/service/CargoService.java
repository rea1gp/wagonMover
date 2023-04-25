package com.test.wagonMover.service;

import com.test.wagonMover.model.Cargo;
import com.test.wagonMover.repository.CargoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CargoService {

    private final CargoRepository cargoRepository;

    public List<Cargo> getAllCargo() {
        return cargoRepository.findAll();
    }

    public Cargo createCargo(Long number, String name) {
        log.info("Создание груза. Номер " + number + " имя " + name);
        return cargoRepository.save(Cargo.builder().number(number).name(name).build());
    }

    public void deleteCargo(Long id) {
        cargoRepository.deleteById(id);
    }
}
