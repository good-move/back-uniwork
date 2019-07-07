package ru.supernova.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.supernova.converter.CourseConverter;
import ru.supernova.dto.Path;
import ru.supernova.exception.http.ResourceDuplicateException;
import ru.supernova.exception.http.ResourceNotFoundException;
import ru.supernova.exception.http.ResourceType;
import ru.supernova.model.dto.CourseDto;
import ru.supernova.model.dto.MultiMediaFileDto;
import ru.supernova.model.entity.Course;
import ru.supernova.model.entity.MultiMediaFile;
import ru.supernova.model.entity.Person;
import ru.supernova.model.entity.Topic;
import ru.supernova.repository.CourseRepository;
import ru.supernova.repository.MultiMediaFileRepository;
import ru.supernova.repository.TopicRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourseServiceImpl implements CourseService {

    private final TopicRepository topicRepository;
    private final CourseRepository courseRepository;
    private final MultiMediaFileRepository multiMediaFileRepository;
    private final CourseConverter courseConverter;
    private final PersonService personService;
    private final RestTemplate restTemplate;

    @Override
    public Course createCourse(CourseDto courseDto) {
        List<Topic> availableTopics = topicRepository.findAll();
        Person person = personService.getPerson(courseDto.getPersonId());
        List<MultiMediaFile> multiMediaFiles = multiMediaFileRepository.findAllByExternalVideoUrlIn(courseDto.getVideos().stream()
            .map(MultiMediaFileDto::getUrl).collect(Collectors.toList()));
        if (multiMediaFiles.size() != 0) {
            throw new ResourceDuplicateException(
                multiMediaFiles.stream().map(MultiMediaFile::getExternalVideoUrl).collect(Collectors.toList()),
                ResourceType.VIDEO
            );
        }

        Course course = courseRepository.save(courseConverter.fromApi(courseDto, availableTopics, person));
        ResponseEntity<String> responseEntity = restTemplate.exchange(buildUrl("process"),
            HttpMethod.POST,
            createRequestEntity(course.getVideos().stream()
                .map(video -> new Path(video.getId())).collect(Collectors.toList()), MediaType.APPLICATION_JSON),
            String.class);
        log.info(Objects.requireNonNull(responseEntity.getBody()));
        return course;
    }

    @Override
    public Course getCourse(Long id) {
        return courseRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException(id, ResourceType.COURSE)
        );
    }

    private String buildUrl(@Nonnull String... uriFragments) {
        return UriComponentsBuilder
            .fromHttpUrl("http://localhost:9090")
            .pathSegment(uriFragments)
            .toUriString();
    }

    private HttpHeaders createHttpHeaders(final MediaType mediaType) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(mediaType);
        return httpHeaders;
    }

    private HttpEntity<?> createRequestEntity(List<Path> path, MediaType mediaType) {
        HttpHeaders httpHeaders = createHttpHeaders(mediaType);

        return new HttpEntity<>(path, httpHeaders);
    }
}
