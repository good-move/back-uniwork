package ru.supernova.service;

import java.util.Arrays;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.supernova.model.enums.EducationType;

@Service
@RequiredArgsConstructor
public class EducationServiceImpl implements EducationService {

    @Override
    public List<EducationType> getEducation() {
        return Arrays.asList(EducationType.values());
    }
}
