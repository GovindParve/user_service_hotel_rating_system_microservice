package com.in.user.config;

import com.in.user.entities.Hotel1;
import com.in.user.external.services.HotelService;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MyConfig {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean(name="hotel1")
    public Hotel1 hotelService1() {
        return new Hotel1();
    }

    @Bean(name="hotel2")
    public Hotel1 hotelService2() {
        return new Hotel1();
    }

}
