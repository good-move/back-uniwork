package ru.supernova.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.supernova.model.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
