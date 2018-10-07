package com.kerbart.namy.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class BestYear {
    private LocalDate date;
    private Long number;
}
