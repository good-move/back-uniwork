package ru.supernova.service;

import ru.supernova.model.dto.PersonDto;
import ru.supernova.model.entity.Person;

public interface PersonService {
    Person createPerson(PersonDto person);
    Person getPerson(Long id);
}
