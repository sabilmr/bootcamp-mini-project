package com.sabillamrayhan.siakad.model;

import com.sabillamrayhan.siakad.entity.MataKuliahEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MataKuliahModel {
    private String id;
    private String code;
    private String  name;
    private Integer sks;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updateAt;
    private String updateBy;

    public MataKuliahModel(MataKuliahEntity entity){
        BeanUtils.copyProperties(entity,this);
    }
}
