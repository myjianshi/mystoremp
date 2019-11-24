package edu.gyc.histore.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class HiTime {
    private String name;
    private LocalDateTime localDateTime;

    public HiTime(String name, LocalDateTime localDateTime) {
        this.name = name;
        this.localDateTime = localDateTime;
    }
}
