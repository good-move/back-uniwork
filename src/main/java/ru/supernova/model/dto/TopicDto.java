package ru.supernova.model.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Value;
import ru.supernova.model.enums.TopicType;

@Value
@ApiModel("Топик")
public class TopicDto {
    @ApiModelProperty(value = "Идентификатор топика")
    Long id;

    @NotBlank
    @ApiModelProperty(value = "Тип топика")
    TopicType topicType;
}
