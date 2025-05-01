package com.example.schedule_service.repository;

import com.example.schedule_service.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByAttractionId(Long attractionId);
    List<Schedule> findByDayOfWeek(String dayOfWeek);
}
