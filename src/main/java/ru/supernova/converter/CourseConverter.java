package ru.supernova.converter;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import ru.supernova.model.dto.CourseDto;
import ru.supernova.model.dto.TopicDto;
import ru.supernova.model.entity.Course;
import ru.supernova.model.entity.Person;
import ru.supernova.model.entity.Topic;
import ru.supernova.model.enums.TopicType;

@Component
@RequiredArgsConstructor
@ParametersAreNonnullByDefault
public class CourseConverter {

    private final TopicConverter topicConverter;
    private final MultiMediaFileConverter multiMediaFileConverter;

    @Nonnull
    public CourseDto toApi(Course course) {
        return new CourseDto(
            course.getId(),
            course.getPerson().getId(),
            course.getName(),
            course.getVideos().stream().map(multiMediaFileConverter::toApi).collect(Collectors.toSet()),
            course.getTopics().stream().map(topicConverter::toApi).collect(Collectors.toSet())
        );
    }

    @Nonnull
    public Course fromApi(CourseDto courseDto, List<Topic> topicList, Person person) {
        List<TopicType> topicTypes = CollectionUtils.emptyIfNull(courseDto.getTopics())
            .stream()
            .map(TopicDto::getTopicType)
            .collect(Collectors.toList());

        return new Course()
            .setName(courseDto.getName())
            .setPerson(person)
            .setTopics(topicList.stream()
                .filter(topic -> topicTypes.contains(topic.getName()))
                .collect(Collectors.toSet()))
            .addAllMultiMediaFiles(courseDto.getVideos().stream().map(multiMediaFileConverter::fromApi).collect(Collectors.toSet()));
    }
}
