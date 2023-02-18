package com.example.nursingroom;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class NursingRoom {

    @Id
    private String id;
    @TextIndexed
    @Indexed
    private String address;
    @TextIndexed
    @Indexed
    private String location;
    private String direction;
    private String image;
    private List<String> facilities;
    @GeoSpatialIndexed(name="coordinate_2dsphere", type= GeoSpatialIndexType.GEO_2DSPHERE)
    public GeoJsonPoint coordinate;

    private boolean changingStation;
    private boolean powerPoints;
    private boolean sink;
    private boolean waterDispenser;

    public NursingRoom(String address, String location, String direction, String image, List<String> facilities, GeoJsonPoint coordinate, boolean changingStation, boolean powerPoints, boolean sink, boolean waterDispenser) {
        this.address = address;
        this.location = location;
        this.direction = direction;
        this.image = image;
        this.facilities = facilities;
        this.coordinate = coordinate;
        this.changingStation = changingStation;
        this.powerPoints = powerPoints;
        this.sink = sink;
        this.waterDispenser = waterDispenser;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public List<String> getFacilities() {
        return facilities;
    }

    public void setFacilities(List<String> facilities) {
        this.facilities = facilities;
    }

    public GeoJsonPoint getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(GeoJsonPoint coordinate) {
        this.coordinate = coordinate;
    }

    public boolean isChangingStation() {
        return changingStation;
    }

    public void setChangingStation(boolean changingStation) {
        this.changingStation = changingStation;
    }

    public boolean isPowerPoints() {
        return powerPoints;
    }

    public void setPowerPoints(boolean powerPoints) {
        this.powerPoints = powerPoints;
    }

    public boolean isSink() {
        return sink;
    }

    public void setSink(boolean sink) {
        this.sink = sink;
    }

    public boolean isWaterDispenser() {
        return waterDispenser;
    }

    public void setWaterDispenser(boolean waterDispenser) {
        this.waterDispenser = waterDispenser;
    }

}
