package com.in.user.external.services;

import com.in.user.entities.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "HOTEL-SERVICE")
public interface HotelService {

    @GetMapping("/hotels/getHotel/{hotelId}")
    public Hotel getHotel(@PathVariable("hotelId") String hotelId);

}
