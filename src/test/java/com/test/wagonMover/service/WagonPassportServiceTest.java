package com.test.wagonMover.service;

import com.test.wagonMover.dto.WagonPassportDTO;
import com.test.wagonMover.model.Type;
import com.test.wagonMover.model.WagonPassport;
import com.test.wagonMover.repository.TypeRepository;
import com.test.wagonMover.repository.WagonPassportRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class WagonPassportServiceTest {

    @Mock
    private WagonPassportRepository wagonPassportRepository;

    @Mock
    private TypeRepository typeRepository;

    @InjectMocks
    private WagonPassportService wagonPassportService;

    @Test
    void getAll() {
        List<WagonPassport> passportList = Arrays.asList(new WagonPassport(), new WagonPassport());
        when(wagonPassportRepository.findByWagonIsNullOrderById()).thenReturn(passportList);
        List<WagonPassport> result = wagonPassportService.getAll();

        assertEquals(passportList, result);
    }

    @Test
    void createWagonPassport() {
        Long typeId = 1L;
        Type type = Type.builder().name("test").id(typeId).build();
        WagonPassportDTO wpDto = WagonPassportDTO.builder().number(111L).typeId(typeId).weight(100.00).capacity(110.00).build();
        when(typeRepository.findById(typeId)).thenReturn(Optional.ofNullable(type));
        when(wagonPassportRepository.save(any(WagonPassport.class))).thenReturn(new WagonPassport());
        WagonPassport result = wagonPassportService.createWagonPassport(wpDto);

        assertNotNull(result);
    }

    @Test
    public void createWagonPassportException() {
        Long typeId = 1L;
        WagonPassportDTO wpDto = WagonPassportDTO.builder().number(111L).typeId(typeId).weight(100.00).capacity(110.00).build();
        when(typeRepository.findById(typeId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> wagonPassportService.createWagonPassport(wpDto));
    }
}