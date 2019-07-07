package ru.supernova.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.supernova.model.entity.MultiMediaFile;

@Repository
public interface MultiMediaFileRepository extends JpaRepository<MultiMediaFile, Long> {
    Optional<MultiMediaFile> findByExternalVideoUrl(String url);

    List<MultiMediaFile> findAllByExternalVideoUrlIn(List<String> urls);
}
