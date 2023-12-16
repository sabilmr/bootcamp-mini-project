package com.sabillamrayhan.springjpa4.controller;

import com.sabillamrayhan.springjpa4.entity.CountryEntity;
import com.sabillamrayhan.springjpa4.entity.ProvinceEntity;
import com.sabillamrayhan.springjpa4.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(".api/v1/country")
@RequiredArgsConstructor
public class ApiCountryController {
    private final CountryService countryService;

    @GetMapping()
    public List<CountryEntity> getCountry(){
        return countryService.getCountry();
    }

    @GetMapping("/province/{id}")
    public List<ProvinceEntity> getProvince(@PathVariable("id") Long id){
        return countryService.getProvince(id);
    }

}
