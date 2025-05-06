package com.example.schedule_service.dto;

import java.time.LocalTime;

public class ScheduleDetailsDto {
    private Long id;
    private String dayOfWeek;
    private LocalTime openTime;
    private LocalTime closeTime;
    private AttractionDto attraction;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDayOfWeek() { return dayOfWeek; }
    public void setDayOfWeek(String dayOfWeek) { this.dayOfWeek = dayOfWeek; }

    public LocalTime getOpenTime() { return openTime; }
    public void setOpenTime(LocalTime openTime) { this.openTime = openTime; }

    public LocalTime getCloseTime() { return closeTime; }
    public void setCloseTime(LocalTime closeTime) { this.closeTime = closeTime; }

    public AttractionDto getAttraction() { return attraction; }
    public void setAttraction(AttractionDto attraction) { this.attraction = attraction; }
}
