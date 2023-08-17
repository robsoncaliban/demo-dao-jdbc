package com.dao_jdbc.application;

import java.util.List;
import java.util.Scanner;

import com.dao_jdbc.dao.DaoFactory;
import com.dao_jdbc.dao.DepartmentDao;
import com.dao_jdbc.db.DbException;
import com.dao_jdbc.model.Department;

public class TesteDepartment {
    public static void main(String[] args) {
        DepartmentDao depDao = DaoFactory.creatDepartmentDao();

        Department department = new Department(null,"Marketing");
        depDao.insert(department);

        

        department.setName("Legal");
        depDao.update(department);
        System.out.println(department.getName());
        System.out.println(department.getId());
        System.out.println(depDao.findById(department.getId()));

        depDao.deleteById(department.getId());
        
        Scanner sc = new Scanner(System.in);
        boolean depDeleted = false;
        
        while(!depDeleted){
            System.out.print("Enter id for delete: ");
            int id = Integer.parseInt(sc.nextLine());

            try {
                depDao.deleteById(id);
                depDeleted = true;
                System.out.println("\nsuccessfully deleted seller");
            } catch (DbException e) {
                System.out.println(e.getMessage());
            }

        }
        sc.close();

        List<Department> list = depDao.findAll();
        list.forEach(System.out::println);




    }
}
