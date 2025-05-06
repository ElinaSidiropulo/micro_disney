package com.example.schedule_service.dto;

import com.example.schedule_service.entity.Schedule;
import java.util.List;

public class AttractionScheduleResponse {

    private AttractionDto attraction;
    private List<Schedule> schedules;

    public AttractionDto getAttraction() {
        return attraction;
    }

    public void setAttraction(AttractionDto attraction) {
        this.attraction = attraction;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }
}
