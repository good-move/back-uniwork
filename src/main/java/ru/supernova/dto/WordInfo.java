package ru.supernova.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class WordInfo {
    private final String word;
    private final BigDecimal startTime;

    @JsonCreator
    public WordInfo(@JsonProperty("word") String word,
                    @JsonProperty("startTime") BigDecimal startTime) {
        this.word = word;
        this.startTime = startTime;
    }
}
