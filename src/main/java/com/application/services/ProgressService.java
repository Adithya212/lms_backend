package com.application.services;

import com.application.model.Progress;
import com.application.repository.ProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProgressService {

    @Autowired
    private ProgressRepository progressRepository;

    // Update progress
    public Progress updateProgress(Progress progress) {
        return progressRepository.save(progress);
    }

    // Get progress by course
    public List<Progress> getProgress() {
        return progressRepository.findAll();
    }
}
