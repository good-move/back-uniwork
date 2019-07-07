package ru.supernova.dto;

import java.util.Set;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import lombok.Value;
import ru.supernova.model.enums.EducationType;
import ru.supernova.model.enums.TopicType;

@Value
@ApiModel("Фильтр поиска исполнителей")
public class PersonSearchFilter {

    Set<@NotNull TopicType> topics;

    Set<@NotNull EducationType> educations;
}
