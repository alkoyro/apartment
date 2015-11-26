package com.es.apartment.web.controller;

import com.es.apartment.dto.ApartmentDTO;
import com.es.apartment.entity.*;
import com.es.apartment.exception.ServiceException;
import com.es.apartment.service.ApartmentService;
import com.es.apartment.web.form.ApartmentForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ApartmentsController {

    private static final String APARTMENT_DETAILS_PAGE_NAME = "ApartmentDetailsPage";
    private static final String ERROR_PAGE_NAME = "ErrorPage";
    private static final String LOGIN_PAGE_NAME = "LoginPage";
    private static final String LOGOUT_PAGE_NAME = "LogoutPage";
    private static final String MANAGEMENT_ALL_APARTMENTS_PAGE_NAME = "ManagementAllApartmentsPage";
    private static final String CONTACTS_PAGE_NAME = "ContactsPage";
    private static final String ALL_APARTMENTS_PAGE_NAME = "AllApartmentsPage";
    private static final String NEW_APARTMENT_PAGE_NAME = "AddApartmentPage";

    private static final String APARTMENT_ID_PARAM_NAME = "apartmentId";
    private static final String SELECTED_APARTMENT_ID_PARAM_NAME = "selectedApartmentId";
    private static final String ERROR_CODE_PARAM_NAME = "errorCode";
    private static final String SELECTED_APARTMENT_COUNT_PARAM_NAME = "count";

    private static final String APARTMENTS_LIST_ATTRIBUTE_NAME = "apartments";
    private static final String ERROR_MESSAGE_ATTRIBUTE_NAME = "errorMessage";
    private static final String APARTMENT_FORM_ATTRIBUTE_NAME = "apartmentForm";
    private static final String APARTMENT_ATTRIBUTE_NAME = "apartment";

    private static final String ALL_APARTMENTS_REDIRECT_URL = "redirect:/management/allApartments.html";
    private static final String NO_AVAILABLE_IMAGE_PATH = "images/no-available-image.png";
    private static final String IMAGE_MIME_PREFIX = "image";
    private static final String ANONYMOUS_USER_NAME = "anonymousUser";

    private static Logger logger = LoggerFactory.getLogger(ApartmentsController.class);

    @Autowired
    private ApartmentService apartmentService;

    @RequestMapping(value = "/allApartments.html", method = RequestMethod.GET)
    public ModelAndView showAllApartments() throws ServiceException {
        logger.debug("Showing all apartments start");
        List<Apartment> apartments = apartmentService.findAll();
        List<ApartmentDTO> apartmentDTOs = new ArrayList<ApartmentDTO>();

        for (Apartment apartment : apartments) {
            apartmentDTOs.add(convertApartment(apartment));
        }

        ModelAndView modelAndView = new ModelAndView(ALL_APARTMENTS_PAGE_NAME);
        modelAndView.addObject(APARTMENTS_LIST_ATTRIBUTE_NAME, apartmentDTOs);
        logger.debug("Showing all apartmaents finished");
        return modelAndView;
    }

    @RequestMapping(value = "/error.html", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView error(@RequestParam(value = ERROR_CODE_PARAM_NAME)
                                  String errorCode) {
        logger.debug("Show error start");

        ModelAndView modelAndView = new ModelAndView(ERROR_PAGE_NAME);

        int error = Integer.valueOf(errorCode);

        switch (error) {
            case 404:
                modelAndView.addObject(ERROR_MESSAGE_ATTRIBUTE_NAME, "No page found");
                break;
            case 405:
                modelAndView.addObject(ERROR_MESSAGE_ATTRIBUTE_NAME, "Server Error");
                break;
            case 500:
                modelAndView.addObject(ERROR_MESSAGE_ATTRIBUTE_NAME, "Internal Server Error");
                break;
            default:
                modelAndView.addObject(ERROR_MESSAGE_ATTRIBUTE_NAME, "Server Error");
                break;
        }


        logger.debug("Show error finished");
        return modelAndView;
    }

    @RequestMapping(value = "/login.html", method = RequestMethod.GET)
    public ModelAndView login() throws ServiceException {
        logger.debug("Show login form start");

        if (!SecurityContextHolder.getContext().getAuthentication().getName().equals(ANONYMOUS_USER_NAME)) {
            logger.info("User is logged in");
            return showAllManagementApartments();
        }
        ModelAndView modelAndView = new ModelAndView(LOGIN_PAGE_NAME);

        logger.debug("Show login form finished");
        return modelAndView;
    }

    @RequestMapping(value = "/management/logout.html", method = RequestMethod.GET)
    public ModelAndView logout() throws ServiceException {
        logger.debug("Show logout form start");

        ModelAndView modelAndView = new ModelAndView(LOGOUT_PAGE_NAME);

        logger.debug("Show logout form finished");
        return modelAndView;
    }


    private ApartmentDTO convertApartment(Apartment apartment) {
        StringBuilder descriptionBuilder = new StringBuilder();
        descriptionBuilder.append("Apartment:").append(" ").
                append(apartment.getRoomCount()).append(" ").
                append("rooms,").append(" ").
                append(apartment.getSquare()).append(" ").
                append("square meters").append("\n").
                append(apartment.getDescription());

        StringBuilder locationBuilder = new StringBuilder();
        locationBuilder.append("Apartment in").append(" ").
                append(apartment.getAddress().getStreet());

        ApartmentDTO apartmentDTO = new ApartmentDTO();
        apartmentDTO.setShortDescription(locationBuilder.toString());
        apartmentDTO.setLongDescription(descriptionBuilder.toString());
        apartmentDTO.setId(apartment.getId());
        apartmentDTO.setPrice(apartment.getPrice());
        apartmentDTO.setRoomCount(apartment.getRoomCount());
        apartmentDTO.setDescription(apartment.getDescription());
        apartmentDTO.setSquare(apartment.getSquare());
        apartmentDTO.setCity(apartment.getAddress().getCity());
        apartmentDTO.setStreet(apartment.getAddress().getStreet());
        apartmentDTO.setHouseNumber(apartment.getAddress().getHouseNumber());

        List<ApartmentImage> apartmentImages = apartment.getApartmentImages();

        String apartmentSubnailPath = apartmentImages.size() > 0 ?
                apartmentImages.get(0).getImageSubnailPath() : NO_AVAILABLE_IMAGE_PATH;
        apartmentDTO.setSubnailPath(apartmentSubnailPath);

        apartmentDTO.setApartmentImages(apartmentImages);

        return apartmentDTO;
    }

    @RequestMapping(value = "/management/addApartment.html", method = RequestMethod.GET)
    public ModelAndView addApartmentView() throws ServiceException {
        logger.debug("Add new apartment start");

        ApartmentForm apartmentForm = new ApartmentForm();

        ModelAndView modelAndView = new ModelAndView(NEW_APARTMENT_PAGE_NAME);
        modelAndView.addObject(APARTMENT_FORM_ATTRIBUTE_NAME, apartmentForm);

        logger.debug("Add new apartment finished");
        return modelAndView;
    }


    @RequestMapping(value = "/management/allApartments.html", method = RequestMethod.GET)
    public ModelAndView showAllManagementApartments() throws ServiceException {
        logger.debug("Showing all apartments for management start");

        List<Apartment> apartments = apartmentService.findAll();
        List<ApartmentDTO> apartmentDTOs = new ArrayList<ApartmentDTO>();

        for (Apartment apartment : apartments) {
            apartmentDTOs.add(convertApartment(apartment));
        }

        ModelAndView modelAndView = new ModelAndView(MANAGEMENT_ALL_APARTMENTS_PAGE_NAME);
        modelAndView.addObject(APARTMENTS_LIST_ATTRIBUTE_NAME, apartmentDTOs);
        logger.debug("Showing all apartments for management finished");
        return modelAndView;
    }

    @RequestMapping(value = "/selectedApartmentDetails.html", method = RequestMethod.GET)
    public ModelAndView showSelectedApartmentDetails(@RequestParam(value = SELECTED_APARTMENT_ID_PARAM_NAME)
                                                     String apartmentId,
                                                     @RequestParam(value = SELECTED_APARTMENT_COUNT_PARAM_NAME)
                                                     String count) throws ServiceException {
        logger.debug("Showing selected apartment details for apartment with id="
                + apartmentId + " start");
        Apartment apartment = apartmentService.findBy(apartmentId);

        ApartmentDTO apartmentDTO = convertApartment(apartment);
        apartmentDTO.setCount(count);
        
        if(apartment != null) {
            ModelAndView modelAndView = new ModelAndView(APARTMENT_DETAILS_PAGE_NAME);
            modelAndView.addObject(APARTMENT_ATTRIBUTE_NAME, apartmentDTO);

            logger.debug("Showing selected apartment details"
                    +" for apartment with id=" + apartmentId + " finished");
            return modelAndView;
        } else {
            return new ModelAndView(ERROR_PAGE_NAME);
        }
    }

    @RequestMapping(value = "/management/applyApartmentChanges.html", method = RequestMethod.POST)
    public ModelAndView applyApartmentChanges(@Valid ApartmentForm apartmentForm, BindingResult result) throws ServiceException {
        logger.debug("Adding apartment changes start");

        Apartment apartment = new Apartment();
        apartment.setAddress(new Address());

        if (!result.hasErrors()) {
            populateApartment(apartment, apartmentForm);
            apartmentService.saveOrUpdate(apartment);
            logger.debug("Adding apartment changes finished");
            return new ModelAndView(ALL_APARTMENTS_REDIRECT_URL);
        } else {
            ModelAndView modelAndView = new ModelAndView(NEW_APARTMENT_PAGE_NAME);
            logger.debug("Adding apartment changes failed: changes are not correct");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/management/delete.html", method = RequestMethod.GET)
    public ModelAndView deleteApartment(@RequestParam(APARTMENT_ID_PARAM_NAME)
                                        String apartmentId) throws ServiceException, IOException {
        logger.debug("Deleting apartment with id=" + apartmentId + " start");
        Apartment apartment = apartmentService.findBy(apartmentId);
        if(apartment != null) {
            apartmentService.remove(apartment);
            logger.debug("Deleting apartment  with id=" + apartmentId + " finished");
            return new ModelAndView(ALL_APARTMENTS_REDIRECT_URL);
        } else {
            return new ModelAndView(ERROR_PAGE_NAME);
        }
    }

    @RequestMapping(value = "/contacts.html", method = RequestMethod.GET)
    public ModelAndView showContacts() throws ServiceException {
        logger.debug("Show contacts start");
        ModelAndView modelAndView = new ModelAndView(CONTACTS_PAGE_NAME);
        logger.debug("Show contacts finished");
        return modelAndView;
    }

    private void populateApartment(Apartment apartment, ApartmentForm apartmentForm) throws ServiceException {
        logger.debug("Populating apartment start");

        List<ApartmentImage> apartmentImages = new ArrayList<ApartmentImage>();
        List<MultipartFile> files = apartmentForm.getFiles();

        for (MultipartFile multipartFile : files) {
            if (!multipartFile.isEmpty() &&
                    multipartFile.getContentType().toLowerCase().contains(IMAGE_MIME_PREFIX)) {
                String imageType = multipartFile.getContentType().toLowerCase();
                imageType = imageType.substring(imageType.indexOf("/") + 1);
                BufferedImage bufferedImage = null;
                try {
                    bufferedImage = ImageIO.read(multipartFile.getInputStream());
                } catch (IOException e) {
                    logger.error("Failed to load image", e);
                    throw new ServiceException();
                }
                ApartmentImage apartmentImage = apartmentService.saveImage(bufferedImage, imageType);
                apartmentImages.add(apartmentImage);
            }
        }

        apartment.setApartmentImages(apartmentImages);

        Address address = apartment.getAddress();

        address.setCity(apartmentForm.getCity());
        address.setStreet(apartmentForm.getStreet());
        String houseNumber = apartmentForm.getHouseNumber();
        String buildingNumber = apartmentForm.getBuildingNumber();
        houseNumber = buildingNumber.trim().isEmpty() ? houseNumber : houseNumber + "/" + buildingNumber;
        address.setHouseNumber(houseNumber);

        apartment.setAddress(address);

        apartment.setPrice(Long.parseLong(apartmentForm.getPrice()));
        apartment.setSquare(Integer.parseInt(apartmentForm.getSquare()));
        apartment.setRoomCount(Integer.parseInt(apartmentForm.getRoomCount()));
        apartment.setDescription(apartmentForm.getDescription());

        logger.debug("Populating apartment finished");
    }
}
