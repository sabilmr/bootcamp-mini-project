package com.bootcamp.northwind.model.entity;

import com.bootcamp.northwind.model.response.CategoryResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_category")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String desc;

   @OneToMany(mappedBy = "category", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ProductEntity> product = new ArrayList<>();

    public CategoryEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addProduct(ProductEntity product){
        this.product.add(product);
        product.setCategory(this);
    }
}
