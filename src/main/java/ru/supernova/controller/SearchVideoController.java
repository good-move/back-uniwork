package ru.supernova.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.supernova.dto.SearchResult;
import ru.supernova.service.SearchVideoService;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
public class SearchVideoController {

    private final SearchVideoService searchVideoService;

    @GetMapping("/search")
    public List<SearchResult> search(
        @RequestParam(name = "query") String query,
        @RequestParam(name = "id", required = false) Long videoId
    ) {
        return searchVideoService.search(query, videoId);
    }

    @GetMapping("/video/{videoId}/details")
    public SearchResult getAllWordsBy(@PathVariable long videoId) {
        return searchVideoService.get(videoId);
    }
}
