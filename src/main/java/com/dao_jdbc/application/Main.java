package com.dao_jdbc.application;

import java.time.LocalDate;

import com.dao_jdbc.model.Department;
import com.dao_jdbc.model.Seller;

public class Main {
    public static void main(String[] args) {
        Department obj = new Department(1, "Books");
        LocalDate date = LocalDate.parse("2004-02-28");

        Seller seller = new Seller(21, "Bob", "bob@gmail.com", date , 3000.0, obj);
        System.out.println(seller);
    }
}
