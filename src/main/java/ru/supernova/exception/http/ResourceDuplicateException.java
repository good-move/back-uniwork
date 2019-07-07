package ru.supernova.exception.http;

import java.util.Collection;

import com.google.common.collect.ImmutableList;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.supernova.exception.UniworkException;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ResourceDuplicateException extends UniworkException {

    private final Collection<String> urls;
    private final ResourceType resourceType;

    public ResourceDuplicateException(final String identifier,
                                      ResourceType resourceType) {
        this(ImmutableList.of(identifier), resourceType);
    }

    public ResourceDuplicateException(Collection<String> urls, ResourceType type) {
        this.urls = urls;
        this.resourceType = type;
    }

    public ResourceType getType() {
        return resourceType;
    }

    @Override
    public String getMessage() {
        return String.format("Found duplicate [%s] with urls %s", resourceType, urls);
    }

    public Collection<String> getUrls() {
        return urls;
    }

}
