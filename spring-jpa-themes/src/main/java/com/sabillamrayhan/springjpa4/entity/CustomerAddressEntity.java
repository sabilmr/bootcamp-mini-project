package com.sabillamrayhan.springjpa4.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_customer_address")
public class CustomerAddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CUSTOMER_ADDR_ID")
    private Long id;

    @Column(name = "CUSTOMER_ID", insertable = false, updatable = false)
    private Long customerId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CUSTOMER_ID")
    private CustomerEntity customer;

    @Column(name = "ADDRESS_NAME")
    private String addressName;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "DISTRICT_ID")
    private Long districtId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DISTRICT_ID", insertable = false, updatable = false)
    private DistrictEntity district;

    @Column(name = "SUB_DISTRICT_ID")
    private Long subDistrictId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SUB_DISTRICT_ID", insertable = false, updatable = false)
    private SubDistrictEntity subDistrict;

    @Column(name = "PROVINCE_ID")
    private Long provinceId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PROVINCE", insertable = false, updatable = false)
    private ProvinceEntity province;

    @Column(name = "COUNTRY_ID")
    private Long countryId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "COUNTRY_ID", insertable = false, updatable = false)
    private CountryEntity country;
}
