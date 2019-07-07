package ru.supernova.specification;

import org.springframework.data.jpa.domain.Specification;

public interface SpecificationFactory<S, T> {
    Specification<S> fromFilter(T filter);
}
