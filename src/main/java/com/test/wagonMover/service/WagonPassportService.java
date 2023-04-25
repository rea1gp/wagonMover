package com.test.wagonMover.service;

import com.test.wagonMover.dto.WagonPassportDTO;
import com.test.wagonMover.model.Type;
import com.test.wagonMover.model.WagonPassport;
import com.test.wagonMover.repository.TypeRepository;
import com.test.wagonMover.repository.WagonPassportRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class WagonPassportService {

    private final WagonPassportRepository wagonPassportRepository;

    private final TypeRepository typeRepository;

    public List<WagonPassport> getAll() {
        return wagonPassportRepository.findByWagonIsNullOrderById();
    }

    public WagonPassport createWagonPassport(WagonPassportDTO wpDto) {
        Type type = typeRepository.findById(wpDto.getTypeId()).orElseThrow(() ->
                new EntityNotFoundException("Тип с переданным id не найден"));
        WagonPassport wagonPassport = WagonPassport.builder()
                .type(type)
                .number(wpDto.getNumber())
                .weight(wpDto.getWeight())
                .capacity(wpDto.getCapacity())
                .build();
        log.info("Создание вагона-паспорт с номером " + wpDto.getNumber());
        return wagonPassportRepository.save(wagonPassport);
    }

    public void deletePassport(Long id) {
        wagonPassportRepository.deleteById(id);
    }
}
