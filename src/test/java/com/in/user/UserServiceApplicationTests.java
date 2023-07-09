package com.in.user;

import com.in.user.entities.Rating;
import com.in.user.external.services.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private RatingService ratingService;

	@Test
	void createdRating(){
		Rating rating = Rating.builder().rating(10).userId("").hotelId("").feedback("This is created using fiegn client").build();
		Rating savedRating = ratingService.createRating(rating);
		System.out.println("New rating created");

	}

	@Test
	void getRating(){
		Rating rating = Rating.builder().rating(10).userId("").hotelId("").feedback("This is created using fiegn client").build();
		List<Rating> getRatings = ratingService.getRatings();
		System.out.println("getRatings List: "+ getRatings);
	}

}
