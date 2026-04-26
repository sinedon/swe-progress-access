package com.swe.project.progressaccess.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProgressResponse {
    private Long id;

    private String learnerId;
    private String topicId;
    private String label;

    private LocalDateTime createdAt;
}