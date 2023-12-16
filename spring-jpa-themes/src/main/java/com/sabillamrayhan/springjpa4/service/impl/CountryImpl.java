package com.sabillamrayhan.springjpa4.service.impl;

import com.sabillamrayhan.springjpa4.entity.CountryEntity;
import com.sabillamrayhan.springjpa4.entity.DistrictEntity;
import com.sabillamrayhan.springjpa4.entity.ProvinceEntity;
import com.sabillamrayhan.springjpa4.entity.SubDistrictEntity;
import com.sabillamrayhan.springjpa4.repository.CountryRepo;
import com.sabillamrayhan.springjpa4.repository.DistrictRepo;
import com.sabillamrayhan.springjpa4.repository.ProvinceRepo;
import com.sabillamrayhan.springjpa4.repository.SubDistrictRepo;
import com.sabillamrayhan.springjpa4.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryImpl implements CountryService {
    private final ProvinceRepo provinceRepo;
    private final DistrictRepo districtRepo;
    private final SubDistrictRepo subDistrictRepo;
    private final CountryRepo countryRepo;
    @Override
    public List<CountryEntity> getCountry() {
        return countryRepo.findAll();
    }

    @Override
    public List<ProvinceEntity> getProvince(Long countryId) {
        if (countryId == 0L)
            return Collections.emptyList();

        return provinceRepo.findByCountryId(countryId);
    }

    @Override
    public List<DistrictEntity> getDistrict(Long provinceId) {
        if (provinceId == 0L)
            return Collections.emptyList();

        return districtRepo.findByProvinceId(provinceId);
    }

    @Override
    public List<SubDistrictEntity> getSubDistrict(Long districtId) {
        if (districtId == 0L)
            return Collections.emptyList();

        return subDistrictRepo.findByDistrictId(districtId);
    }
}
