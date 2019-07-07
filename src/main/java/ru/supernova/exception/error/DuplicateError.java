package ru.supernova.exception.error;

import java.util.Collection;

import javax.annotation.Nonnull;

import ru.supernova.exception.http.ResourceType;

public class DuplicateError extends SimpleApiError{
    private ResourceType resourceType;

    private Collection<String> urls;

    public DuplicateError() {
    }

    public DuplicateError(@Nonnull final ResourceType resourceType,
                          @Nonnull final Collection<String> urls,
                          @Nonnull final String message) {
        super(message);
        this.resourceType = resourceType;
        this.urls = urls;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public Collection<String> getUrls() {
        return urls;
    }
}
