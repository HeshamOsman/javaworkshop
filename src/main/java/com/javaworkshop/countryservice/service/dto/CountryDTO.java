package com.javaworkshop.countryservice.service.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class CountryDTO {

    final private String name;

    final private String continent;

    final private Integer population;

    final private Double lifeExpectancy;

    final private String countryLanguage;


}
