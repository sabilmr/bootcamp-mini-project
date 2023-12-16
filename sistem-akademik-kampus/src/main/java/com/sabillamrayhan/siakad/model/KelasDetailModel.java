package com.sabillamrayhan.siakad.model;

import com.sabillamrayhan.siakad.entity.KelasDetailEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KelasDetailModel {
    private String id;

    private String kelasId;
    private KelasModel kelas;

    private String mahasiswaId;
    private MahasiswaModel mahasiswa;

    private String status;

    public KelasDetailModel(KelasDetailEntity entity) {
        BeanUtils.copyProperties(entity, this);

        if (entity.getKelas() != null){
            this.kelasId = entity.getKelasId();
            this.kelas = new KelasModel(entity.getKelas());
        }

        if (entity.getMahasiswa() != null){
            this.mahasiswaId = entity.getMahasiswaId();
            this.mahasiswa = new MahasiswaModel(entity.getMahasiswa());
        }
    }
}
