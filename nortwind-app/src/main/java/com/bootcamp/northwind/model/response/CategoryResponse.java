package com.bootcamp.northwind.model.response;

import com.bootcamp.northwind.model.entity.CategoryEntity;
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
public class CategoryResponse {
    private Long id;
    private String name;
    private String desc;
    private List<ProductResponse> product = new ArrayList<>();

    public CategoryResponse(CategoryEntity entity) {
        BeanUtils.copyProperties(entity, this);

        if (!entity.getProduct().isEmpty()){
            this.product = entity.getProduct().stream()
                    .map(ProductResponse::new)
                    .collect(Collectors.toList());
        }
    }
}
