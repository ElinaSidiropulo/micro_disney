package com.example.attraction_service.controller;

import com.example.attraction_service.entity.Attraction;
import com.example.attraction_service.service.AttractionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attractions")
public class AttractionController {

    private final AttractionService attractionService;

    public AttractionController(AttractionService attractionService) {
        this.attractionService = attractionService;
    }

    @PostMapping
    public ResponseEntity<Attraction> createAttraction(@RequestBody Attraction attraction) {
        return ResponseEntity.ok(attractionService.saveAttraction(attraction));
    }

    @GetMapping
    public ResponseEntity<List<Attraction>> getAllAttractions() {
        List<Attraction> list = attractionService.getAllAttractions();
        return list.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Attraction> getAttractionById(@PathVariable Long id) {
        return attractionService.getAttractionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Attraction> updateAttraction(@PathVariable Long id, @RequestBody Attraction attraction) {
        Attraction updated = attractionService.updateAttraction(id, attraction);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Attraction> patchAttraction(@PathVariable Long id, @RequestBody Attraction partialAttraction) {
        Attraction updated = attractionService.patchAttraction(id, partialAttraction);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttraction(@PathVariable Long id) {
        return attractionService.deleteAttraction(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @RestControllerAdvice
    public class GlobalExceptionHandler {

        @ExceptionHandler(IllegalArgumentException.class)
        public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

}
