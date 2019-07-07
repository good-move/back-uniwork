package ru.supernova.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.supernova.converter.PersonConverter;
import ru.supernova.dto.PersonSearchFilter;
import ru.supernova.exception.http.ResourceNotFoundException;
import ru.supernova.exception.http.ResourceType;
import ru.supernova.model.dto.PersonDto;
import ru.supernova.model.entity.Person;
import ru.supernova.model.entity.Topic;
import ru.supernova.repository.PersonRepository;
import ru.supernova.repository.TopicRepository;
import ru.supernova.specification.SpecificationFactory;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final PersonConverter personConverter;
    private final TopicRepository topicRepository;
    private final SpecificationFactory<Person, PersonSearchFilter> specificationFactory;

    @Override
    public Person createPerson(PersonDto person) {
        List<Topic> availableTopics = topicRepository.findAll();
        return personRepository.save(personConverter.fromApi(person, availableTopics));
    }

    @Override
    public Person getPerson(Long id) {
        return personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id, ResourceType.PERSON));
    }

    @Override
    public List<Person> search(PersonSearchFilter personSearchFilter) {
        return personRepository.findAll(specificationFactory.fromFilter(personSearchFilter));
    }
}
