package com.sabillamrayhan.springjpa4.service;

import com.sabillamrayhan.springjpa4.entity.CountryEntity;
import com.sabillamrayhan.springjpa4.entity.DistrictEntity;
import com.sabillamrayhan.springjpa4.entity.ProvinceEntity;
import com.sabillamrayhan.springjpa4.entity.SubDistrictEntity;

import java.util.List;

public interface CountryService {
    List<CountryEntity> getCountry();
    List<ProvinceEntity> getProvince(Long countryId);
    List<DistrictEntity> getDistrict(Long provinceId);
    List<SubDistrictEntity> getSubDistrict(Long districtId);
}
