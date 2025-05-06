package com.example.schedule_service.controller;

import com.example.schedule_service.dto.AttractionScheduleResponse;
import com.example.schedule_service.dto.ScheduleDetailsDto;
import com.example.schedule_service.entity.Schedule;
import com.example.schedule_service.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping
    public Schedule createSchedule(@RequestBody Schedule schedule) {
        try {
            return scheduleService.saveSchedule(schedule);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error creating schedule: " + e.getMessage());
        }
    }

    @GetMapping
    public List<Schedule> getAllSchedules() {
        return scheduleService.getAllSchedules();
    }

    @GetMapping("/{id}")
    public Schedule getScheduleById(@PathVariable Long id) {
        return scheduleService.getScheduleById(id);
    }

    @GetMapping("/attraction/{attractionId}")
    public List<Schedule> getSchedulesByAttractionId(@PathVariable Long attractionId) {
        return scheduleService.getSchedulesByAttractionId(attractionId);
    }

    @GetMapping("/day/{dayOfWeek}")
    public List<Schedule> getSchedulesByDayOfWeek(@PathVariable String dayOfWeek) {
        return scheduleService.getSchedulesByDayOfWeek(dayOfWeek);
    }

    @PutMapping("/{id}")
    public Schedule updateSchedule(@PathVariable Long id, @RequestBody Schedule schedule) {
        return scheduleService.updateSchedule(id, schedule);
    }

    @DeleteMapping("/{id}")
    public boolean deleteSchedule(@PathVariable Long id) {
        return scheduleService.deleteSchedule(id);
    }

    @GetMapping("/attraction/{attractionId}/details")
    public AttractionScheduleResponse getAttractionWithSchedule(@PathVariable Long attractionId) {
        return scheduleService.getAttractionWithSchedule(attractionId);
    }

    @GetMapping("/{id}/details")
    public ScheduleDetailsDto getScheduleDetails(@PathVariable Long id) {
        ScheduleDetailsDto dto = scheduleService.getScheduleWithAttraction(id);
        if (dto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Schedule not found");
        }
        return dto;
    }

    @PatchMapping("/{id}")
    public Schedule patchSchedule(@PathVariable Long id, @RequestBody Schedule partialSchedule) {
        Schedule patched = scheduleService.patchSchedule(id, partialSchedule);
        if (patched == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Schedule not found");
        }
        return patched;
    }

}
