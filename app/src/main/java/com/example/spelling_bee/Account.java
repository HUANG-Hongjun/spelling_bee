/**
 * A class of one user
 * include id (name) and password
 * Author : HUANG Hongjun && YUAN Mengcheng
 * ISE-OC
 * ESIGELEC
 */

package com.example.spelling_bee;

public class Account {
    private String name;//user id
    private String password;//user password

    public Account(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
