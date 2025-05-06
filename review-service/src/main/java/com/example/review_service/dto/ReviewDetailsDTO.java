package com.example.review_service.dto;

public class ReviewDetailsDTO {
    private ReviewDTO review;
    private UserDTO user;
    private AttractionDTO attraction;

    // геттеры и сеттеры

    public ReviewDTO getReview() { return review; }
    public void setReview(ReviewDTO review) { this.review = review; }

    public UserDTO getUser() { return user; }
    public void setUser(UserDTO user) { this.user = user; }

    public AttractionDTO getAttraction() { return attraction; }
    public void setAttraction(AttractionDTO attraction) { this.attraction = attraction; }
}
