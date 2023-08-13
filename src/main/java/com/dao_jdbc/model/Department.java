package com.dao_jdbc.model;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Department implements Serializable {
    private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include
    private Integer id;
    private String name;

    public Department(){

    }

    public Department(Integer id, String name){
        this.id = id;
        this.name = name;
    }
    

}
