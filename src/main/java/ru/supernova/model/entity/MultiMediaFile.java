package ru.supernova.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import ru.supernova.model.enums.StatusType;

@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@ToString(exclude = "course")
@Entity
@Table(name = "multi_media_file")
public class MultiMediaFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @Column
    private String externalVideoUrl;

    @Column
    private String internalAudioUrl;

    @Column
    @Enumerated(value = EnumType.STRING)
    private StatusType status;

    @Column
    private String message;
}
