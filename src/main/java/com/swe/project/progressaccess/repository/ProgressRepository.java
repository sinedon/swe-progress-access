package com.swe.project.progressaccess.repository;

import com.swe.project.progressaccess.model.Progress;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgressRepository extends JpaRepository<Progress, Long> {
    List<Progress> findByLearnerId(String learnerId);
}