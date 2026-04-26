package com.swe.project.progressaccess.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(
        name = "completed_topics",
        uniqueConstraints = @UniqueConstraint(columnNames = {"learner_id", "topic_id"})
)
public class CompletedTopic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "learner_id", nullable = false)
    private String learnerId;

    @Column(name = "topic_id", nullable = false)
    private String topicId;

    private LocalDateTime completedAt;

    @PrePersist
    public void prePersist() {
        if (completedAt == null) {
            completedAt = LocalDateTime.now();
        }
    }
}