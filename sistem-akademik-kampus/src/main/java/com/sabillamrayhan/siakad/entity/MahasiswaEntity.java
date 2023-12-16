package com.sabillamrayhan.siakad.entity;

import com.sabillamrayhan.siakad.model.MahasiswaModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_mahasiswa")
public class MahasiswaEntity {
    @Id
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "name", length = 200)
    private String name;

    @Column(name = "nim")
    private Integer nim;

    @Column(name = "gender")
    private String gender;

    @Column(name = "alamat")
    private String alamat;

    @Column(name = "tempat_lahir")
    private String tmptLahir;

    @Column(name = "tanggal_lahir")
    private LocalDate tglLahir;

    @Column(name = "agama")
    private String agama;

    @Column(name = "created_at", length = 20)
    private LocalDateTime createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "update_at", length = 20)
    private LocalDateTime updateAt;

    @Column(name = "update_by")
    private String updateBy;

    @Column(name = "jurusan_id")
    private String jurusanId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "jurusan_id", insertable = false, updatable = false)
    private JurusanEntity jurusan;

    @Column(name = "fakultas_id")
    private String fakultasId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fakultas_id", insertable = false, updatable = false)
    private FakultasEntity fakultas;

    public MahasiswaEntity(MahasiswaModel model) {
        BeanUtils.copyProperties(model,this);
        this.id = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now();
        this.createdBy = "SYSTEM";
        this.updateAt = LocalDateTime.now();
        this.updateBy = "SYSTEM";
    }
}
