package ru.supernova.specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.Nonnull;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import ru.supernova.dto.PersonSearchFilter;
import ru.supernova.exception.http.EnumConvertException;
import ru.supernova.model.entity.Person;
import ru.supernova.model.entity.Topic;

@Component
public class PersonSpecification implements SpecificationFactory<Person, PersonSearchFilter> {
    @Override
    public Specification<Person> fromFilter(PersonSearchFilter filter) {
        return (root, query, cb) -> {
            final List<Predicate> expressions = new ArrayList<>();

            Optional.ofNullable(filter.getEducations())
                .ifPresent(educations -> expressions.add(root.get("education").in(educations)));

            Join<Person, Topic> personTopicJoin = root.join("skills", JoinType.LEFT);

            Optional.ofNullable(filter.getTopics())
                .ifPresent(topics -> expressions.add(personTopicJoin.get("name").in(topics)));

            return cb.and(expressions.toArray(new Predicate[0]));
        };
    }

    @Nonnull
    private <T extends Enum<T>> T convert(@Nonnull Enum<?> value, @Nonnull Class<T> to) {
        try {
            return Enum.valueOf(to, value.name());
        } catch (IllegalArgumentException e) {
            throw new EnumConvertException(e.getMessage(), e.getCause());
        }
    }
}
