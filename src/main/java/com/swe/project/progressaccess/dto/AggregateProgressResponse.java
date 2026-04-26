package com.swe.project.progressaccess.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AggregateProgressResponse {
    private String learnerId;
    private boolean newlyCompleted;
    private int completedTopicCount;
    private long totalHotspotClicks;
    private String lastCompletedTopicId;
}
