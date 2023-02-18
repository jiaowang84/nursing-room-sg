package com.example.nursingroom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NursingRoomController {

    private NursingRoomRepository nursingRoomRepository;

    @Autowired
    public NursingRoomController(NursingRoomRepository nursingRoomRepository) {
        this.nursingRoomRepository = nursingRoomRepository;
    }

    @RequestMapping("/nursingrooms")
    List<NursingRoom> getAll() {
        return nursingRoomRepository.findAll();
    }

    @RequestMapping("/nursingrooms/getByLocation")
    List<NursingRoom> getByLocation(@RequestParam String location) {
        return nursingRoomRepository.findByLocationContainingIgnoreCase(location);
    }

    @RequestMapping("/nursingrooms/query")
    List<NursingRoom> getByQueryString(@RequestParam String str) {
//        return nursingRoomRepository.findAllBy(TextCriteria.forDefaultLanguage().matching(str));
        return nursingRoomRepository.findByAddressContainingIgnoreCaseOrLocationContainingIgnoreCase(str, str);
    }

    @RequestMapping("/nursingrooms/getByCoordinates")
    List<NursingRoom> getByCoordinates(@RequestParam String latitude,@RequestParam String longitude) {
        Point point = new Point(Double.parseDouble(longitude), Double.parseDouble(latitude));
        Distance distance = new Distance(3, Metrics.KILOMETERS);
        return nursingRoomRepository.findByCoordinateNear(point, distance);
    }

}
