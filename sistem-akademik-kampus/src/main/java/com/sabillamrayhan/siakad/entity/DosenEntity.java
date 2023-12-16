package com.sabillamrayhan.siakad.entity;

import com.sabillamrayhan.siakad.model.DosenModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_dosen")
public class DosenEntity {
    @Id
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "nip")
    private String nip;

    @Column(name = "name", length = 200)
    private String name;

    @Column(name = "jenis_kelamin")
    private String jk;

    @Column(name = "alamat")
    private String alamat;

    @Column(name = "gelar")
    private String gelar;

    @Column(name = "created_at", length = 20)
    private LocalDateTime createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "update_at", length = 20)
    private LocalDateTime updateAt;

    @Column(name = "update_")
    private String updateBy;

    public DosenEntity(DosenModel model) {
        BeanUtils.copyProperties(model,this);
        this.id = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now();
        this.createdBy = "SYSTEM";
        this.updateAt = LocalDateTime.now();
        this.updateBy = "SYSTEM";
    }
}
