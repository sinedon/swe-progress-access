package com.swe.project.progressaccess.controller;

import com.swe.project.progressaccess.dto.ClickRequest;
import com.swe.project.progressaccess.model.Progress;
import com.swe.project.progressaccess.service.ProgressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/progress")
public class ProgressController {

    private final ProgressService service;

    public ProgressController(ProgressService service) {
        this.service = service;
    }

    @PostMapping("/click")
    public ResponseEntity<Void> recordClick(@RequestBody ClickRequest request) {
        service.recordClick(
                request.getLearnerId(),
                request.getTopicId(),
                request.getLabel()
        );

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{learnerId}")
    public List<Progress> getProgress(@PathVariable String learnerId) {
        return service.getProgress(learnerId);
    }

    @GetMapping("/{learnerId}/topic/{topicId}")
    public List<Progress> getProgressForTopic(@PathVariable String learnerId,
                                              @PathVariable String topicId) {
        return service.getProgressForTopic(learnerId, topicId);
    }

    @DeleteMapping("/{learnerId}/topic/{topicId}/latest")
    public ResponseEntity<Void> undoLatestClick(@PathVariable String learnerId,
                                                @PathVariable String topicId) {
        boolean removed = service.undoLatestClick(learnerId, topicId);

        if (!removed) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}