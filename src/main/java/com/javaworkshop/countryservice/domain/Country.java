package com.javaworkshop.countryservice.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Country {

    @Id
    private String code;

    @Column
    private String name;

    @Column
    private String continent;

    @Column
    private String region;

    @Column(name = "surface_area")
    private Double surfaceArea;

    @Column(name = "indep_year")
    private Integer independentYear;

    @Column
    private Integer population;

    @Column(name = "life_expectancy")
    private Double lifeExpectancy;

    @Column
    private Double gnp;

    @Column(name = "gnp_old")
    private Double gnpOld;

    @Column(name="local_name")
    private String localName;

    @Column(name="government_form")
    private String governmentForm;

    @Column(name="head_of_state")
    private String headOfState;

    @OneToOne
    @JoinColumn(name="capital")
    private City capital;

    @Column(name="code2")
    private String code2;

    @OneToMany
    @JoinColumn(name = "country_code")
    private List<CountryLanguage> languages;
}
