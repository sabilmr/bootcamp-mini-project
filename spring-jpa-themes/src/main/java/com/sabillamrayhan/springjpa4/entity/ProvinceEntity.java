package com.sabillamrayhan.springjpa4.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_province")
public class ProvinceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "COUNTRY_ID", insertable = false, updatable = false)
    private Long countryId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "COUNTRY_ID", nullable = false)
    private CountryEntity country;

    @Column(name = "PROVINCE_CODE", nullable = false)
    private String code;

    @Column(name = "PROVINCE_NAME", nullable = false)
    private String name;

    public ProvinceEntity(CountryEntity country, String code, String name) {
        this.country = country;
        this.code = code;
        this.name = name;
    }
}
