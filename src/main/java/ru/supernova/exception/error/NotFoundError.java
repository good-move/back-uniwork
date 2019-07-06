package ru.supernova.exception.error;

import java.util.Collection;

import javax.annotation.Nonnull;

import ru.supernova.exception.http.ResourceType;

public class NotFoundError extends SimpleApiError {
    private ResourceType resourceType;

    private Collection<Long> identifiers;

    public NotFoundError() {
    }

    public NotFoundError(@Nonnull final ResourceType resourceType,
                         @Nonnull final Collection<Long> identifiers,
                         @Nonnull final String message) {
        super(message);
        this.resourceType = resourceType;
        this.identifiers = identifiers;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public Collection<Long> getIdentifiers() {
        return identifiers;
    }
}
