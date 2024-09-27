package com.application.controller;

import com.application.model.Progress;
import com.application.services.ProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/progress")
public class ProgressController {

    @Autowired
    private ProgressService progressService;
    @PostMapping
    public ResponseEntity<Progress> updateProgress(@RequestBody Progress progress) {
        Progress updatedProgress = progressService.updateProgress(progress);
        return ResponseEntity.ok(updatedProgress);
    }
    @GetMapping
    public List<Progress> getprogress(){
        return progressService.getProgress();

    }

}
