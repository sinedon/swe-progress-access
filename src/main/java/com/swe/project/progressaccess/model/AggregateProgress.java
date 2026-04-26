package com.swe.project.progressaccess.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "aggregate_progress")
public class AggregateProgress {

    @Id
    private String learnerId;

    private int completedTopicCount;

    private long totalHotspotClicks;

    private String lastCompletedTopicId;

    private LocalDateTime updatedAt;

    @PrePersist
    @PreUpdate
    public void updateTimestamp() {
        updatedAt = LocalDateTime.now();
    }
}
