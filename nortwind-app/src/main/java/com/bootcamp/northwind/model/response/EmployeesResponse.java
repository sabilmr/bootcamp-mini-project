package com.bootcamp.northwind.model.response;

import com.bootcamp.northwind.model.entity.EmployeesEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeesResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String title;
    private String titleOfCourtesy;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate birthDate;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate hireDate;
    private String address;
    private String city;
    private String region;
    private String postalCode;
    private String country;
    private String homePhone;
    private String extension;
    private String notes;
    private String reportsTo;
    private String photoPath;

    public EmployeesResponse(EmployeesEntity entity) {
        BeanUtils.copyProperties(entity, this);
    }
}
