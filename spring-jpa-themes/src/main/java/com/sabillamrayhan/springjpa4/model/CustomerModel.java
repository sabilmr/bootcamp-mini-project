package com.sabillamrayhan.springjpa4.model;

import com.sabillamrayhan.springjpa4.entity.CustomerEntity;
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
public class CustomerModel {
    private Long id;
    private Long accountNo;
    private String name;
    private String email;
    private String phoneNumber;
    private List<CustomerAddressModel> address = new ArrayList<>();
    public CustomerModel(CustomerEntity entity){
        BeanUtils.copyProperties(entity,this);

        if (!entity.getAddress().isEmpty()){
            this.address = entity.getAddress().stream()
                    .map(CustomerAddressModel::new)
                    .collect(Collectors.toList());
        }
    }
}
