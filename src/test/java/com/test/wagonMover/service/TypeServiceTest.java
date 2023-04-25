package com.test.wagonMover.service;

import com.test.wagonMover.model.Type;
import com.test.wagonMover.repository.TypeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class TypeServiceTest {

    @Mock
    private TypeRepository typeRepository;

    @InjectMocks
    private TypeService typeService;

    @Test
    void createType() {
        String name = "Type1";
        Type type = Type.builder().name(name).id(1L).build();
        when(typeRepository.save(any(Type.class))).thenReturn(type);
        Type result = typeService.createType(name);

        assertEquals(type, result);
    }

    @Test
    void getAllType() {
        List<Type> typeList = Arrays.asList(new Type(), new Type());
        when(typeRepository.findAll()).thenReturn(typeList);
        List<Type> result = typeService.getAllType();

        assertEquals(typeList, result);
    }
}