package ru.supernova.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.supernova.model.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
