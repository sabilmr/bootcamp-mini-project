package com.bootcamp.northwind.model.response;

import com.bootcamp.northwind.model.entity.CustomerDemographicsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDemographicsResponse {
    private String id;
    private String desc;

    public CustomerDemographicsResponse(CustomerDemographicsEntity entity) {
        BeanUtils.copyProperties(entity, this);
    }
}
