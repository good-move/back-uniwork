package ru.supernova.controller;

import java.util.List;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.supernova.model.enums.EducationType;
import ru.supernova.service.EducationService;

@Api(value = "Контроллер для работы с курсами")
@RestController
@RequestMapping("education")
@RequiredArgsConstructor
public class EducationController {

    private final EducationService educationService;

    @GetMapping
    List<EducationType> getEducation() {
        return educationService.getEducation();
    }
}
