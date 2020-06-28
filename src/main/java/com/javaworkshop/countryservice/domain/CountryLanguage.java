package com.javaworkshop.countryservice.domain;

import com.javaworkshop.countryservice.domain.compositeid.CountryLanguagePK;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "country_language")
@IdClass(CountryLanguagePK.class)
public class CountryLanguage {

    @Id
    private String countryCode;

    @Id
    private String language;

    @Column(name = "is_official")
    private Boolean isOfficial;

    @Column
    private Double percentage;


}
