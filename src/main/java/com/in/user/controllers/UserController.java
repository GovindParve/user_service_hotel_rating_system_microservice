package com.in.user.controllers;

import com.in.user.entities.Hotel1;
import com.in.user.entities.User;
import com.in.user.services.UserService;
import com.in.user.services.impl.UserServiceImpl;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userServices;

    @Autowired
    @Qualifier(value="hotel1")
    Hotel1 hotel1;

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    //create

    @PostMapping("/createUser")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User user1 = userServices.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    //single user get
    int retryCount = 1;
    @GetMapping("/getSingleUserById/{userId}")
//    @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
//    @Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelFallback")
    @RateLimiter(name = "userRateService", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getSingleUserById(@PathVariable String userId){
        logger.info("Get Single User Handle: UserController: ");
        logger.info("Retry count: {} ", retryCount);
        retryCount++;
        User user = userServices.getUser(userId);
        return ResponseEntity.ok(user);
    }

    //creating fallback method for circuitbreaker
    public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex){
        logger.info("Fallback is executed because service is down : ", ex.getMessage());
        User user = User.builder()
                .email("dummy@gmail.com")
                .name("Dummy")
                .about("This user is created dummy because some services down")
                .userId("141234")
                .build();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    //all user get
    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>>getAllUsers(){
        List<User> allUser = userServices.getAllUser();
        return ResponseEntity.ok(allUser);
    }
}
