package com.es.apartment.service;

import com.es.apartment.entity.Apartment;
import com.es.apartment.entity.ApartmentImage;
import com.es.apartment.exception.ServiceException;

import java.awt.image.BufferedImage;
import java.util.List;

public interface ApartmentService {
    Apartment saveOrUpdate(Apartment apartment) throws ServiceException;
    void remove(Apartment apartment) throws ServiceException;

    Apartment findBy(String id) throws  ServiceException;
    List<Apartment> findAll() throws ServiceException;
    List<Apartment> findAll(int pageIndex, int pageSize) throws ServiceException;

    Integer getSize() throws ServiceException;
    ApartmentImage saveImage(BufferedImage bufferedImage, String imageType) throws ServiceException;
}
