package com.javaworkshop.countryservice.service;

import com.javaworkshop.countryservice.service.dto.CountryDTO;

public interface CountryService {

    CountryDTO findCountryByCode(String code);
}
