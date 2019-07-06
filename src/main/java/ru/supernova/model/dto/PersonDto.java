package ru.supernova.model.dto;

import java.time.LocalDate;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Value;
import ru.supernova.model.enums.PersonType;

@Value
@ApiModel(value = "Пользователь")
public class PersonDto {

    @ApiModelProperty(value = "Идентификатор пользователя")
    Long id;

    @NotBlank
    @ApiModelProperty(value = "Логин пользователя")
    String login;

    @NotBlank
    @ApiModelProperty(value = "Имя пользователя")
    String firstName;

    @NotBlank
    @ApiModelProperty(value = "Фамилия пользователя")
    String lastName;

    @ApiModelProperty(value = "Отчество пользователя")
    String middleName;

    @NotNull
    @ApiModelProperty(value = "Дата рождения пользователя")
    LocalDate birthDate;

    @ApiModelProperty(value = "Образование пользователя")
    String education;

    @ApiModelProperty(value = "Организация пользователя")
    String organization;

    @ApiModelProperty(value = "Навыки пользователя")
    Set<TopicDto> skills;

    @NotNull
    @ApiModelProperty(value = "Тип пользователя")
    PersonType type;
}
