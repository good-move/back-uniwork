package ru.supernova.exception.http;

import java.util.Collection;

import com.google.common.collect.ImmutableList;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.supernova.exception.UniworkException;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends UniworkException {

    private final Collection<Long> identifiers;
    private final ResourceType resourceType;

    public ResourceNotFoundException(final long identifier,
                                     ResourceType resourceType) {
        this(ImmutableList.of(identifier), resourceType);
    }

    public ResourceNotFoundException(Collection<Long> identifiers, ResourceType type) {
        this.identifiers = identifiers;
        this.resourceType = type;
    }

    public ResourceType getType() {
        return resourceType;
    }

    @Override
    public String getMessage() {
        return String.format("Failed to find [%s] with ids %s", resourceType, identifiers);
    }

    public Collection<Long> getIdentifiers() {
        return identifiers;
    }

}
