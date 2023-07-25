package com.example.demo;

import java.sql.*;

public class DBconnect {
    public static Connection databaselink;



    public static Connection getConnection(){
        String dbname ="Library_db";
        String dbuser ="root";
        String dbpassword ="reallyStrongPwd123";
        String url ="jdbc:mysql://localhost/"+dbname;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaselink = DriverManager.getConnection(url,dbuser,dbpassword);
            System.out.println("connected");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return databaselink;


    }





    }



