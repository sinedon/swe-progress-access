package com.swe.project.progressaccess.repository;

import com.swe.project.progressaccess.model.AggregateProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AggregateProgressRepository extends JpaRepository<AggregateProgress, String> {
}