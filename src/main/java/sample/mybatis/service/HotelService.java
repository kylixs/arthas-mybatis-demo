package sample.mybatis.service;

import sample.mybatis.domain.Hotel;

public interface HotelService {

    Hotel selectByCityId(int cityId);

}
