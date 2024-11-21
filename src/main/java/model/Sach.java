/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model;

/**
 *
 * @author hoang
 */
public class Sach {
    private String id;
    private String name;
    private int quantity;
    private String type;
    private String language;
    private String about;
    private String nxb;
    private int year;

    public Sach() {
    }

    public Sach(String id, String name, int quantity, String type, String language, String about, String nxb, int year) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.type = type;
        this.language = language;
        this.about = about;
        this.nxb = nxb;
        this.year = year;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getNxb() {
        return nxb;
    }

    public void setNxb(String nxb) {
        this.nxb = nxb;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\nTên sách: " + name + "\nSố lượng: " + quantity + "\nThể loại: " + type + "\nNgôn ngữ: " + language + "\nGiới thiệu: " + about + "\nNXB: " + nxb + "\nNăm xuất bản: " + year;
    }
    
    
}
