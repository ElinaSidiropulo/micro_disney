package com.example.visit_service.repository;

import com.example.visit_service.entity.Visit;
import com.example.visit_service.entity.VisitId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitRepository extends JpaRepository<Visit, VisitId> {
}
