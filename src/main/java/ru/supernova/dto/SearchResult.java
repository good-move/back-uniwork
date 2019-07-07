package ru.supernova.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class SearchResult {
    private final Long id;
    private final String url;
    private final List<WordInfo> words;

    @JsonCreator
    public SearchResult(@JsonProperty("id") Long id,
                        @JsonProperty("url") String url,
                        @JsonProperty("words") List<WordInfo> words) {
        this.id = id;
        this.url = url;
        this.words = words;
    }
}
