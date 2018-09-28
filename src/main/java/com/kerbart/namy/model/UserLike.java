package com.kerbart.namy.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserLike {

    private String firstNameUUID;
    private String clientUUID;
    private LikeType likeType;
}
