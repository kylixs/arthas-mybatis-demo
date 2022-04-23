package sample.mybatis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sample.mybatis.domain.Hotel;
import sample.mybatis.service.HotelService;

@RequestMapping("/hotel")
@RestController
public class HotelController {

    @Autowired @Lazy
    private HotelService hotelService;

    @GetMapping("city/{cityId}")
    public Hotel selectByCityId(@PathVariable("cityId") int cityId) {
        return hotelService.selectByCityId(cityId);
    }
}
