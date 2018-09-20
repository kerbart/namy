package com.kerbart.namy.namy.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Maximums {
    Date date;
    Long number;
}
