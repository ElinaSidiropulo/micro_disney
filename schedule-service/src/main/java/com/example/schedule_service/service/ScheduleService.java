package com.example.schedule_service.service;

import com.example.schedule_service.client.AttractionClient;
import com.example.schedule_service.dto.AttractionDto;
import com.example.schedule_service.dto.AttractionScheduleResponse;
import com.example.schedule_service.dto.ScheduleDetailsDto;
import com.example.schedule_service.entity.Schedule;
import com.example.schedule_service.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final AttractionClient attractionClient;

    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository, AttractionClient attractionClient) {
        this.scheduleRepository = scheduleRepository;
        this.attractionClient = attractionClient;
    }

    public Schedule saveSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public Schedule getScheduleById(Long id) {
        return scheduleRepository.findById(id).orElse(null);
    }

    public List<Schedule> getSchedulesByAttractionId(Long attractionId) {
        return scheduleRepository.findByAttractionId(attractionId);
    }

    public List<Schedule> getSchedulesByDayOfWeek(String dayOfWeek) {
        return scheduleRepository.findByDayOfWeek(dayOfWeek);
    }

    public Schedule updateSchedule(Long id, Schedule updatedSchedule) {
        if (scheduleRepository.existsById(id)) {
            updatedSchedule.setId(id);
            return scheduleRepository.save(updatedSchedule);
        }
        return null;
    }

    public boolean deleteSchedule(Long id) {
        if (scheduleRepository.existsById(id)) {
            scheduleRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public AttractionScheduleResponse getAttractionWithSchedule(Long attractionId) {
        AttractionDto attraction = attractionClient.getAttractionById(attractionId);
        List<Schedule> schedules = scheduleRepository.findByAttractionId(attractionId);

        AttractionScheduleResponse response = new AttractionScheduleResponse();
        response.setAttraction(attraction);
        response.setSchedules(schedules);

        return response;
    }

    public ScheduleDetailsDto getScheduleWithAttraction(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElse(null);
        if (schedule == null) return null;

        AttractionDto attraction = attractionClient.getAttractionById(schedule.getAttractionId());

        ScheduleDetailsDto dto = new ScheduleDetailsDto();
        dto.setId(schedule.getId());
        dto.setDayOfWeek(schedule.getDayOfWeek());
        dto.setOpenTime(schedule.getOpenTime());
        dto.setCloseTime(schedule.getCloseTime());
        dto.setAttraction(attraction);

        return dto;
    }

    public Schedule patchSchedule(Long id, Schedule partialSchedule) {
        Schedule existing = scheduleRepository.findById(id).orElse(null);
        if (existing == null) return null;

        if (partialSchedule.getDayOfWeek() != null) {
            existing.setDayOfWeek(partialSchedule.getDayOfWeek());
        }
        if (partialSchedule.getOpenTime() != null) {
            existing.setOpenTime(partialSchedule.getOpenTime());
        }
        if (partialSchedule.getCloseTime() != null) {
            existing.setCloseTime(partialSchedule.getCloseTime());
        }
        if (partialSchedule.getAttractionId() != null) {
            existing.setAttractionId(partialSchedule.getAttractionId());
        }

        return scheduleRepository.save(existing);
    }

}
