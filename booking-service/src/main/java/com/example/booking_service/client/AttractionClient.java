package com.example.booking_service.client;

import com.example.booking_service.dto.AttractionDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "attraction-service")
public interface AttractionClient {

    @GetMapping("/api/attractions/{id}")
    AttractionDto getAttractionById(@PathVariable("id") Long id);
}
