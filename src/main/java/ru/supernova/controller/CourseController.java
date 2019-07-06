package ru.supernova.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.supernova.converter.CourseConverter;
import ru.supernova.model.dto.CourseDto;
import ru.supernova.service.CourseService;

@Api(value = "Контроллер для работы с курсами")
@RestController
@RequestMapping("course")
@RequiredArgsConstructor
public class CourseController {
    private final CourseConverter courseConverter;
    private final CourseService courseService;

    @PostMapping
    public CourseDto createCourse(@RequestBody @Valid @NotNull CourseDto courseDto) {
        return courseConverter.toApi(courseService.createCourse(courseDto));
    }

    @GetMapping("{id}")
    public CourseDto getCourse(@PathVariable Long id) {
        return courseConverter.toApi(courseService.getCourse(id));
    }
}
