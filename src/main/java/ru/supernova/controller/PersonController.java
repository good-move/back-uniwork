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
import ru.supernova.converter.PersonConverter;
import ru.supernova.model.dto.PersonDto;
import ru.supernova.service.PersonService;

@Api(value = "Контроллер для работы с пользователями")
@RestController
@RequestMapping("person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;
    private final PersonConverter personConverter;

    @PostMapping
    public PersonDto createPerson(@RequestBody @Valid @NotNull PersonDto personDto) {
        return personConverter.toApi(personService.createPerson(personDto));
    }

    @GetMapping("{id}")
    public PersonDto getPerson(@PathVariable Long id) {
        return personConverter.toApi(personService.getPerson(id));
    }
}
