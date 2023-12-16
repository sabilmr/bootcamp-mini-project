package com.bootcamp.northwind.model.response;

import com.bootcamp.northwind.model.entity.CustomerEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {
    private Long id;
    private String companyName;
    private String contactName;
    private String contactTitle;
    private String address;
    private String city;
    private String region;
    private String postalCode;
    private String country;
    private String phone;
    private String fax;

    public CustomerResponse(CustomerEntity entity) {
        BeanUtils.copyProperties(entity, this);
        this.id = entity.getId();
    }
}
