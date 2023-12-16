package com.sabillamrayhan.siakad.entity;

import com.sabillamrayhan.siakad.model.JurusanModel;
import jakarta.persistence.*;
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
@Table(name = "tbl_jurusan")
public class JurusanEntity {
    @Id
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "code", length = 20, unique = true)
    private String code;

    @Column(name = "name", length = 200)
    private String name;

    @Column(name = "created_at", length = 20)
    private LocalDateTime createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "update_at", length = 20)
    private LocalDateTime updateAt;

    @Column(name = "update_by")
    private String updateBy;

    @Column(name = "fakultas_id",length = 36, nullable = false)
    private String fakultasId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fakultas_id", insertable = false , updatable = false)
    private FakultasEntity fakultas;

    public JurusanEntity(JurusanModel model, FakultasEntity fakultas) {
        BeanUtils.copyProperties(model, this);
        this.id = UUID.randomUUID().toString();
        this.fakultas = fakultas;
        this.createdAt = LocalDateTime.now();
        this.createdBy = "SYSTEM";
        this.updateAt = LocalDateTime.now();
        this.updateBy = "SYSTEM";
    }
}
