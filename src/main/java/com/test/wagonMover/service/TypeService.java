package com.test.wagonMover.service;

import com.test.wagonMover.model.Type;
import com.test.wagonMover.repository.TypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TypeService {

    private final TypeRepository typeRepository;

    public Type createType(String type) {
        log.info("Создание типа груза " + type);
        return typeRepository.save(Type.builder().name(type).build());
    }

    public List<Type> getAllType() {
        return typeRepository.findAll();
    }

    public void deleteType(Long id){
        typeRepository.deleteById(id);
    }

}
