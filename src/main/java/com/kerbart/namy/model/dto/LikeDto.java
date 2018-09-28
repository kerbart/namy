package com.kerbart.namy.model.dto;

import com.kerbart.namy.model.LikeType;
import lombok.Data;

@Data
public class LikeDto {

    private String userId;
    private String firstNameId;
    private LikeType likeType;
}
