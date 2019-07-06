package ru.supernova.service;

import ru.supernova.model.dto.CourseDto;
import ru.supernova.model.entity.Course;

public interface CourseService {
    Course createCourse(CourseDto courseDto);

    Course getCourse(Long id);
}
