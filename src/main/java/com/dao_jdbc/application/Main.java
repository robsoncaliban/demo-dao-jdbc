package com.dao_jdbc.application;


import com.dao_jdbc.dao.DaoFactory;
import com.dao_jdbc.dao.SellerDao;
import com.dao_jdbc.model.Seller;

public class Main {
    public static void main(String[] args) {
        SellerDao sellerDao = DaoFactory.creatSellerDao();
        Seller seller = sellerDao.findById(3);
        System.out.println(seller);
    }
}
