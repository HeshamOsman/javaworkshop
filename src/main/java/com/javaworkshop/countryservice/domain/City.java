package com.javaworkshop.countryservice.domain;

import lombok.Data;

import javax.persistence.*;


@Entity
@Data
public class City {

    @Id
    private Integer id;

    @Column
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_code")
    private Country country;

    @Column
    private String district;

    @Column
    private Integer population;

}
