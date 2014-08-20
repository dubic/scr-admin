/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dubic.scribbles.admin.dt;

/**
 *
 * @author dubem
 */
public class Param {
    private String name;
    private String operator;
    private Object value;

//    private boolean and = true;
    
    public Param(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public Param(String name, String operator, Object value) {
        this.name = name;
        this.operator = operator;
        this.value = value;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
    
    
}
