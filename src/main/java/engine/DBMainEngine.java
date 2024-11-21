/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package engine;

import java.sql.*;
import java.util.*;
import model.Sach;
import utils.*;

/**
 *
 * @author hoang
 */
public class DBMainEngine {
    private Connection con;
    private ConnectionPool cp;
    
    public DBMainEngine(ConnectionPool cp){
        if(cp == null){
            this.cp = new ConnectionPool();
        } else {
            this.cp = cp;
        }
        try {
            this.con = this.cp.getConnection("User");
            if(this.con.getAutoCommit()){
                this.con.setAutoCommit(false);
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
    private boolean exe(PreparedStatement pre){
        if(pre != null){
            try {
                int results = pre.executeUpdate();
                if(results == 0){
                    this.con.rollback();
                    return false;
                }
                this.con.commit();
                return true;
            } catch(SQLException e){
                e.printStackTrace();
                try {
                    this.con.rollback();
                } catch(SQLException e1){
                    e1.printStackTrace();
                }
            } finally {
                pre = null;
            }
        }
        return false;
    }
    public List<Sach> ReadData(){
        List<Sach> list = null;
        String sql = "SELECT * FROM sach";
        try{
            PreparedStatement pre = this.con.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            if(rs != null){
                list = new ArrayList<>();
                while(rs.next()){
                    Sach s = new Sach();
                    s.setId(rs.getString("id"));
                    s.setName(rs.getString("name"));
                    s.setQuantity(rs.getInt("quantity"));
                    s.setType(rs.getString("type"));
                    s.setLanguage(rs.getString("language"));
                    s.setAbout(rs.getString("about"));
                    s.setNxb(rs.getString("nxb"));
                    s.setYear(rs.getInt("year"));
                    list.add(s);
                }
            }
        } catch(SQLException e){
            e.printStackTrace();
            try {
                this.con.rollback();
            } catch(SQLException e1){
                e1.printStackTrace();
            }
        }
        return list;
    }
    private boolean isExisting(Sach s){
        boolean flag = false;
        String sql = "SELECT id FROM sach WHERE id = ?";
        try {
            PreparedStatement pre = this.con.prepareStatement(sql);
            pre.setString(1, s.getId());
            ResultSet rs = pre.executeQuery();
            if(rs != null){
                if(rs.next()){
                    flag = true;
                }
                rs.close();
            }
        } catch(SQLException e){
            e.printStackTrace();
            try {
                this.con.rollback();
            } catch(SQLException e1){
                e1.printStackTrace();
            }
        }
        return flag;
    }
    public boolean addNew(Sach s){
        if(this.isExisting(s)){
            return false;
        }
        StringBuilder sqlQuery = new StringBuilder();
        sqlQuery.append("INSERT INTO sach ");
        sqlQuery.append("VALUES(?, ?, ?, ?, ?, ?, ?, ?);");
        try {
            PreparedStatement pre = this.con.prepareStatement(sqlQuery.toString());
            pre.setString(1, s.getId());
            pre.setString(2, s.getName());
            pre.setInt(3, s.getQuantity());
            pre.setString(4, s.getType());
            pre.setString(5, s.getLanguage());
            pre.setString(6, s.getAbout());
            pre.setString(7, s.getNxb());
            pre.setInt(8, s.getYear());
            
            return this.exe(pre);
        } catch(SQLException e){
            e.printStackTrace();
            try{
                this.con.rollback();
            } catch(SQLException e1){
                e1.printStackTrace();
            }
        }
        return false;
    }
    public boolean updateData(Sach s){
        if(!this.isExisting(s)){
            return false;
        }
        StringBuilder sqlQuery = new StringBuilder();
        sqlQuery.append("UPDATE sach ");
        sqlQuery.append("SET name = ?, quantity = ?, type = ?, language = ?, about = ?, nxb = ?, year = ? ");
        sqlQuery.append("WHERE id = ?;");
        try {
            PreparedStatement pre = this.con.prepareStatement(sqlQuery.toString());
            pre.setString(1, s.getName());
            pre.setInt(2, s.getQuantity());
            pre.setString(3, s.getType());
            pre.setString(4, s.getLanguage());
            pre.setString(5, s.getAbout());
            pre.setString(6, s.getNxb());
            pre.setInt(7, s.getYear());
            pre.setString(8, s.getId());
            
            return this.exe(pre);
        } catch(SQLException e){
            e.printStackTrace();
            try{
                this.con.rollback();
            } catch(SQLException e1){
                e1.printStackTrace();
            }
        }
        return false;
    }
    public boolean deleteData(String id){
        String sql = "DELETE FROM sach WHERE id = " + id + ";";
        try {
            PreparedStatement pre = this.con.prepareStatement(sql);            
            return this.exe(pre);
        } catch(SQLException e){
            e.printStackTrace();
            try{
                this.con.rollback();
            } catch(SQLException e1){
                e1.printStackTrace();
            }
        }
        return false;
    }
    public Sach timKiem(String id){
        String sql = "SELECT * FROM sach WHERE id = " + id + ";";
        Sach finding = null;
        try {
            PreparedStatement pre = this.con.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            if(rs != null){
                if(rs.next()){
                    finding = new Sach();
                    finding.setId(rs.getString("id"));
                    finding.setName(rs.getString("name"));
                    finding.setQuantity(rs.getInt("quantity"));
                    finding.setType(rs.getString("type"));
                    finding.setLanguage(rs.getString("language"));
                    finding.setAbout(rs.getString("about"));
                    finding.setNxb(rs.getString("nxb"));
                    finding.setYear(rs.getInt("year"));
                }
            }
        } catch(SQLException e){
            e.printStackTrace();
            try {
                this.con.rollback();
            } catch(SQLException e1){
                e1.printStackTrace();
            }
        }
        return finding;
    }
}
