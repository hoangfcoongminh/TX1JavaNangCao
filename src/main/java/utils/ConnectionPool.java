/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package utils;

import java.sql.*;
import java.util.*;

/**
 *
 * @author hoang
 */
public class ConnectionPool {
    private String driver;
    private String username;
    private String password;
    private String url;
    private Stack<Connection> pool;
    
    public ConnectionPool(){
        this.driver = "com.mysql.cj.jdbc.Driver";
        try {
            Class.forName(this.driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        this.username = "tx1";
        this.password = "Hoangcongminh";
        
        this.url = "jdbc:mysql://localhost:3306/tx1?allowMultiQueries=true";
        this.pool = new Stack<>();
    }
    public Connection getConnection(String objectName) throws SQLException {
        if(this.pool.isEmpty()){
            System.out.println(objectName + " da khoi tao bo nho moi !");
            return DriverManager.getConnection(this.url, this.username, this.password);
        } else {
            System.out.println(objectName + " da lay ket noi !");
            return this.pool.pop();
        }
    }
    public void releaseConnection(Connection con, String objectName) throws SQLException {
        System.out.println(objectName + " da tra ve mot ket noi !");
        this.pool.push(con);
    }
    protected void finalize() throws Throwable {
        this.pool.clear();
        this.pool = null;
       System.out.println("CPool is Closed !");
    }
}
