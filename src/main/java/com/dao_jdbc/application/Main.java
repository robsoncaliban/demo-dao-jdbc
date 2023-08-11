package com.dao_jdbc.application;

import com.dao_jdbc.db.DB;

public class Main {
    public static void main(String[] args) {
        DB.getConnection();
        DB.closeConnection();
    }
}
