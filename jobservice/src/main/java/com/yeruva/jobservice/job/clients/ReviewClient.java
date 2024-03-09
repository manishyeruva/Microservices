package com.yeruva.jobservice.job.clients;

import com.yeruva.jobservice.job.external.Review;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name="REVIEW-SERVICE")
public interface ReviewClient {
    @GetMapping("/reviews/getAllReviews")
    List<Review> getReviews(@RequestParam("companyId") Long companyId);
}
