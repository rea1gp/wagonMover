package com.test.wagonMover.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WagonPassportDTO {

    private Long number;

    private Long typeId;

    private Double weight;

    private Double capacity;
}
