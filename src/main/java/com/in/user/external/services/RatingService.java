package com.in.user.external.services;

import com.in.user.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
@FeignClient(name = "RATING-SERVICE")
public interface RatingService {


    //get getRatings
//    @GetMapping("/ratings/getRatings")
//    public ResponseEntity<List<Rating>> getRatings();

    @GetMapping("/ratings/getRatings")
    public List<Rating> getRatings();


    //post
//    @PostMapping("/ratings/createRating")
//    public ResponseEntity<Rating> createRating(Rating values);

    @PostMapping("/ratings/createRating")
    public Rating createRating(Rating values);

    //put
    @PutMapping("/ratings/updateRating/{ratingId}")
    public ResponseEntity<Rating> updateRating(@PathVariable String ratingId, Rating rating);

    @DeleteMapping ("/ratings/deleteRating/{ratingId}")
    public void deleteRating(@PathVariable String ratingId);
}
