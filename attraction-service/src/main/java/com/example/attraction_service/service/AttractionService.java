package com.example.attraction_service.service;

import com.example.attraction_service.entity.Attraction;
import com.example.attraction_service.repository.AttractionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttractionService {

    private final AttractionRepository attractionRepository;

    public AttractionService(AttractionRepository attractionRepository) {
        this.attractionRepository = attractionRepository;
    }

    public Attraction saveAttraction(Attraction attraction) {
        Optional<Attraction> existing = attractionRepository.findByName(attraction.getName());
        if (existing.isPresent()) {
            throw new IllegalArgumentException("Attraction with this name already exists.");
        }
        return attractionRepository.save(attraction);
    }


    public List<Attraction> getAllAttractions() {
        return attractionRepository.findAll();
    }

    public Optional<Attraction> getAttractionById(Long id) {
        return attractionRepository.findById(id);
    }

    public Attraction updateAttraction(Long id, Attraction updatedAttraction) {
        return attractionRepository.findById(id).map(existingAttraction -> {

            if (attractionRepository.existsByNameAndIdNot(updatedAttraction.getName(), id)) {
                throw new IllegalArgumentException("Already exists!");
            }

            existingAttraction.setName(updatedAttraction.getName());
            existingAttraction.setDescription(updatedAttraction.getDescription());
            existingAttraction.setMinHeight(updatedAttraction.getMinHeight());
            existingAttraction.setMaxCapacity(updatedAttraction.getMaxCapacity());
            existingAttraction.setDuration(updatedAttraction.getDuration());

            return attractionRepository.save(existingAttraction);
        }).orElse(null);
    }

    public Attraction patchAttraction(Long id, Attraction partialAttraction) {
        return attractionRepository.findById(id).map(existing -> {

            if (partialAttraction.getName() != null &&
                    attractionRepository.existsByNameAndIdNot(partialAttraction.getName(), id)) {
                throw new IllegalArgumentException("Already exists!");
            }

            if (partialAttraction.getName() != null) existing.setName(partialAttraction.getName());
            if (partialAttraction.getDescription() != null) existing.setDescription(partialAttraction.getDescription());
            if (partialAttraction.getMinHeight() != null) existing.setMinHeight(partialAttraction.getMinHeight());
            if (partialAttraction.getMaxCapacity() != null) existing.setMaxCapacity(partialAttraction.getMaxCapacity());
            if (partialAttraction.getDuration() != null) existing.setDuration(partialAttraction.getDuration());

            return attractionRepository.save(existing);
        }).orElse(null);
    }

    public boolean deleteAttraction(Long id) {
        if (attractionRepository.existsById(id)) {
            attractionRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
