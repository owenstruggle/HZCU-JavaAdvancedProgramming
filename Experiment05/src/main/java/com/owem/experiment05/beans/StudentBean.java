package com.owem.experiment05.beans;

/**
 * @projectName: Experiment05
 * @package: com.owem.experiment05
 * @className: StudentBean
 * @author: Owem
 * @description: TODO
 * @date: 2022/10/18 17:42
 * @version: 1.0
 */
public class StudentBean {
    private int id;
    private String full_name;
    private boolean gender;
    private String phone_number;
    private String address;
    private int hobby;
    private String area;
    private int develop_language;

    public StudentBean() {
    }

    public StudentBean(int id, String full_name, boolean gender, String phone_number, String address, int hobby, String area, int develop_language) {
        this.id = id;
        this.full_name = full_name;
        this.gender = gender;
        this.phone_number = phone_number;
        this.address = address;
        this.hobby = hobby;
        this.area = area;
        this.develop_language = develop_language;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getHobby() {
        return hobby;
    }

    public void setHobby(int hobby) {
        this.hobby = hobby;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getDevelop_language() {
        return develop_language;
    }

    public void setDevelop_language(int develop_language) {
        this.develop_language = develop_language;
    }
}
