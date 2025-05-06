package com.example.review_service.controller;

import com.example.review_service.dto.ReviewDetailsDTO;
import com.example.review_service.entity.Review;
import com.example.review_service.service.ReviewService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public Review createReview(@RequestBody Review review) {
        return reviewService.createReview(review);
    }

    @GetMapping
    public List<Review> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @GetMapping("/{id}")
    public Review getReviewById(@PathVariable Long id) {
        return reviewService.getReviewById(id);
    }

    @GetMapping("/user/{userId}")
    public List<Review> getReviewsByUserId(@PathVariable Long userId) {
        return reviewService.getReviewsByUserId(userId);
    }

    @GetMapping("/attraction/{attractionId}")
    public List<Review> getReviewsByAttractionId(@PathVariable Long attractionId) {
        return reviewService.getReviewsByAttractionId(attractionId);
    }

    @PutMapping("/{id}")
    public Review updateReview(@PathVariable Long id, @RequestBody Review review) {
        return reviewService.updateReview(id, review);
    }

    @PatchMapping("/{id}")
    public Review patchReview(@PathVariable Long id, @RequestBody Review review) {
        return reviewService.patchReview(id, review);
    }


    @DeleteMapping("/{id}")
    public boolean deleteReview(@PathVariable Long id) {
        return reviewService.deleteReview(id);
    }

    @GetMapping("/{id}/details")
    public ReviewDetailsDTO getReviewWithDetails(@PathVariable Long id) {
        return reviewService.getReviewWithDetails(id);
    }
}
