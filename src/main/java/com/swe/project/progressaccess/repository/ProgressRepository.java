package com.swe.project.progressaccess.repository;

import com.swe.project.progressaccess.model.Progress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProgressRepository extends JpaRepository<Progress, Long> {
    List<Progress> findByLearnerId(String learnerId);

    List<Progress> findByLearnerIdAndTopicIdOrderByCreatedAtAsc(String learnerId, String topicId);

    Optional<Progress> findTopByLearnerIdAndTopicIdOrderByCreatedAtDesc(String learnerId, String topicId);
}