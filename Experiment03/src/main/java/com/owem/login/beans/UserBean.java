package com.owem.login.beans;

/**
 * @author Owem
 * @date 2022/9/27 16:26
 * @description TODO
 **/
public class UserBean {
    private int id;
    private String password;

    public UserBean() {
    }

    public UserBean(int id, String password) {
        this.id = id;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
