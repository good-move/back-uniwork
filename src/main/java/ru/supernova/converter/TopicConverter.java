package ru.supernova.converter;

import javax.annotation.ParametersAreNonnullByDefault;

import org.springframework.stereotype.Component;
import ru.supernova.model.dto.TopicDto;
import ru.supernova.model.entity.Topic;

@Component
@ParametersAreNonnullByDefault
public class TopicConverter {

    public TopicDto toApi(Topic source) {
        return new TopicDto(
            source.getId(),
            source.getType()
        );
    }
}
