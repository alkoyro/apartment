package com.es.apartment.web.form.validator;

import com.es.apartment.web.form.ApartmentForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component("apartmentFormValidator")
public class ApartmentFormValidator implements Validator {
    private static final Integer MAX_ALLOWED_DESCRPIPTION_SIZE = 1000;

    private static final String PRICE_FIELD = "price";
    private static final String CITY_FIELD = "city";
    private static final String STREET_FIELD = "street";
    private static final String HOUSE_NUMBER_FIELD = "houseNumber";
    private static final String ROOM_COUNT_FIELD = "roomCount";
    private static final String DESCRIPTION_FIELD = "description";
    private static final String SQUARE_FIELD = "square";

    private static final String EMPTY_PRICE_ERROR_CODE = "price.empty";
    private static final String EMPTY_CITY_ERROR_CODE = "city.empty";
    private static final String EMPTY_STREET_ERROR_CODE = "street.empty";
    private static final String EMPTY_HOUSE_NUMBER_ERROR_CODE = "houseNumber.empty";
    private static final String EMPTY_ROOM_COUNT_ERROR_CODE = "roomCount.empty";
    private static final String EMPTY_DESCRIPTION_ERROR_CODE = "description.empty";
    private static final String EMPTY_SQUARE_ERROR_CODE = "square.empty";

    private static final String INVALID_PRICE_ERROR_CODE = "price.invalid";
    private static final String INVALID_HOUSE_NUMBER_ERROR_CODE = "houseNumber.invalid";
    private static final String INVALID_ROOM_COUNT_ERROR_CODE = "roomCount.invalid";
    private static final String INVALID_SQUARE_ERROR_CODE = "square.invalid";
    private static final String INVALID_DESCRIPTION_ERROR_CODE = "description.invalid";

    private static final String PRICE_REQUIRED_MESSAGE = "*Price is required";
    private static final String CITY_REQUIRED_MESSAGE = "*City is required";
    private static final String STREET_REQUIRED_MESSAGE = "*Street is required";
    private static final String HOUSE_NUMBER_REQUIRED_MESSAGE = "*House number is required";
    private static final String ROOM_COUNT_REQUIRED_MESSAGE = "*Room count is required";
    private static final String DESCRIPTION_REQUIRED_MESSAGE = "*Description is required";
    private static final String SQUARE_REQUIRED_MESSAGE = "*Square is required";

    private static final String INVALID_PRICE_MESSAGE = "*Price must be positive value";
    private static final String INVALID_HOUSE_NUMBER_MESSAGE = "*House number must be positive value";
    private static final String INVALID_ROOM_COUNT_MESSAGE = "*Room count must be positive value";
    private static final String INVALID_SQUARE_MESSAGE = "*Square must be positive value";
    private static final String INVALID_DESCRIPTION_MESSAGE = "*Description too long";

    private static final String UNREAL_ROOM_COUNT_MESSAGE = "*Room count must be less than 10";

    private static final String REGEX = "[0-9]*";

    private static final int MAX_ROOM_COUNT = 10;


    private static Logger logger = LoggerFactory.getLogger(ApartmentFormValidator.class);

    @Override
    public boolean supports(Class<?> aClass) {
        logger.debug("Supporting start");
        boolean result = ApartmentForm.class.isAssignableFrom(aClass);
        logger.debug("Supporting finished");
        return result;
    }

    @Override
    public void validate(Object object, Errors errors) {
        logger.debug("Validating start");
        ApartmentForm apartmentForm = (ApartmentForm) object;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, PRICE_FIELD,
                EMPTY_PRICE_ERROR_CODE, PRICE_REQUIRED_MESSAGE);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, CITY_FIELD,
                EMPTY_CITY_ERROR_CODE, CITY_REQUIRED_MESSAGE);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, STREET_FIELD,
                EMPTY_STREET_ERROR_CODE, STREET_REQUIRED_MESSAGE);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, HOUSE_NUMBER_FIELD,
                EMPTY_HOUSE_NUMBER_ERROR_CODE, HOUSE_NUMBER_REQUIRED_MESSAGE);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, ROOM_COUNT_FIELD,
                EMPTY_ROOM_COUNT_ERROR_CODE, ROOM_COUNT_REQUIRED_MESSAGE);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, DESCRIPTION_FIELD,
                EMPTY_DESCRIPTION_ERROR_CODE, DESCRIPTION_REQUIRED_MESSAGE);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, SQUARE_FIELD,
                EMPTY_SQUARE_ERROR_CODE, SQUARE_REQUIRED_MESSAGE);

        if (errors.hasErrors()) {
            logger.debug("Validating finished");
            return;
        }

        if (!apartmentForm.getPrice().matches(REGEX)) {
            errors.rejectValue(PRICE_FIELD, INVALID_PRICE_ERROR_CODE,
                    INVALID_PRICE_MESSAGE);
        } else {
            Long price = Long.parseLong(apartmentForm.getPrice());
            if (price <= 0) {
                errors.rejectValue(PRICE_FIELD, INVALID_PRICE_ERROR_CODE,
                        INVALID_PRICE_MESSAGE);
            }
        }

        if (!apartmentForm.getHouseNumber().matches(REGEX)) {
            errors.rejectValue(HOUSE_NUMBER_FIELD, INVALID_HOUSE_NUMBER_ERROR_CODE,
                    INVALID_HOUSE_NUMBER_MESSAGE);
        } else {
            Integer houseNumber = Integer.parseInt(apartmentForm.getHouseNumber());
            if (houseNumber <= 0) {
                errors.rejectValue(HOUSE_NUMBER_FIELD, INVALID_HOUSE_NUMBER_ERROR_CODE,
                        INVALID_HOUSE_NUMBER_MESSAGE);
            }
        }

        if (!apartmentForm.getSquare().matches(REGEX)) {
            errors.rejectValue(SQUARE_FIELD, INVALID_SQUARE_ERROR_CODE,
                    INVALID_SQUARE_MESSAGE);
        } else {
            Integer square = Integer.parseInt(apartmentForm.getSquare());
            if (square <= 0) {
                errors.rejectValue(SQUARE_FIELD, INVALID_SQUARE_ERROR_CODE,
                        INVALID_SQUARE_MESSAGE);
            }
        }

        if (apartmentForm.getDescription().length() > MAX_ALLOWED_DESCRPIPTION_SIZE) {
            errors.rejectValue(DESCRIPTION_FIELD, INVALID_DESCRIPTION_ERROR_CODE,
                    INVALID_DESCRIPTION_MESSAGE);
        }

        if (!apartmentForm.getRoomCount().matches(REGEX)) {
            errors.rejectValue(ROOM_COUNT_FIELD, INVALID_ROOM_COUNT_ERROR_CODE,
                    INVALID_ROOM_COUNT_MESSAGE);
        } else {
            Integer roomCount = Integer.parseInt(apartmentForm.getRoomCount());
            if (roomCount > MAX_ROOM_COUNT) {
                errors.rejectValue(ROOM_COUNT_FIELD, INVALID_ROOM_COUNT_ERROR_CODE,
                        UNREAL_ROOM_COUNT_MESSAGE);
            }
            if (roomCount <= 0) {
                errors.rejectValue(ROOM_COUNT_FIELD, INVALID_ROOM_COUNT_ERROR_CODE,
                        INVALID_ROOM_COUNT_MESSAGE);
            }
        }
        logger.debug("Validating finished");
    }
}
