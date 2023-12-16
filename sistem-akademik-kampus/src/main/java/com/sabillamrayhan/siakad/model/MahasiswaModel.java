package com.sabillamrayhan.siakad.model;

import com.sabillamrayhan.siakad.entity.MahasiswaEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MahasiswaModel {
    private String id;
    private String name;
    private Integer nim;
    private String gender;
    private String alamat;
    private String tmptLahir;
    @DateTimeFormat(pattern = "yyyy-MM-DD")
    private LocalDate tglLahir;
    private String agama;

    private String jurusanId;
    private String jurusanName;

    private String fakultasId;
    private String fakultasName;

    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updateAt;
    private String updateBy;
    public MahasiswaModel(MahasiswaEntity entity){
        BeanUtils.copyProperties(entity,this);

        if (entity.getJurusan() != null){
            this.jurusanId = entity.getJurusanId();
            this.jurusanName = entity.getJurusan().getName();
        }

        if (entity.getFakultas() != null){
            this.fakultasId = entity.getFakultasId();
            this.fakultasName = entity.getFakultas().getName();
        }
    }
}
