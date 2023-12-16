package com.sabillamrayhan.springjpa4.model;

import com.sabillamrayhan.springjpa4.entity.CategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryModel {
    private Long id;
    private String name;

    public CategoryModel(CategoryEntity entity){
        this.id = entity.getId();
        this.name = entity.getName();
    }
}
