package com.dao_jdbc.dao;

import com.dao_jdbc.dao_impl.SellerDaoJDBC;
import com.dao_jdbc.db.DB;

public class DaoFactory {
    
    public static SellerDao creatSellerDao(){
        return new SellerDaoJDBC(DB.getConnection());
    }
}
