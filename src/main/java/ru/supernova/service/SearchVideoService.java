package ru.supernova.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nonnull;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.supernova.dto.SearchResult;

@Service
@RequiredArgsConstructor
public class SearchVideoService {

    private final RestTemplate restTemplate;

    public List<SearchResult> search(String query, Long videoId) {
        Map<String, Set<String>> multiValueMap = new HashMap<>();
        multiValueMap.put("query", Collections.singleton(query));
        if (videoId != null) {
            multiValueMap.put("id", Collections.singleton(String.valueOf(videoId)));
        }
        ResponseEntity<List<SearchResult>> responseEntity = restTemplate.exchange(
            buildUrl(multiValueMap, "search"),
            HttpMethod.GET,
            createRequestEntity(MediaType.APPLICATION_JSON),
            new ParameterizedTypeReference<List<SearchResult>>() {
            }
        );
        return responseEntity.getBody();
    }

    public SearchResult get(Long videoId) {
        return restTemplate.exchange(
            buildUrl(Collections.emptyMap(), "video", String.valueOf(videoId), "details"),
            HttpMethod.GET,
            createRequestEntity(MediaType.APPLICATION_JSON),
            SearchResult.class
        ).getBody();
    }

    private String buildUrl(@Nonnull Map<String, Set<String>> paramMap, @Nonnull String... uriFragments) {
        MultiValueMap<String, String> paramMultiMap = new LinkedMultiValueMap<>();

        paramMap.keySet().forEach(key -> paramMultiMap.put(key, new ArrayList<>(paramMap.get(key))));

        return UriComponentsBuilder
            .fromHttpUrl("http://localhost:9090")
            .queryParams(paramMultiMap)
            .pathSegment(uriFragments)
            .toUriString();
    }

    private HttpHeaders createHttpHeaders(final MediaType mediaType) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(mediaType);
        return httpHeaders;
    }

    private HttpEntity<?> createRequestEntity(MediaType mediaType) {
        HttpHeaders httpHeaders = createHttpHeaders(mediaType);

        return new HttpEntity<>(httpHeaders);
    }
}
