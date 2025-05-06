package com.example.review_service.service;

import com.example.review_service.client.AttractionClient;
import com.example.review_service.client.UserClient;
import com.example.review_service.dto.AttractionDTO;
import com.example.review_service.dto.ReviewDTO;
import com.example.review_service.dto.ReviewDetailsDTO;
import com.example.review_service.dto.UserDTO;
import com.example.review_service.entity.Review;
import com.example.review_service.repository.ReviewRepository;
import feign.FeignException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserClient userClient;
    private final AttractionClient attractionClient;

    public ReviewService(ReviewRepository reviewRepository, UserClient userClient, AttractionClient attractionClient) {
        this.reviewRepository = reviewRepository;
        this.userClient = userClient;
        this.attractionClient = attractionClient;
    }

    public Review createReview(Review review) {
        return reviewRepository.save(review);
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Review getReviewById(Long id) {
        return reviewRepository.findById(id).orElse(null);
    }

    public List<Review> getReviewsByUserId(Long userId) {
        return reviewRepository.findByUserId(userId);
    }

    public List<Review> getReviewsByAttractionId(Long attractionId) {
        return reviewRepository.findByAttractionId(attractionId);
    }

    public Review updateReview(Long id, Review updatedReview) {
        return reviewRepository.findById(id).map(existingReview -> {
            existingReview.setRating(updatedReview.getRating());
            existingReview.setComment(updatedReview.getComment());
            existingReview.setUserId(updatedReview.getUserId());
            existingReview.setAttractionId(updatedReview.getAttractionId());
            return reviewRepository.save(existingReview);
        }).orElse(null);
    }

    public boolean deleteReview(Long id) {
        if (reviewRepository.existsById(id)) {
            reviewRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public ReviewDetailsDTO getReviewWithDetails(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if (review == null) return null;

        UserDTO user = null;
        AttractionDTO attraction = null;

        try {
            user = userClient.getUserById(review.getUserId());
        } catch (FeignException e) {
            System.err.println("Error fetching user: " + e.getMessage());
        }

        try {
            attraction = attractionClient.getAttractionById(review.getAttractionId());
        } catch (FeignException e) {
            System.err.println("Error fetching attraction: " + e.getMessage());
        }

        ReviewDTO reviewDTO = new ReviewDTO(
                review.getId(), review.getUserId(), review.getAttractionId(),
                review.getRating(), review.getComment(), review.getCreatedAt()
        );

        ReviewDetailsDTO details = new ReviewDetailsDTO();
        details.setReview(reviewDTO);
        details.setUser(user); // может быть null
        details.setAttraction(attraction); // может быть null

        return details;
    }

    public Review patchReview(Long id, Review updatedReview) {
        return reviewRepository.findById(id).map(existingReview -> {
            // Частичное обновление
            if (updatedReview.getRating() != null) {
                existingReview.setRating(updatedReview.getRating());
            }
            if (updatedReview.getComment() != null) {
                existingReview.setComment(updatedReview.getComment());
            }
            if (updatedReview.getUserId() != null) {
                existingReview.setUserId(updatedReview.getUserId());
            }
            if (updatedReview.getAttractionId() != null) {
                existingReview.setAttractionId(updatedReview.getAttractionId());
            }
            return reviewRepository.save(existingReview);
        }).orElse(null);
    }

}
