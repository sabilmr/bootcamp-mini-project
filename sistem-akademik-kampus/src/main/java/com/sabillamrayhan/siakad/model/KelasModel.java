package com.sabillamrayhan.siakad.model;

import com.sabillamrayhan.siakad.entity.KelasEntity;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KelasModel {
    private String id;
    private String code;
    private String nameHari;
    @DateTimeFormat(pattern = "HH:mm")
    private Date jamMulai;
    @DateTimeFormat(pattern = "HH:mm")
    private Date jamSelesai;
    private String status;
    private Integer semester;
    private Integer tahunAjaran;
    private Integer kuota;
    private Boolean bisaOnline;

    private String ruangId;
    private String ruangName;

    private String dosenId;
    private String dosenName;

    private String mataKuliahId;
    private String mataKuliahName;

    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updateAt;
    private String updateBy;

    public KelasModel(KelasEntity entity) {
        BeanUtils.copyProperties(entity, this);

        if (entity.getRuang() != null){
            this.ruangId = entity.getRuangId();
            this.ruangName = entity.getRuang().getName();
        }

        if (entity.getDosen() != null){
            this.dosenId = entity.getDosenId();
            this.dosenName = entity.getDosen().getName();
        }

        if (entity.getMataKuliah() != null){
            this.mataKuliahId = entity.getMataKuliahId();
            this.mataKuliahName = entity.getMataKuliah().getName();
        }
    }
}
