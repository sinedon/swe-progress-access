package com.swe.project.progressaccess.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProgressSummaryResponse {
    private String learnerId;
    private long totalClickCount;
    private List<String> clickedLabels;
    private Map<String, Long> labelCounts;
}
