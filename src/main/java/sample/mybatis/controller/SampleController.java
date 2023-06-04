package sample.mybatis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sample.mybatis.dao.CityDao;
import sample.mybatis.domain.City;
import sample.mybatis.domain.Hotel;
import sample.mybatis.mapper.HotelMapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author gongdewei 2022/12/16
 */
@RestController
public class SampleController {

    @Autowired
    private CityDao cityDao;

    @Autowired
    private HotelMapper hotelMapper;

    private Random random = new Random();

    private List<byte[]>  cache = Collections.synchronizedList(new ArrayList<>());

    @RequestMapping("/city/{id}")
    public City getCity(@PathVariable long id) {
        return cityDao.selectCityById(id);
    }

    @RequestMapping("/hotel/{id}")
    public Hotel getHotel(@PathVariable int id) {
        return hotelMapper.selectByCityId(id);
    }

    @RequestMapping("/byte/{size}")
    public Object getByte(@PathVariable int size) {
        int maxCount = Integer.parseInt(System.getProperty("resultCount", "1000"));
        int type = random.nextInt(10);
        if (type >= 9) { // big job
            return doHeavilyJob(size, maxCount);
        } else if (type >= 7) { // medium
            return doMediumJob(size, maxCount);
        } else { // small
            return doSmallJob(size, maxCount);
        }

//        int count = random.nextInt(maxCount) + maxCount/3;
//        int tempCount = random.nextInt(maxCount) + maxCount / 5;
//
//        // long period
//        for (int i = 0; i < count; i++) {
//            cache.add(new byte[size]);
//        }

        // short period
//        List<byte[]> list = new ArrayList<>();
//        for (int i = 0; i < tempCount; i++) {
//            list.add(new byte[size]);
//        }
//        return list;
    }

    private List<byte[]> doHeavilyJob(int size, int maxCount) {
        int count = maxCount * 10;
        List<byte[]> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(new byte[size]);
        }
        return list.subList(0, 100);
    }

    private List<byte[]> doMediumJob(int size, int count) {
        List<byte[]> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(new byte[size]);
        }
        return list.subList(0, 50);
    }

    private List<byte[]> doSmallJob(int size, int count) {
        count = count / 10;
        List<byte[]> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(new byte[size]);
        }
        return list.subList(0, 10);
    }

    @Scheduled(initialDelay = 1000, fixedRate = 2000)
    public void clear() {
        if (cache.isEmpty()) {
            return;
        }
        int count = cache.size()/10;
        for (int i = 0; i < count; i++) {
            if (!cache.isEmpty()) {
                cache.remove(cache.size()-1);
            }
        }
    }

}
