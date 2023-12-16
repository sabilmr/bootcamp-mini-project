package com.sabillamrayhan.springjpa4.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_sub_district")
public class SubDistrictEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "DISTRICT_ID", insertable = false, updatable = false)
    private Long districtId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DISTRICT_ID", nullable = false)
    private DistrictEntity district;

    @Column(name = "SUB_DISTRICT_CODE", nullable = false)
    private String code;

    @Column(name = "SUB_DISTRICT_NAME", nullable = false)
    private String name;

    public SubDistrictEntity(DistrictEntity district, String code, String name) {
        this.district = district;
        this.code = code;
        this.name = name;
    }
}
