package com.kerbart.namy.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Metadata {
    private Boolean composed;
    private Integer length;
    private Boolean male;
    private Boolean female;
}
