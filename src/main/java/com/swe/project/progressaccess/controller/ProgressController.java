package com.swe.project.progressaccess.controller;

import org.springframework.web.bind.annotation.RestController;

import com.swe.project.progressaccess.dto.ClickRequest;
import com.swe.project.progressaccess.model.Progress;
import com.swe.project.progressaccess.service.ProgressService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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

    /*
        CRUD on learner progress table in PostgreSQL. Business verbs:
    POST /progress/click (record a click), GET /progress/{learnerId} (return all click
    sets), PATCH /progress/{learnerId}/topic/{topicId}/complete.
    */
}