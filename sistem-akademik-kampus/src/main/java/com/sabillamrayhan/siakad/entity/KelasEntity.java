package com.sabillamrayhan.siakad.entity;

import com.sabillamrayhan.siakad.model.KelasModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_kelas")
public class KelasEntity {
    @Id
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "code", length = 20, unique = true)
    private String code;

    @Column(name = "name_hari")
    private String nameHari;

    @Temporal(TemporalType.TIME)
    @Column(name = "jam_mulai")
    private Date jamMulai;

    @Temporal(TemporalType.TIME)
    @Column(name = "jam_selesai")
    private Date jamSelesai;

    @Column(name = "status")
    private String status;

    @Column(name = "semester")
    private Integer semester;

    @Column(name = "tahun_ajaran")
    private Integer tahunAjaran;

    @Column(name = "kuota")
    private Integer kuota;

    @Column(name = "bisa_online")
    private Boolean bisaOnline;

    @Column(name = "created_at", length = 20)
    private LocalDateTime createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "update_at", length = 20)
    private LocalDateTime updateAt;

    @Column(name = "update_by")
    private String updateBy;

    @Column(name = "ruang_id")
    private String ruangId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ruang_id", insertable = false, updatable = false)
    private RuangEntity ruang;

    @Column(name = "dosen_id")
    private String dosenId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dosen_id", insertable = false, updatable = false)
    private DosenEntity dosen;

    @Column(name = "mata_kuliah_id")
    private String mataKuliahId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mata_kuliah_id", insertable = false, updatable = false)
    private MataKuliahEntity mataKuliah;

    public KelasEntity(KelasModel model) {
        BeanUtils.copyProperties(model,this);
        this.id = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now();
        this.createdBy = "SYSTEM";
        this.updateAt = LocalDateTime.now();
        this.updateBy = "SYSTEM";
    }
}
