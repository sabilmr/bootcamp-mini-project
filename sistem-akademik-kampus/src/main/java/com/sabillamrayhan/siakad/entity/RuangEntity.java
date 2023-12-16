package com.sabillamrayhan.siakad.entity;

import com.sabillamrayhan.siakad.model.RuangModel;
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
@Table(name = "tbl_ruang")
public class RuangEntity {
    @Id
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "code", length = 20, unique = true)
    private String code;

    @Column(name = "name", length = 200)
    private String name;

    @Column(name = "created_at", length = 20)
    private LocalDateTime createdAt;

    @Column(name = "createt_by")
    private String createdBy;

    @Column(name = "update_at", length = 20)
    private LocalDateTime updateAt;

    @Column(name = "update_by")
    private String  updateBy;

    @Column(name = "gedung_id")
    private String gedungId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gedung_id", insertable = false, updatable = false)
    private GedungEntity gedung;

    public RuangEntity(RuangModel model) {
        BeanUtils.copyProperties(model,this);
        this.id = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now();
        this.createdBy = "SYSTEM";
        this.updateAt = LocalDateTime.now();
        this.updateBy = "SYSTEM";
    }
}
