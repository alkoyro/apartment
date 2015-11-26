package com.es.apartment.service.impl;

import com.es.apartment.dao.ApartmentDao;
import com.es.apartment.entity.Address;
import com.es.apartment.entity.Apartment;
import com.es.apartment.entity.ApartmentImage;
import com.es.apartment.exception.ServiceException;
import com.es.apartment.service.ApartmentService;
import com.mongodb.MongoException;
import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class ApartmentServiceImpl implements ApartmentService {
    private static Logger logger = LoggerFactory.getLogger(ApartmentServiceImpl.class);

    private static final String SUBNAIL_NAME_PREFIX = "sub";
    private static final String IMAGES_DIR_NAME = "images";
    private static final String UUID_VALUE_RANGE = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final Integer UUID_VALUE_LENGTH = 10;
    private static final Integer SUBNAIL_WIDTH = 220;
    private static final Integer SUBNAIL_HEIGHT = 160;

    @Autowired
    private ApartmentDao apartmentDao;

    @Value("${image.upload.path}")
    private String uploadImagePath;

    @Value("${image.upload.path.reserve}")
    private String uploadImagePathReserve;


    @Override
    public ApartmentImage saveImage(BufferedImage bufferedImage, String imageType) throws ServiceException {
        logger.debug("Saving start");

        ApartmentImage apartmentImage = new ApartmentImage();

        String fileName = generateFileName() + "." + imageType;
        String filePath = uploadImagePath + fileName;
        try {
            ImageIO.write(bufferedImage, imageType, new File(filePath));
        } catch (IOException e) {
            logger.error("Fail to save image to " + filePath, e);
            throw new ServiceException();
        }
        filePath = uploadImagePathReserve + fileName;
        try {
            ImageIO.write(bufferedImage, imageType, new File(filePath));
        } catch (IOException e) {
            logger.error("Fail to save reserved image to " + filePath, e);
            throw new ServiceException();
        }
        apartmentImage.setImagePath(IMAGES_DIR_NAME + "/" + fileName);

        BufferedImage thumbnailImage = Scalr.resize(bufferedImage, SUBNAIL_WIDTH, SUBNAIL_HEIGHT);
        fileName = generateFileName() + SUBNAIL_NAME_PREFIX + "." + imageType;
        filePath = uploadImagePath + fileName;
        try {
            ImageIO.write(thumbnailImage, imageType, new File(filePath));
        } catch (IOException e) {
            logger.error("Fail to save subnail image to " + filePath, e);
            throw new ServiceException();
        }
        filePath = uploadImagePathReserve + fileName;
        try {
            ImageIO.write(thumbnailImage, imageType, new File(filePath));
        } catch (IOException e) {
            logger.error("Fail to save subnail reserve image to " + filePath, e);
            throw new ServiceException();
        }
        apartmentImage.setImageSubnailPath(IMAGES_DIR_NAME + "/" + fileName);

        logger.debug("Saving finished");

        return apartmentImage;
    }

    private String generateFileName() {
        logger.debug("Generating file name start");

        Random random = new Random();

        StringBuilder result = new StringBuilder(UUID_VALUE_LENGTH);
        for (int i = 0; i < UUID_VALUE_LENGTH; i++) {
            result.append(UUID_VALUE_RANGE.charAt(random.nextInt(UUID_VALUE_RANGE.length())));
        }
        logger.debug("Generating file name finished");

        return result.toString();
    }

    @Override
    public Apartment saveOrUpdate(Apartment apartment) throws ServiceException {
        logger.debug("Save or update apartment start");

        try {
            apartment = apartmentDao.save(apartment);
            logger.debug("Save or update apartment finished");
            return apartment;
        } catch (MongoException e) {
            logger.error("Save or update apartment failed", e);
            throw new ServiceException();
        } catch (DataAccessException e) {
            logger.error("Save or update apartment failed", e);
            throw new ServiceException();
        }
    }

    @Override
    public void remove(Apartment apartment) throws ServiceException {
        logger.debug("Convert apartment start");
        try {
            apartmentDao.delete(apartment);
            logger.debug("Remove apartment finished");
        } catch (MongoException e) {
            logger.error("Remove apartment failed", e);
            throw new ServiceException();
        } catch (DataAccessException e) {
            logger.error("Remove apartment failed", e);
            throw new ServiceException();
        }
    }

    @Override
    public List<Apartment> findAll() throws ServiceException {
        logger.debug("Find all apartments start");
        List<Apartment> apartments = new ArrayList<Apartment>();
        try {
            Iterable<Apartment> iterable = apartmentDao.findAll();
            for (Apartment apartment : iterable) {
                apartments.add(apartment);
            }
        } catch (MongoException e) {
            logger.error("Find all apartments failed", e);
            throw new ServiceException();
        } catch (DataAccessException e) {
            logger.error("Find all apartments failed", e);
            throw new ServiceException();
        }
        logger.debug("Find All finished");
        return apartments;
    }

    @Override
    public List<Apartment> findAll(int pageIndex, int pageSize) throws ServiceException {
        logger.debug("Find all apartment paging start");
        List<Apartment> apartments = new ArrayList<Apartment>();

        PageRequest pageRequest = new PageRequest(pageIndex, pageSize);
        try {
            Iterable<Apartment> iterable = apartmentDao.findAll(pageRequest);
            for (Apartment apartment : iterable) {
                apartments.add(apartment);
            }
        } catch (MongoException e) {
            logger.error("Find all apartment paging failed", e);
            throw new ServiceException();
        } catch (DataAccessException e) {
            logger.error("Find all apartment paging failed", e);
            throw new ServiceException();
        }
        logger.debug("Find all apartment paging finished");
        return apartments;
    }

    @Override
    public Apartment findBy(String id) throws ServiceException {
        logger.debug("Find apartment by id start");
        try {
            Apartment result = apartmentDao.findOne(id);
            logger.debug("Find apartment by id finished");
            return result;
        } catch (MongoException e) {
            logger.error("Find apartment by id failed", e);
            throw new ServiceException();
        } catch (DataAccessException e) {
            logger.error("Find apartment by id failed", e);
            throw new ServiceException();
        }
    }

    @Override
    public Integer getSize() throws ServiceException {
        logger.debug("Get apartments size start");
        try {
            int result = Long.valueOf(apartmentDao.count()).intValue();
            logger.debug("Get apartments size finished");
            return result;
        } catch (MongoException e) {
            logger.error("Get apartments size failed", e);
            throw new ServiceException();
        } catch (DataAccessException e) {
            logger.error("Get apartments size failed", e);
            throw new ServiceException();
        }

    }

}
