package com.es.apartment.entity;

import java.util.List;

public class Apartment extends AbstractEntity {
    private Long price;
    private Integer square;
    private Integer roomCount;
    private String description;
    private Address address;
    private List<ApartmentImage> apartmentImages;

    public List<ApartmentImage> getApartmentImages() {
        return apartmentImages;
    }

    public void setApartmentImages(List<ApartmentImage> apartmentImages) {
        this.apartmentImages = apartmentImages;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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
