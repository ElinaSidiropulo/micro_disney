package com.example.review_service.client;

import com.example.review_service.dto.AttractionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "attraction-service")
public interface AttractionClient {
    @GetMapping("api/attractions/{id}")
    AttractionDTO getAttractionById(@PathVariable("id") Long id);
}
