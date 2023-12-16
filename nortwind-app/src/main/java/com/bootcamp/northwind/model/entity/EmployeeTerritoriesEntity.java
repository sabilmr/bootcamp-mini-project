package com.bootcamp.northwind.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_employee_teritories")
public class EmployeeTerritoriesEntity {
    @Id
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "employee_id", length = 36)
    private String employeeId;

    @Column(name = "territory_id", length = 36)
    private String territoryId;
}
