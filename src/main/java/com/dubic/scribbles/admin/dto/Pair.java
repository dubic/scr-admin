/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dubic.scribbles.admin.dto;

/**
 *
 * @author dubem
 */
public class Pair {
    private String key;
    private Long lvalue;
    private int dvalue;

    public Pair(String key, Long lvalue) {
        this.key = key;
        this.lvalue = lvalue;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Long getLvalue() {
        return lvalue;
    }

    public void setLvalue(Long nvalue) {
        this.lvalue = nvalue;
    }

    public int getDvalue() {
        return dvalue;
    }

    public void setDvalue(int dvalue) {
        this.dvalue = dvalue;
    }
    
    
}
