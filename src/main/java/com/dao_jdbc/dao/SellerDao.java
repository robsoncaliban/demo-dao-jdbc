package com.dao_jdbc.dao;

import java.util.List;

import com.dao_jdbc.model.Department;
import com.dao_jdbc.model.Seller;

public interface SellerDao {

    void insert(Seller obj);
    void update(Seller obj);
    void deleteById(Integer id);
    Seller findById(Integer id);
    List<Seller> findByDepartment(Department dep);
    List<Seller> findAll();
}
