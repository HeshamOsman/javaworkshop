package com.javaworkshop.countryservice.service.impl;

import com.javaworkshop.countryservice.domain.Country;
import com.javaworkshop.countryservice.domain.CountryLanguage;
import com.javaworkshop.countryservice.repository.CountryRepository;
import com.javaworkshop.countryservice.service.CountryService;
import com.javaworkshop.countryservice.service.dto.CountryDTO;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public CountryDTO findCountryByCode(String code) {
        Function<CountryLanguage,String> mapCountryLanguageToLanguageString = countryLanguage -> countryLanguage.getLanguage();
        return countryRepository.findById(code).map(country ->
            new CountryDTO(country.getName(),country.getContinent(),country.getPopulation(),country.getLifeExpectancy(),
                    //Set language to the first official language if not use any language if not set language to null
                    country.getLanguages().stream().filter(countryLanguage -> countryLanguage.getIsOfficial()).findFirst()
                            .map(mapCountryLanguageToLanguageString).orElseGet(
                            () -> country.getLanguages().stream().findFirst().map(mapCountryLanguageToLanguageString).orElse(null)
                                                                     )
            )

        ).orElseThrow(()-> new RuntimeException());
    }
}
