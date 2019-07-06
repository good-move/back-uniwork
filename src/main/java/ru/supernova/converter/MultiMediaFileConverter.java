package ru.supernova.converter;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.supernova.model.dto.MultiMediaFileDto;
import ru.supernova.model.entity.MultiMediaFile;
import ru.supernova.model.enums.StatusType;

@Component
@RequiredArgsConstructor
@ParametersAreNonnullByDefault
public class MultiMediaFileConverter {

    @Nonnull
    public MultiMediaFileDto toApi(MultiMediaFile multiMediaFile) {
        return new MultiMediaFileDto(multiMediaFile.getId(),
            multiMediaFile.getExternalVideoUrl(),
            multiMediaFile.getTitle());
    }

    @Nonnull
    public MultiMediaFile fromApi(MultiMediaFileDto multiMediaFileDto) {
        return new MultiMediaFile()
            .setExternalVideoUrl(multiMediaFileDto.getUrl())
            .setTitle(multiMediaFileDto.getTitle())
            .setStatus(StatusType.CREATED);
    }
}
