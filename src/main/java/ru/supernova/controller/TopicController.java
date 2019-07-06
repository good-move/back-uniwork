package ru.supernova.controller;

import java.util.List;
import java.util.stream.Collectors;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.supernova.converter.TopicConverter;
import ru.supernova.model.dto.TopicDto;
import ru.supernova.service.TopicService;

@Api(value = "Контроллер для работы с топиками")
@RestController
@RequestMapping("topic")
@RequiredArgsConstructor
public class TopicController {

    private final TopicService topicService;
    private final TopicConverter topicConverter;

    @GetMapping
    public List<TopicDto> getTopics() {
        return topicService.getTopics().stream().map(topicConverter::toApi).collect(Collectors.toList());
    }
}
