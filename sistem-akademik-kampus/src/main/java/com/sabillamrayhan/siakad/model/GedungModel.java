package com.sabillamrayhan.siakad.model;

import com.sabillamrayhan.siakad.entity.GedungEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GedungModel {
    private String id;
    private String code;
    private String name;
    private String jumlahLantai;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updateAt;
    private String updateBy;

    public GedungModel(GedungEntity entity){
        BeanUtils.copyProperties(entity, this);

    }
}
