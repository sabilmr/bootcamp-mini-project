package com.sabillamrayhan.springjpa4.model;

import com.sabillamrayhan.springjpa4.entity.CustomerAddressEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerAddressModel {
    private Long id;
    private Long customerId;

    private String addressName;
    private String address;

    private Long districtId;
    private String districtName;

    private Long subDistrictId;
    private String subDistrictName;

    private Long provinceId;
    private String provinceName;

    private Long countryId;
    private String countryName;

    public CustomerAddressModel(CustomerAddressEntity entity){
        BeanUtils.copyProperties(entity,this);

        if (entity.getCountry() != null){
            this.countryName = entity.getCountry().getName();
        }

        if (entity.getProvince() != null){
            this.provinceName = entity.getProvince().getName();
        }

        if (entity.getDistrict() != null){
            this.districtName = entity.getDistrict().getName();
        }

        if (entity.getSubDistrict() != null){
            this.subDistrictName = entity.getSubDistrict().getName();
        }
    }
}
