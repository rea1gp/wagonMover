package com.test.wagonMover.service;
import com.test.wagonMover.model.Cargo;
import com.test.wagonMover.repository.CargoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CargoServiceTest {

    @Mock
    private CargoRepository cargoRepository;

    @InjectMocks
    private CargoService cargoService;

    @Test
    public void testGetAllCargo() {
        List<Cargo> cargoList = new ArrayList<>();
        cargoList.add(Cargo.builder().number(1L).name("Груз 1").build());
        cargoList.add(Cargo.builder().number(2L).name("Груз 2").build());
        when(cargoRepository.findAll()).thenReturn(cargoList);
        List<Cargo> result = cargoService.getAllCargo();

        assertEquals(cargoList, result);
    }

    @Test
    public void testCreateCargo() {
        Long number = 1L;
        String name = "Груз 1";
        Cargo cargo = Cargo.builder().number(number).name(name).build();
        when(cargoRepository.save(cargo)).thenReturn(cargo);
        Cargo result = cargoService.createCargo(number, name);

        assertEquals(cargo, result);
    }
}