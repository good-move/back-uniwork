package ru.supernova.converter;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import ru.supernova.model.dto.PersonDto;
import ru.supernova.model.dto.TopicDto;
import ru.supernova.model.entity.Person;
import ru.supernova.model.entity.Topic;
import ru.supernova.model.enums.TopicType;

@Component
@ParametersAreNonnullByDefault
@RequiredArgsConstructor
public class PersonConverter {

    private final TopicConverter topicConverter;

    @Nonnull
    public PersonDto toApi(Person source) {
        return new PersonDto(
            source.getId(),
            source.getLogin(),
            source.getFirstName(),
            source.getLastName(),
            source.getMiddleName(),
            source.getBirthDate(),
            source.getEducation(),
            source.getOrganization(),
            source.getSkills().stream().map(topicConverter::toApi).collect(Collectors.toSet()),
            source.getType()
        );
    }

    @Nonnull
    public Person fromApi(PersonDto personDto, List<Topic> topicList) {
        List<TopicType> topicTypes = CollectionUtils.emptyIfNull(personDto.getSkills())
            .stream()
            .map(TopicDto::getTopicType)
            .collect(Collectors.toList());

        return new Person()
            .setLogin(personDto.getLogin())
            .setFirstName(personDto.getFirstName())
            .setLastName(personDto.getLastName())
            .setMiddleName(personDto.getMiddleName())
            .setBirthDate(personDto.getBirthDate())
            .setEducation(personDto.getEducation())
            .setOrganization(personDto.getOrganization())
            .setSkills(topicList.stream()
                .filter(topic -> topicTypes.contains(topic.getType()))
                .collect(Collectors.toSet()))
            .setType(personDto.getType());
    }
}
