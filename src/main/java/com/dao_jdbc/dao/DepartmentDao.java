package com.dao_jdbc.dao;

import java.util.List;

import com.dao_jdbc.model.Department;

public interface DepartmentDao{
    
    void insert(Department obj);
    void update(Department obj);
    void deleteById(Integer id);
    Department findById(Integer id);
    List<Department> findAll();


}