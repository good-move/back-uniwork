package ru.supernova.service;

import java.util.List;

import ru.supernova.dto.PersonSearchFilter;
import ru.supernova.model.dto.PersonDto;
import ru.supernova.model.entity.Person;

public interface PersonService {
    Person createPerson(PersonDto person);
    Person getPerson(Long id);
    List<Person> search(PersonSearchFilter personSearchFilter);
}
