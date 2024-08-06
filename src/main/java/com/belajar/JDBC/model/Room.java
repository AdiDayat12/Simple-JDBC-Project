package com.belajar.JDBC.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class Room {
    private int id;
    private RoomType type;
    private RoomStatus status;

    public Room (RoomType type, RoomStatus status){
        this.type = type;
        this.status = status;
    }
    @Override
    public String toString (){
        return id + " " + type + " " + status;
    }
}
