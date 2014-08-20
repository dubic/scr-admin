/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dubic.scribbles.admin;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dubem
 */
public class DT {
    private Long iTotalRecords;
    private Long iTotalDisplayRecords;
    private List<Object[]> aaData = new ArrayList<Object[]>();
    
    public static void main(String[] arrg){
        DT d = new DT();
        d.setAaData(new ArrayList<Object[]>());
        d.setiTotalDisplayRecords(234444L);
        d.setiTotalRecords(234444L);
        
        System.out.println(new Gson().toJson(d));
    }

    public DT(int size) {
        this.aaData = new ArrayList<Object[]>(size); 
    }

    public DT() {
        
    }

    public Long getiTotalRecords() {
        return iTotalRecords;
    }

    public void setiTotalRecords(Long iTotalRecords) {
        this.iTotalRecords = iTotalRecords;
    }

    public Long getiTotalDisplayRecords() {
        return iTotalDisplayRecords;
    }

    public void setiTotalDisplayRecords(Long iTotalDisplayRecords) {
        this.iTotalDisplayRecords = iTotalDisplayRecords;
    }

    public List<Object[]> getAaData() {
        return aaData;
    }

    public void setAaData(List<Object[]> aaData) {
        this.aaData = aaData;
    }
    
    
}
