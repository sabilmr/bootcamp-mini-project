package com.sabillamrayhan.siakad.model;

import com.sabillamrayhan.siakad.entity.RuangEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RuangModel {
    private String id;
    private String code;
    private String name;
    private String gedungId;
    private String gedungName;
    private String jumlahLantai;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updateAt;
    private String updateBy;
    public RuangModel(RuangEntity entity){
        BeanUtils.copyProperties(entity, this);

        if (entity.getGedung() != null){
            this.gedungId = entity.getGedungId();
            this.gedungName = entity.getGedung().getName();
            this.jumlahLantai = entity.getGedung().getJumlahLantai();
        }
    }
}
