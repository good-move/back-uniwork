package ru.supernova.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.supernova.converter.CourseConverter;
import ru.supernova.exception.http.ResourceNotFoundException;
import ru.supernova.exception.http.ResourceType;
import ru.supernova.model.dto.CourseDto;
import ru.supernova.model.entity.Course;
import ru.supernova.model.entity.Person;
import ru.supernova.model.entity.Topic;
import ru.supernova.repository.CourseRepository;
import ru.supernova.repository.TopicRepository;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final TopicRepository topicRepository;
    private final CourseRepository courseRepository;
    private final CourseConverter courseConverter;
    private final PersonService personService;

    @Override
    public Course createCourse(CourseDto courseDto) {
        List<Topic> availableTopics = topicRepository.findAll();
        Person person = personService.getPerson(courseDto.getPersonId());
        return courseRepository.save(courseConverter.fromApi(courseDto, availableTopics, person));
    }

    @Override
    public Course getCourse(Long id) {
        return courseRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException(id, ResourceType.COURSE)
        );
    }
}
