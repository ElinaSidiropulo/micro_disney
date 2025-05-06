package com.example.visit_service.service;

import com.example.visit_service.entity.Visit;
import com.example.visit_service.entity.VisitId;
import com.example.visit_service.repository.VisitRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VisitService {

    private final VisitRepository visitRepository;

    public VisitService(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    public Visit save(Visit visit) {
        return visitRepository.save(visit);
    }

    public Optional<Visit> findById(VisitId id) {
        return visitRepository.findById(id);
    }

    public List<Visit> findAll() {
        return visitRepository.findAll();
    }

    public Visit update(Visit visit) {
        return visitRepository.save(visit);
    }

    public void deleteById(VisitId id) {
        visitRepository.deleteById(id);
    }
}
