package com.javaworkshop.countryservice.controller;

import com.javaworkshop.countryservice.service.CountryService;
import com.javaworkshop.countryservice.service.dto.CountryDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/{countryCode}")
    public ResponseEntity<CountryDTO> getCountryInfoByCode(@PathVariable String countryCode) {
        return ResponseEntity.ok(countryService.findCountryByCode(countryCode.toUpperCase()));
    }
}
