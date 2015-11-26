package com.es.apartment.dao;

import com.es.apartment.entity.Apartment;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ApartmentDao extends PagingAndSortingRepository<Apartment, String> {
}
