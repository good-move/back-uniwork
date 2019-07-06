package ru.supernova.model.dto;

import java.util.Set;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Value;

@Value
@ApiModel(value = "Курс")
public class CourseDto {

    @ApiModelProperty("Идентификатор")
    Long id;

    @ApiModelProperty("Идентификатор пользователя")
    Long personId;

    @ApiModelProperty("Название")
    String name;

    @ApiModelProperty("Видео")
    Set<MultiMediaFileDto> videos;

    @ApiModelProperty("Топики")
    Set<TopicDto> topics;
}
