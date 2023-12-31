package com.dao_jdbc.application;


import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.dao_jdbc.dao.DaoFactory;
import com.dao_jdbc.dao.SellerDao;
import com.dao_jdbc.db.DbException;
import com.dao_jdbc.model.Department;
import com.dao_jdbc.model.Seller;

public class TesteSeller {
    public static void main(String[] args) {
       
        SellerDao sellerDao = DaoFactory.creatSellerDao();

        Seller seller = sellerDao.findById(3);
        System.out.println(seller);

        System.out.println();
        
        Department department = new Department(2, null);
        List<Seller> list = sellerDao.findByDepartment(department);
        list.forEach(System.out::println);

        System.out.println();

        LocalDate date = LocalDate.parse("2004-02-28");
        Seller newSeller = new Seller(null, "Greg", "greg@gmail.com", date , 4000, department);
        sellerDao.insert(newSeller);
        System.out.println(newSeller.getId());

        System.out.println();

        newSeller.setName("Martha Waine");
        newSeller.setEmail("marthawaine@gmail.com");
        sellerDao.update(newSeller);

        System.out.println();

        Scanner sc = new Scanner(System.in);
        boolean sellerDeleted = false;
        
        while(!sellerDeleted){
            System.out.print("Enter id for delete: ");
            int id = Integer.parseInt(sc.nextLine());

            try {
                sellerDao.deleteById(id);
                sellerDeleted = true;
                System.out.println("\nsuccessfully deleted seller");
            } catch (DbException e) {
                System.out.println(e.getMessage());
            }

        }
        sc.close();
        System.out.println();

        list = sellerDao.findAll();
        list.forEach(System.out::println);

    }
}
