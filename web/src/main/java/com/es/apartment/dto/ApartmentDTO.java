package com.es.apartment.dto;

import com.es.apartment.entity.ApartmentImage;

import java.io.Serializable;
import java.util.List;

public class ApartmentDTO implements Serializable {
    private String id;
    private Long price;
    private String street;
    private String city;
    private String houseNumber;
    private Integer square;
    private Integer roomCount;
    private String description;

    private String longDescription;
    private String shortDescription;
    private String count;

    private String subnailPath;
    private List<ApartmentImage> apartmentImages;

    public List<ApartmentImage> getApartmentImages() {
        return apartmentImages;
    }

    public void setApartmentImages(List<ApartmentImage> apartmentImages) {
        this.apartmentImages = apartmentImages;
    }

    public String getSubnailPath() {
        return subnailPath;
    }

    public void setSubnailPath(String subnailPath) {
        this.subnailPath = subnailPath;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public Integer getSquare() {
        return square;
    }

    public void setSquare(Integer square) {
        this.square = square;
    }

    public Integer getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(Integer roomCount) {
        this.roomCount = roomCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
