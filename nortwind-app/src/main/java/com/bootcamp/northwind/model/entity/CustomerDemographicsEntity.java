package com.bootcamp.northwind.model.entity;

import com.bootcamp.northwind.model.response.CustomerDemoResponse;
import com.bootcamp.northwind.model.response.CustomerDemographicsResponse;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_customer_demographics")
public class CustomerDemographicsEntity {
    @Id
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "description")
    private String desc;

    public CustomerDemographicsEntity(CustomerDemographicsResponse response) {
        BeanUtils.copyProperties(response, this);
        this.id = UUID.randomUUID().toString();
    }
}
