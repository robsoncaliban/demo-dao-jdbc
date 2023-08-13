package com.dao_jdbc.model;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Seller implements Serializable {
    private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include
    private Integer id;
    private String name;
    private String email;
    private LocalDate birthDate;
    private double baseSalary;
    private Department department;
    
    public Seller() {
    }

    public Seller(Integer id, String name, String email, LocalDate birthDate, double baseSalary, Department department) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
        this.baseSalary = baseSalary;
        this.department = department;
    }
    
    
}
