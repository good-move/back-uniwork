package ru.supernova.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Value;

@Value
@ApiModel("Видео")
public class MultiMediaFileDto {

    @ApiModelProperty("Идентификатор")
    Long id;

    @ApiModelProperty("URL до файла")
    String url;

    @ApiModelProperty("Заголовок")
    String title;
}
