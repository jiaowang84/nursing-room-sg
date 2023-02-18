package com.example.nursingroom;

import java.util.List;

import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NursingRoomRepository extends MongoRepository<NursingRoom, String> {

    List<NursingRoom> findByLocationContainingIgnoreCase(String location);
    List<NursingRoom> findByAddressContainingIgnoreCase(String address);
    List<NursingRoom> findByAddressContainingIgnoreCaseOrLocationContainingIgnoreCase(String address, String location);
    List<NursingRoom> findAllBy(TextCriteria criteria);
    List<NursingRoom> findByCoordinateNear(Point p, Distance d);
}