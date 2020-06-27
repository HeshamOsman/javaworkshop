package com.javaworkshop.countryservice.domain.compositeid;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;

@Embeddable
@Data
public class CountryLanguagePK implements Serializable {

    @Column(name = "country_code")
    private String countryCode;

    @Column
    private String language;

}
