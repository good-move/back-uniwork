package ru.supernova.controller;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.google.common.collect.ImmutableList;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.supernova.converter.PersonConverter;
import ru.supernova.dto.PersonSearchFilter;
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

    @PutMapping("search")
    public List<PersonDto> search(@RequestBody @Valid PersonSearchFilter personSearchFilter) {
        if (Stream.of(
            personSearchFilter.getEducations(),
            personSearchFilter.getTopics()
        ).anyMatch(this::isNonNullAndEmpty)) {
            return ImmutableList.of();
        }
        return personService.search(personSearchFilter).stream().map(personConverter::toApi).collect(Collectors.toList());
    }

    private <T> boolean isNonNullAndEmpty(Collection<T> collection) {
        return collection != null && collection.isEmpty();
    }
}
