package sample.mybatis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sample.mybatis.domain.Hotel;
import sample.mybatis.mapper.HotelMapper;
import sample.mybatis.service.HotelService;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelMapper hotelMapper;

    @Override
    public Hotel selectByCityId(int cityId) {
        return hotelMapper.selectByCityId(cityId);
    }

}
