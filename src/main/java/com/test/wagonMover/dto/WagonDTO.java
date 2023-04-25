package com.test.wagonMover.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WagonDTO {

    private Long wagonPassportId;

    private Long cargoId;

    private Double weight;

}
