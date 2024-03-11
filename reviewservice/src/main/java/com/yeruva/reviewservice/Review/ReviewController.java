package com.yeruva.reviewservice.Review;

import com.yeruva.reviewservice.Review.messaging.ReviewMessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
   @Autowired
    private ReviewService reviewService;


    private ReviewMessageProducer reviewMessageProducer;
    public ReviewController(ReviewMessageProducer reviewMessageProducer) {
        this.reviewMessageProducer = reviewMessageProducer;
    }



   @GetMapping("/getAllReviews")
   public ResponseEntity<List<Review>> getAllReviews(@RequestParam Long companyId){
       return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
   }
    @PostMapping("/addReview")
    public ResponseEntity<String> addReview(@RequestParam Long companyId,@RequestBody Review review){
       boolean addedReview= reviewService.addReview(companyId,review);
       if(addedReview) {
           reviewMessageProducer.sendMessage(review);
           return new ResponseEntity<>("Review Added Successfully", HttpStatus.OK);
       }return new ResponseEntity<>("Review Not Added .might be company not exists", HttpStatus.NOT_FOUND);

    }
    @GetMapping("/getReviewById/{reviewId}")
    public ResponseEntity<Review> getReviewByReviewId(@PathVariable Long reviewId){
return new ResponseEntity<>(reviewService.getReview(reviewId),HttpStatus.OK);
    }

    @PutMapping("/updateReview/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long reviewId,@RequestBody Review review) {
    boolean isReviewUpdated= reviewService.updateReview(reviewId,review);
    if(isReviewUpdated)
        return new ResponseEntity<>("Review updated successfully",HttpStatus.OK);
    return new ResponseEntity<>("Review Not updated... might be no company or No review exists",HttpStatus.NOT_FOUND);
   }

   @DeleteMapping("/deleteReviewById/{reviewId}")
   public ResponseEntity<String> deleteReview(@PathVariable Long reviewId) {
       boolean isReviewDeleted= reviewService.deleteReview(reviewId);
       if(isReviewDeleted)
           return new ResponseEntity<>("Review deleted successfully",HttpStatus.OK);
       return new ResponseEntity<>("Review Not deleted... might be no company or No review exists",HttpStatus.NOT_FOUND);

   }

   @GetMapping("/averageRating")
    public Double getAverageReview(@RequestParam Long companyId){
        List<Review> reviewList=reviewService.getAllReviews(companyId);
       return reviewService.getAverageRatingOfCompany(companyId);

   }
   }
