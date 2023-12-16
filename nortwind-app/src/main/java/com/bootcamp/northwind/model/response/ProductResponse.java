package com.bootcamp.northwind.model.response;

import com.bootcamp.northwind.model.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private Long id;
    private Long categoryId;
    private String name;
    private Double quantityPerUnit;
    private Double unitPrice;
    private Double stock;
    private String onOrder;
    private Double reorderLevel;
    private String discontinued;
    private Long supplierId;
    private String companyName;

    public ProductResponse(ProductEntity entity) {
        BeanUtils.copyProperties(entity, this);

        if (entity.getSupplier() != null){
            this.supplierId = entity.getSupplierId();
            this.companyName = entity.getSupplier().getCompanyName();
        }
    }
}
