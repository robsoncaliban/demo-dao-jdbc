package com.dao_jdbc.dao_impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dao_jdbc.dao.SellerDao;
import com.dao_jdbc.db.DB;
import com.dao_jdbc.db.DbException;
import com.dao_jdbc.model.Department;
import com.dao_jdbc.model.Seller;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SellerDaoJDBC implements SellerDao{

    private Connection conn;

    @Override
    public void insert(Seller seller) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                "INSERT INTO seller "
                + "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
                + "VALUES (?, ?, ?, ?, ?) ",
                Statement.RETURN_GENERATED_KEYS);

            st.setString(1, seller.getName());
            st.setString(2, seller.getEmail());
            st.setDate(3, Date.valueOf(seller.getBirthDate()));
            st.setDouble(4, seller.getBaseSalary());
            st.setInt(5, seller.getDepartment().getId());

            int rowsAffected = st.executeUpdate();

            if(rowsAffected > 0){
                ResultSet rs = st.getGeneratedKeys();
                if(rs.next()){
                    int id = rs.getInt(1);
                    seller.setId(id);
                    DB.closeResultSet(rs);
                    return;
                }
            }
            throw new DbException("Unexpected error!, No rows affected!");

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally{
            DB.closeStatement(st);
        }
        
    }

    @Override
    public void update(Seller seller) {
         PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                "UPDATE seller "
                + "SET Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ? "
                + "WHERE Id = ? ");

            st.setString(1, seller.getName());
            st.setString(2, seller.getEmail());
            st.setDate(3, Date.valueOf(seller.getBirthDate()));
            st.setDouble(4, seller.getBaseSalary());
            st.setInt(5, seller.getDepartment().getId());
            st.setInt(6, seller.getId());

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally{
            DB.closeStatement(st);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("DELETE FROM seller WHERE Id = ?");
            st.setInt(1, id);
            int rowsAffected = st.executeUpdate();
            if(rowsAffected == 0){
                throw new DbException("id not found");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally{
            DB.closeStatement(st);
        }
    }

    @Override
    public Seller findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                "SELECT seller.*,department.Name as DepName "
                + "FROM seller INNER JOIN department "
                + "ON seller.DepartmentId = department.Id "
                + "WHERE seller.Id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();
            if(rs.next()){
                Department dep = EntitiesFactory.instantiateDepartment(rs);
                Seller seller = EntitiesFactory.instantiateSeller(rs, dep);
                return seller;
            }
            return null;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally{
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Seller> findByDepartment(Department dep) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                "SELECT seller.*,department.Name as DepName "
                + "FROM seller INNER JOIN department "
                + "ON seller.DepartmentId = department.Id "
                + "WHERE DepartmentId = ? "
                + "ORDER BY Name");
            st.setInt(1, dep.getId());
            rs = st.executeQuery();
            
            List<Seller> list = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>();
            
            while(rs.next()){
                Department department = map.get(rs.getInt("DepartmentId"));
                if(department == null){
                    department = EntitiesFactory.instantiateDepartment(rs);
                    map.put(rs.getInt("DepartmentId"), department);
                }
                Seller seller = EntitiesFactory.instantiateSeller(rs, department);
                list.add(seller);
            }
            return list;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally{
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Seller> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                "SELECT seller.*,department.Name as DepName "
                + "FROM seller INNER JOIN department "
                + "ON seller.DepartmentId = department.Id "
                + "ORDER BY Name");
            rs = st.executeQuery();
            
            List<Seller> list = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>();
            
            while(rs.next()){
                Department department = map.get(rs.getInt("DepartmentId"));

                if(department == null){
                    department = EntitiesFactory.instantiateDepartment(rs);
                    map.put(rs.getInt("DepartmentId"), department);
                }
                Seller seller = EntitiesFactory.instantiateSeller(rs, department);
                list.add(seller);
            }
            return list;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally{
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

  
    
}
