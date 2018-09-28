package com.kerbart.namy.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Occurence {

    private LocalDate date;
    private Long number;
    private Boolean male;
    private Boolean female;

}
