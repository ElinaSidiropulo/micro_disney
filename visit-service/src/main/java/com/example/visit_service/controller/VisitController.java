package com.example.visit_service.controller;

import com.example.visit_service.entity.Visit;
import com.example.visit_service.entity.VisitId;
import com.example.visit_service.service.VisitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/visits")
public class VisitController {

    private final VisitService visitService;

    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    @PostMapping
    public ResponseEntity<Visit> createVisit(@RequestBody Visit visit) {
        return ResponseEntity.ok(visitService.save(visit));
    }

    @GetMapping("/{userId}/{attractionId}")
    public ResponseEntity<Visit> getVisit(
            @PathVariable Long userId,
            @PathVariable Long attractionId,
            @RequestParam String visitTime) {
        VisitId id = new VisitId(userId, attractionId, LocalDateTime.parse(visitTime));
        return visitService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Visit>> getAllVisits() {
        return ResponseEntity.ok(visitService.findAll());
    }

    @PutMapping
    public ResponseEntity<Visit> updateVisit(@RequestBody Visit visit) {
        return ResponseEntity.ok(visitService.update(visit));
    }

    @DeleteMapping("/{userId}/{attractionId}")
    public ResponseEntity<Void> deleteVisit(
            @PathVariable Long userId,
            @PathVariable Long attractionId,
            @RequestParam String visitTime) {
        VisitId id = new VisitId(userId, attractionId, LocalDateTime.parse(visitTime));
        visitService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
