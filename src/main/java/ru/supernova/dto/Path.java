package ru.supernova.dto;

import lombok.Value;

@Value
public class Path {
    private Long id;

    public Path(Long id) {
        this.id = id;
    }
}
