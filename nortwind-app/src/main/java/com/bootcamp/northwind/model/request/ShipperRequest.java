package com.bootcamp.northwind.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShipperRequest {
    private String id;
    private String companyName;
    private String phone;
}
