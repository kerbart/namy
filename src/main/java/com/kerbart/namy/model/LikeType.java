package com.kerbart.namy.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum LikeType {
    UNLIKED(-1),
    DONTKNOW(0),
    LIKED(1);

    private int value;

    public int getValue() {
        return value;
    }
}
