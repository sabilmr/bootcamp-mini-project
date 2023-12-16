package com.sabillamrayhan.siakad.model;

import com.sabillamrayhan.siakad.entity.FakultasEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FakultasModel {
    private String id;
    private String code;
    private String name;
    private List<JurusanModel> jurusans = new ArrayList<>();

    public FakultasModel(FakultasEntity entity){
        BeanUtils.copyProperties(entity, this);

    }
}
