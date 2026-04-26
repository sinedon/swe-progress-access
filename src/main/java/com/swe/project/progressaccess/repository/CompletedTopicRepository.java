package com.swe.project.progressaccess.repository;

import com.swe.project.progressaccess.model.CompletedTopic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompletedTopicRepository extends JpaRepository<CompletedTopic, Long> {

    boolean existsByLearnerIdAndTopicId(String learnerId, String topicId);
}
