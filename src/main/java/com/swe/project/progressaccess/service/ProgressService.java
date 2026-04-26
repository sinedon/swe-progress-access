package com.swe.project.progressaccess.service;

import com.swe.project.progressaccess.model.Progress;
import com.swe.project.progressaccess.repository.ProgressRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProgressService {

    private final ProgressRepository repository;

    public ProgressService(ProgressRepository repository) {
        this.repository = repository;
    }

    public void recordClick(String learnerId, String topicId, String label) {
        Progress progress = new Progress();
        progress.setLearnerId(learnerId);
        progress.setTopicId(topicId);
        progress.setLabel(label);
        progress.setCreatedAt(java.time.LocalDateTime.now());

        repository.save(progress);
    }

    public List<Progress> getProgress(String learnerId) {
        return repository.findByLearnerId(learnerId);
    }

    public List<Progress> getProgressForTopic(String learnerId, String topicId) {
        return repository.findByLearnerIdAndTopicIdOrderByCreatedAtAsc(learnerId, topicId);
    }

    public boolean undoLatestClick(String learnerId, String topicId) {
        Optional<Progress> latestClick =
                repository.findTopByLearnerIdAndTopicIdOrderByCreatedAtDesc(learnerId, topicId);

        if (latestClick.isEmpty()) {
            return false;
        }

        repository.delete(latestClick.get());
        return true;
    }
}