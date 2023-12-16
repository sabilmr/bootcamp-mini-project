package com.bootcamp.northwind.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {
    private String id;
    private String name;
    private Double quantityPerUnit;
    private Double unitPrice;
    private Double stock;
    private String onOrder;
    private Double reorderLevel;
    private String discontinued;
    private String categoryId;
    private String supplierId;
    private String categoryName;
}
