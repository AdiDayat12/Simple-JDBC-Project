package com.belajar.JDBC.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
public enum RoomType {
    NORMAL("Normal"),
    VIP("VIP"),
    VVIP("VVIP");

    private final String value;

    @Override
    public String toString (){
        return value;
    }
}
