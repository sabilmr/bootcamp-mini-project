package com.sabillamrayhan.siakad.model;

import com.sabillamrayhan.siakad.entity.JurusanEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JurusanModel {
    private String id;
    private String code;
    private String name;
    private String fakultasId;
    private String fakultasName;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updateAt;
    private String updateBy;

    public JurusanModel(JurusanEntity entity){
        BeanUtils.copyProperties(entity,this);

        if (entity.getFakultas() != null){
            this.fakultasId = entity.getFakultasId();
            this.fakultasName = entity.getFakultas().getName();
        }
    }
}
