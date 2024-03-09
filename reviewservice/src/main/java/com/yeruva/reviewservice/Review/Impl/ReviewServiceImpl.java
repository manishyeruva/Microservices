package com.yeruva.reviewservice.Review.Impl;

import com.yeruva.reviewservice.Review.Review;
import com.yeruva.reviewservice.Review.ReviewRepository;
import com.yeruva.reviewservice.Review.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews=reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        if(companyId!=null){
            review.setCompanyId(companyId);
            reviewRepository.save(review);
            return true;
        }
        return  false;
    }

    @Override
    public Review getReview( Long reviewId) {
        return reviewRepository.findById(reviewId).orElse(null);
//        List<Review> reviews= reviewRepository.findByCompanyId(companyId);
//        return reviews.stream().filter(review -> review.getId().equals(reviewId))
//                .findFirst().orElse(null);
    }

    @Override
    public boolean updateReview( Long reviewId, Review updatedReview) {
        Review review=reviewRepository.findById(reviewId).orElse(null);
        if(review!=null){
            review.setTitle(updatedReview.getTitle());
            review.setDescription(updatedReview.getDescription());
            review.setRating(updatedReview.getRating());
            review.setCompanyId(updatedReview.getCompanyId());
                reviewRepository.save(review);
                return true;
        }
        return false;
    }

    @Override
    public boolean deleteReview( Long reviewId) {
        if (reviewRepository.existsById(reviewId)){
            reviewRepository.deleteById(reviewId);
              return true;
        }
            return false;
        }
}
