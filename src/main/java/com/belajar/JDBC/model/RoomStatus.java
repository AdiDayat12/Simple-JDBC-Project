package com.belajar.JDBC.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
public enum RoomStatus {
    EMPTY("empty"),
    BOOKED("booked"),
    BEING_REPAIRED("being repaired");

    private final String value;
    @Override
    public String toString() {
        return value;
    }
}
