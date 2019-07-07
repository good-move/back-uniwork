package ru.supernova.service;

import java.util.List;

import ru.supernova.model.enums.EducationType;

public interface EducationService {
    List<EducationType> getEducation();
}
